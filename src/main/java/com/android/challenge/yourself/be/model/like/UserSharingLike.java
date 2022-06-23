package com.android.challenge.yourself.be.model.like;

import com.android.challenge.yourself.be.model.SharedChallenge;
import com.android.challenge.yourself.be.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_sharing_likes")
public class UserSharingLike {

    @EmbeddedId
    private UserSharingLikeId userSharingLikeId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("sharingId")
    @JoinColumn(name = "sharing_id")
    private SharedChallenge sharedChallenge;
}
