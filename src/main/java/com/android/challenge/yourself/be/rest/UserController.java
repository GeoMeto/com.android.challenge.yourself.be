package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.*;
import com.android.challenge.yourself.be.model.core.AuthToken;
import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.UserService;
import com.android.challenge.yourself.be.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/secure", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization") String token) {
        authService.expireTokens(token);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("200", "User was logged out from all devices!"));
    }

    @PostMapping("/change/password")
    public ResponseEntity<AuthToken> changePassword(@RequestHeader("Authorization") String token, @Valid @RequestBody ChangePassword changePassword) {
        User userForUpdate = authService.getUser(token);
        if (null != userForUpdate && userForUpdate.getId() > 0 && passwordEncoder.matches(encryptionUtils.decrypt(changePassword.getCurrentPassword()), userForUpdate.getPassword())) {
            authService.expireTokens(token);
            userForUpdate.setPassword(encryptionUtils.decrypt(changePassword.getNewPassword()));
            if (userService.updatePassword(userForUpdate)) {
                AuthToken authToken = new AuthToken();
                authToken.setUser(userForUpdate);
                authService.saveToken(authToken);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(authToken);
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AuthToken());

    }

    @PostMapping("/delete/account")
    public ResponseEntity<Response> deleteAccount(@RequestHeader("Authorization") String token) {
        User oldUser = authService.getUser(token);
        if (null != oldUser) {
            authService.expireTokens(token);
            userService.deleteUser(oldUser.getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("200", "Account was softly deleted and user was logged out from all devices!"));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("400", "There was a problem with account deletion!"));
    }

    @PostMapping("/change/username")
    public ResponseEntity<Response> changeUsername(@RequestHeader("Authorization") String token, @RequestBody Username username) {
        User userForUpdate = authService.getUser(token);
        if (null != userForUpdate) {
            userService.updateUsername(userForUpdate.getId(), username.getUsername());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("200", "Username was changed successfully"));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("400", "There was a problem with username deletion!"));
    }

    @GetMapping("/username")
    public ResponseEntity<Username> getUsername(@RequestHeader("Authorization") String token) {
        User user = authService.getUser(token);
        if (null != user) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Username(user.getUsername()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Username());
    }
}
