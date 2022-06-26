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
import java.util.stream.Collectors;

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
    public ResponseEntity<List<SharedChallengeDTO>> getSharings() {
        List<SharedChallengeDTO> sharedChallenges = sharingService.getSharings().stream().map(SharedChallengeDTO::new).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/user")
    public ResponseEntity<List<SharedChallengeDTO>> getUserSharings(@PathVariable int userId) {
        List<SharedChallengeDTO> sharedChallenges = sharingService.getSharings(userId).stream().map(SharedChallengeDTO::new).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/hot")
    public ResponseEntity<List<SharedChallengeDTO>> getHotSharings() {
        List<SharedChallengeDTO> sharedChallenges = sharingService.getHotSharings().stream().map(SharedChallengeDTO::new).collect(Collectors.toList());
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
                .body(new Response("200", "Sharing was softly deleted"));
    }
}
