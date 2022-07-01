package com.android.challenge.yourself.be.model.dto;

import com.android.challenge.yourself.be.model.core.AuthToken;
import com.android.challenge.yourself.be.model.core.BaseEntity;
import com.android.challenge.yourself.be.model.entities.*;
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

public class UserDTO extends BaseEntity {
    private Integer id;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Size(max = 100, message = "Username must be between 3 and 45 characters long")
    private String username;

    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    private String role;

    private String isDeleted;
}
