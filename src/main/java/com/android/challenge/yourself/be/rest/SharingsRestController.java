package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.model.dto.SharedChallengeDTO;
import com.android.challenge.yourself.be.model.entities.CompletedChallenge;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.model.like.LikesDTO;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.CompletedChallengesService;
import com.android.challenge.yourself.be.service.SharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/secure/share", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class SharingsRestController {
    @Autowired
    private SharingService sharingService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CompletedChallengesService completedChallengesService;

    @GetMapping("/all")
    public ResponseEntity<List<SharedChallenge>> getSharings() {
        List<SharedChallenge> sharedChallenges = sharingService.getSharings();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/user")
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
    public ResponseEntity<Response> createSharing(@RequestHeader("Authorization") String token, @RequestBody SharedChallengeDTO dto) {
        User user = authService.getUser(token);
        SharedChallenge sharedChallenge = new SharedChallenge();
        CompletedChallenge completedChallenge = completedChallengesService.getCompletedChallenge(dto.getCompletedChallengeDTO().getId());
        sharedChallenge.setUser(user);
        sharedChallenge.setCompletedChallenge(completedChallenge);
        if (sharingService.getSharings(user.getId()).stream().anyMatch(x -> x.getCompletedChallenge().getId() == completedChallenge.getId())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new Response("409", "Challenge is already shared!"));
        }
        sharingService.saveSharing(sharedChallenge);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("200", "Challenge was shared"));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<LikesDTO> likeSharing(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);
        LikesDTO like = sharingService.likeSharing(user.getId(), id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(like);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteSharing(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);

        sharingService.deleteSharing(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("200", "Sharing was deleted successfully"));
    }
}
