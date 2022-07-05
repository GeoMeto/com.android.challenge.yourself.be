package com.android.challenge.yourself.be.model.entities;

import com.android.challenge.yourself.be.model.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shared_challenge")
@NamedQuery(name = "SharedChallenge.softDeleteSharedChallenge", query = "UPDATE SharedChallenge s SET s.isDeleted = '1' WHERE s.id = ?1")
public class SharedChallenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    private int likes;
    private int reports;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ch_id", referencedColumnName = "id", nullable = false)
    private CompletedChallenge completedChallenge;

    @OneToMany(mappedBy = "sharedChallenge")
    private Set<UserComment> userComments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sharedChallenge")
    private Set<UserSharingLike> likedSharings;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sharedChallenge")
    private Set<ReportedSharing> reportedSharings;
}
