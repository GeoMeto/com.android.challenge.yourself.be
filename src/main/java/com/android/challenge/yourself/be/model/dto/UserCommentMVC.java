package com.android.challenge.yourself.be.model.dto;


import com.android.challenge.yourself.be.model.entities.ReportedComment;
import com.android.challenge.yourself.be.model.entities.SharedChallenge;
import com.android.challenge.yourself.be.model.entities.UserComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentMVC {

    private int id;
    private int reportsCount;
    private String email;
    private String name;
    private String comment;

    public UserCommentMVC(UserComment comment) {
        this.setId(comment.getId());
        this.setEmail(comment.getUser().getEmail());
        this.setName(comment.getSharedChallenge().getCompletedChallenge().getName());
        this.setComment(comment.getContent());
        this.setReportsCount(comment.getReports());
    }
}
