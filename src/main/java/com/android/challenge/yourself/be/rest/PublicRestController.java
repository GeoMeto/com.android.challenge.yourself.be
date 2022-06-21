package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.AuthToken;
import com.android.challenge.yourself.be.model.Login;
import com.android.challenge.yourself.be.model.User;
import com.android.challenge.yourself.be.repository.UserRepository;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Login login) throws Exception {
        User user = userRepository.readByEmail(login.getEmail());

        if (null != user && user.getId() > 0 && passwordEncoder.matches(encryptionUtils.decrypt(login.getPass()), user.getPassword())) {
            AuthToken authToken = new AuthToken();
            authToken.setUser(user);
            authToken.setToken(passwordEncoder.encode(RandomStringUtils.randomAlphabetic(10)));
            authService.saveToken(authToken);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Token was created: " + authToken.getToken());
        }
        throw new BadCredentialsException("Invalid credentials!");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String token) {
        if (authService.isTokenValid(token)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Token is valid!");
        }
        throw new BadCredentialsException("Invalid token!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (authService.expireTokens(token)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User was logged out from all devices!");
        }
        throw new BadCredentialsException("Invalid token!");
    }
}
