package com.android.challenge.yourself.be.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ChangePassword {
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String currentPassword;
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String newPassword;
}
