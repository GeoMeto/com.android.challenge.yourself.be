package com.android.challenge.yourself.be.model.entities;

import com.android.challenge.yourself.be.model.core.BaseEntity;
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
@NamedQueries({
        @NamedQuery(name = "CompletedChallenge.softDeleteChallenge",
                query = "UPDATE CompletedChallenge c SET c.isDeleted = '1' WHERE c.id = ?1")
})
public class CompletedChallenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Integer id;
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 45, message = "Name must be between 3 and 45 characters long")
    private String name;
    @NotBlank(message = "Measurement must not be blank")
    @Size(min = 3, max = 45, message = "Measurement must be between 3 and 45 characters long")
    private String measurement;
    @Size(max = 100, message = "Description must be less than 100 characters long")
    private String description;
    @Size(max = 100, message = "Comment must be be less than 100 characters long")
    private String comment;
    private int result;
    private int target;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isShared;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isCompleted;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
