package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import edu.raijin.commons.domain.type.StoryPriority;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;
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

@Entity(name = "stories")
@Table(name = "stories", schema = "story")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class StoriesEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @With
    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = true)
    private StagesEntity stage;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectsEntity project;

    @ManyToOne
    @JoinColumn(name = "product_owner_id", nullable = true)
    private UsersEntity productOwner;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = true)
    private UsersEntity developer;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer points;

    @Enumerated(STRING)
    @Column(nullable = false)
    private StoryPriority priority;

    @Builder.Default
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
