package com.android.challenge.yourself.be.model.entities;

import com.android.challenge.yourself.be.model.core.AuthToken;
import com.android.challenge.yourself.be.model.core.BaseEntity;
import com.android.challenge.yourself.be.model.like.UserSharingLike;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "User.updateUserPassword",
                query = "UPDATE User u SET u.password = ?1 WHERE u.id = ?2"),
        @NamedQuery(name = "User.softDeleteUser",
                query = "UPDATE User u SET u.isDeleted = '1' WHERE u.id = ?1"),
        @NamedQuery(name = "User.updateUsername",
                query = "UPDATE User u SET u.username = ?1 WHERE u.id = ?2")
})

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Size(max = 100, message = "Username must be between 3 and 45 characters long")
    private String username;

    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    private String role;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isActive;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private Set<CompletedChallenge> completedChallenges;

    @OneToMany(mappedBy = "user")
    private Set<SharedChallenge> sharedChallenges;

    @OneToMany(mappedBy = "user")
    private Set<UserComment> userComments;

    @OneToMany(mappedBy = "user")
    private Set<AuthToken> authTokens;

    @OneToMany(mappedBy = "user")
    private Set<UserSharingLike> likedSharings;
}
