package com.android.challenge.yourself.be.model.dto;

import com.android.challenge.yourself.be.model.core.BaseEntity;
import com.android.challenge.yourself.be.model.entities.Challenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO extends BaseEntity {
    private Integer id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 45, message = "Name must be between 3 and 45 characters long")
    private String name;

    private String isDeleted;
}
