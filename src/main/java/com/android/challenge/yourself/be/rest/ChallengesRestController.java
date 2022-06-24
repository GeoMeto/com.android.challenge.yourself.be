package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.model.dto.ChallengeDTO;
import com.android.challenge.yourself.be.model.dto.CompletedChallengeDTO;
import com.android.challenge.yourself.be.model.entities.Challenge;
import com.android.challenge.yourself.be.model.entities.CompletedChallenge;
import com.android.challenge.yourself.be.model.entities.User;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.ChallengesService;
import com.android.challenge.yourself.be.service.CompletedChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/secure/challenges", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class ChallengesRestController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ChallengesService challengesService;
    @Autowired
    private CompletedChallengesService completedChallengesService;

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getChallenges() {
        List<ChallengeDTO> challenges = challengesService.getChallenges()
                .stream().map(ChallengeDTO::new).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(challenges);
    }

    @PostMapping("/new")
    public ResponseEntity<Response> createChallenge(@RequestHeader("Authorization") String token,
                                                    @Valid @RequestBody CompletedChallenge challenge) {
        User user = authService.getUser(token);
        challenge.setUser(user);
        completedChallengesService.saveChallenge(challenge);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("200", "Challenge added!"));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<CompletedChallengeDTO>> getChallenges(@RequestHeader("Authorization") String token) {
        User user = authService.getUser(token);
        List<CompletedChallengeDTO> completedChallenges = completedChallengesService.getCompletedChallenges(user.getId())
                .stream().map(CompletedChallengeDTO::new).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(completedChallenges);
    }

    @PostMapping("/complete")
    public ResponseEntity<Response> completeChallenge(@RequestHeader("Authorization") String token,
                                                      @Valid @RequestBody CompletedChallenge challenge) {
        User user = authService.getUser(token);
        challenge.setUser(user);
        if (challenge.getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response("400", "Missing challenge id!"));
        }
        challenge.setUser(user);
        challenge.setCompleted(true);
        completedChallengesService.saveChallenge(challenge);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("200", "Challenge completed!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteChallenge(@PathVariable int id) {
        completedChallengesService.deleteChallenge(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("200", "Challenge completed!"));
    }
}
