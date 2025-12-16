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

    Page<SprintsEntity> findByProjectIdAndDeletedFalseAndDeletedFalse(UUID projectId, Pageable pageable);

    Optional<SprintsEntity> findByIdAndDeletedFalseAndProjectIdAndProjectDeletedFalse(Long id, UUID projectId);

    boolean existsByProjectIdAndProjectDeletedFalseAndStatusAndDeletedFalse(UUID projectId, SprintStatus status);

    boolean existsByIdAndDeletedFalseAndProjectDeletedFalse(Long id);

    default Optional<SprintsEntity> findByIdAndProjectId(Long id, UUID projectId) {
        return findByIdAndProjectId(id, projectId);
    }

    default Page<SprintsEntity> findProjectSprintsPage(UUID projectId, Pageable pageable) {
        return findByProjectIdAndDeletedFalseAndDeletedFalse(projectId, pageable);
    }

    default boolean existsActiveProjectSprint(UUID projectId) {
        return existsByProjectIdAndProjectDeletedFalseAndStatusAndDeletedFalse(projectId, SprintStatus.ACTIVE);
    }

    default boolean existsProjectSprint(Long sprintId) {
        return existsByIdAndDeletedFalseAndProjectDeletedFalse(sprintId);
    }
}
