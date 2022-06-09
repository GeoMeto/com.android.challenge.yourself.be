package com.android.challenge.yourself.be.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "completed_challenge")
public class CompletedChallenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 45, message = "Name must be between 3 and 45 characters long")
    private String name;

    @NotBlank(message = "Measurement must not be blank")
    @Size(min = 3, max = 45, message = "Measurement must be between 3 and 45 characters long")
    private String measurement;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must be between 3 and 45 characters long")
    private String comment;

    private int result;
    private int target;
    private boolean isShared;
    private boolean isCompleted;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
