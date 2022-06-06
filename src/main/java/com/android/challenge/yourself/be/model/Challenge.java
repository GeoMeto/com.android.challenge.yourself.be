package com.android.challenge.yourself.be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "challenge")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 45, message = "Name must be between 3 and 45 characters long")
    private String name;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 45, message = "Measurement must be between 3 and 45 characters long")
    private String measurement;

    private int target;
    @Size(max = 100, message = "Description can be max 100 characters long")
    private String description;
}
