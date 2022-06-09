package com.android.challenge.yourself.be.model;

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
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Size(max = 100, message = "Username must be between 3 and 45 characters long")
    private String username;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    private String role;
    private boolean isActive;
    private boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private Set<CompletedChallenge> completedChallenges;

    @OneToMany(mappedBy = "user")
    private Set<SharedChallenge> sharedChallenges;

    @OneToMany(mappedBy = "user")
    private Set<UserComment> userComments;
}
