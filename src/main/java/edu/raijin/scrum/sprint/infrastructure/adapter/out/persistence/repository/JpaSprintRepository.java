package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.commons.domain.type.SprintStatus;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.SprintsEntity;

@Repository
public interface JpaSprintRepository extends JpaRepository<SprintsEntity, Long> {

    Page<SprintsEntity> findByProjectId(UUID projectId, Pageable pageable);

    Optional<SprintsEntity> findByIdAndProjectId(Long id, UUID projectId);

    boolean existsByProjectIdAndStatus(UUID projectId, SprintStatus status);

    default boolean existsActiveProjectSprint(UUID projectId) {
        return existsByProjectIdAndStatus(projectId, SprintStatus.ACTIVE);
    }
}
