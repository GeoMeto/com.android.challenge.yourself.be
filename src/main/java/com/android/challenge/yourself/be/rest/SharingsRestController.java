package com.android.challenge.yourself.be.rest;

import com.android.challenge.yourself.be.model.core.Response;
import com.android.challenge.yourself.be.model.dto.SharedChallengeDTO;
import com.android.challenge.yourself.be.model.dto.UserCommentDTO;
import com.android.challenge.yourself.be.model.entities.*;
import com.android.challenge.yourself.be.model.like.LikesDTO;
import com.android.challenge.yourself.be.service.AuthService;
import com.android.challenge.yourself.be.service.CommentService;
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
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<SharedChallengeDTO>> getSharings() {
        List<SharedChallengeDTO> sharedChallenges = sharingService.getSharings().stream().map(x -> new SharedChallengeDTO(x)).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/user")
    public ResponseEntity<List<SharedChallengeDTO>> getUserSharings(@RequestHeader("Authorization") String token) {
        User user = authService.getUser(token);

        List<SharedChallengeDTO> sharedChallenges = sharingService.getSharings(user.getId()).stream().map(x -> new SharedChallengeDTO(x)).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sharedChallenges);
    }

    @GetMapping("/hot")
    public ResponseEntity<List<SharedChallengeDTO>> getHotSharings() {
        List<SharedChallengeDTO> sharedChallenges = sharingService.getHotSharings().stream().map(x -> new SharedChallengeDTO(x)).collect(Collectors.toList());
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
        LikesDTO like = sharingService.likeSharing(user, id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(like);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteSharing(@PathVariable int id) {
        sharingService.deleteSharing(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("200", "Sharing was softly deleted"));
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity<List<UserCommentDTO>> getComments(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);
        List<UserCommentDTO> comments = sharingService.getSharingComments(id, user.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comments);
    }

    @PostMapping("/{id}/comment/new")
    public ResponseEntity<UserCommentDTO> postComment(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody UserCommentDTO dto) {
        User user = authService.getUser(token);
        UserComment userComment = new UserComment();
        userComment.setUser(user);
        userComment.setContent(dto.getContent());
        userComment.setSharedChallenge(sharingService.getSharing(id));
        UserCommentDTO savedComment = new UserCommentDTO(commentService.saveComment(userComment), true);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedComment);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Response> deleteComment(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);
        UserComment userComment = commentService.getComment(id);

        if (user.getId() != userComment.getUser().getId()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new Response("403", "You can not delete other user's comment!"));
        }
        commentService.deleteComment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response("200", "Comment was deleted"));
    }

    @PostMapping("comment/report/{id}")
    public ResponseEntity<Response> reportComment(@RequestHeader("Authorization") String token, @PathVariable int id) {
        User user = authService.getUser(token);
        UserComment userComment = commentService.getComment(id);
        ReportedComment reportedComment = new ReportedComment();
        reportedComment.setUser(user);
        reportedComment.setUserComment(userComment);
        if (commentService.saveReportedComment(reportedComment)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new Response("409", "Comment was already reported!"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response("201", "Comment report was saved."));
    }
}
