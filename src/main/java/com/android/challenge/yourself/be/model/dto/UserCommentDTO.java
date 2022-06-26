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
    private boolean isOwner;
    private String username;

    public UserCommentDTO(UserComment userComment, boolean isOwner) {
        this.setId(userComment.getId());
        this.setContent(userComment.getContent());
        this.setOwner(isOwner);
        this.setUsername(userComment.getUser().getUsername());
    }
}
