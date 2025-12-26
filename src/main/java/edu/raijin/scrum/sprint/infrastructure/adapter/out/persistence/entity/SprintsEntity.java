package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.UUID;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import edu.raijin.commons.domain.type.SprintStatus;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity(name = "sprints")
@Table(name = "sprints", schema = "sprint")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class SprintsEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @With
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectsEntity project;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Builder.Default
    @Enumerated(STRING)
    private SprintStatus status = SprintStatus.PENDING;

    @Builder.Default
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
