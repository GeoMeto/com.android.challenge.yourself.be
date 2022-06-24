package com.android.challenge.yourself.be.model.dto;

import com.android.challenge.yourself.be.model.entities.UserComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentDTO {
    private int id;
    private String content;
    private int userId;

    public UserCommentDTO(UserComment userComment) {
        this.setId(userComment.getId());
        this.setContent(userComment.getContent());
        this.setUserId(userComment.getUser().getId());
    }
}
