package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.UUID;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import edu.raijin.commons.domain.type.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity(name = "projects")
@Table(name = "projects", schema = "project")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String name;

    private String description;

    private String client;

    @Builder.Default
    @Enumerated(STRING)
    private ProjectStatus status = ProjectStatus.ACTIVE;

    @Builder.Default
    private BigDecimal monthlyIncome = BigDecimal.ZERO;

    @Builder.Default
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    private Instant closedAt;

    private Instant deletedAt;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "members", schema = "project", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UsersEntity> members;
}
