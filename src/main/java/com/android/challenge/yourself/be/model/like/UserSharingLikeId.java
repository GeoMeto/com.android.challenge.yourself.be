package com.android.challenge.yourself.be.model.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserSharingLikeId implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "sharing_id")
    private int sharingId;
}
