package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.model.like.Like;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.SharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/secure/share", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class SharingsRestController {
    @Autowired
    private SharingService sharingService;
    @Autowired
    private AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<List<SharedChallenge>> getSharings() {
        List<SharedChallenge> sharedChallenges = sharingService.getSharings();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SharedChallenge>> getUserSharings(@PathVariable int userId) {
        List<SharedChallenge> sharedChallenges = sharingService.getSharings(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/hot")
    public ResponseEntity<List<SharedChallenge>> getHotSharings() {
        List<SharedChallenge> sharedChallenges = sharingService.getHotSharings();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @PostMapping("/new")
    public ResponseEntity<Response> createSharing(@RequestHeader("Authorization") String token, @Valid @RequestBody SharedChallenge sharedChallenge) {
        User user = authService.getUser(token);
        sharedChallenge.setUser(user);
        sharingService.saveSharing(sharedChallenge);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("200", "Challenge was shared"));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Like> likeSharing(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);
        Like like = sharingService.likeSharing(user.getId(), id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(like);
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<Response> dislikeSharing(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("200", "Dislike was successful"));
    }
}
