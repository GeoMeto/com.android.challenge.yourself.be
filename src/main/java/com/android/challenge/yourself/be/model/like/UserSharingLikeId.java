package com.android.challenge.yourself.be.model.like;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class UserSharingLikeId implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "sharing_id")
    private int sharingId;
}
