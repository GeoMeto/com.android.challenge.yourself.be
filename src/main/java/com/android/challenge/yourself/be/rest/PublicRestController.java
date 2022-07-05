package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.entities.AuthToken;
import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.model.dto.Login;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.repository.UserRepository;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.UserService;
import com.android.challenge.yourself.be.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class PublicRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@Valid @RequestBody Login login) {
        User user = userRepository.readByEmail(login.getEmail());
        if (null != user && user.getId() > 0 && passwordEncoder.matches(encryptionUtils.decrypt(login.getPass()), user.getPassword())) {
            if (user.getIsDeleted()) {
                return ResponseEntity
                        .status(HttpStatus.LOCKED)
                        .body(null);
            }
            AuthToken authToken = new AuthToken();
            authToken.setUser(user);
            authService.saveToken(authToken);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authToken);
        }
        throw new BadCredentialsException("Invalid credentials!");
    }

    @PostMapping("/validate")
    public ResponseEntity<Response> loginWithToken(@RequestBody AuthToken token) {
        if (authService.isTokenValid(token.getToken())) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("200", "Token is valid!"));
        }
        throw new BadCredentialsException("Invalid token!");
    }

    @PostMapping("/user")
    public ResponseEntity<AuthToken> createUser(@Valid @RequestBody User user) {
        User foundUser = userRepository.readByEmail(user.getEmail());
        if (foundUser != null && foundUser.getIsDeleted()) {
            user.setId(foundUser.getId());
        }
        boolean isSaved = userService.createUser(user);
        if (!isSaved) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(null);
        }
        AuthToken authToken = new AuthToken();
        authToken.setUser(user);
        authService.saveToken(authToken);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authToken);
    }
}
