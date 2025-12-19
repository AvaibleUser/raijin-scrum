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

    boolean existsByProjectIdAndStatusNotAndDeletedFalse(UUID projectId, SprintStatus status);

    Page<SprintsEntity> findByProjectIdAndDeletedFalse(UUID projectId, Pageable pageable);

    boolean existsByStatusAndDeletedFalseAndProjectId(SprintStatus status, UUID projectId);

    boolean existsByStatusAndDeletedFalseAndIdNotAndProjectId(SprintStatus status, Long id, UUID projectId);

    boolean existsByIdAndDeletedFalse(Long id);

    Optional<SprintsEntity> findByIdAndProjectId(Long id, UUID projectId);

    default boolean existsByActiveAndProjectId(UUID projectId) {
        return existsByStatusAndDeletedFalseAndProjectId(SprintStatus.ACTIVE, projectId);
    }

    default boolean existsByActiveAndNotIdAndProjectId(Long id, UUID projectId) {
        return existsByStatusAndDeletedFalseAndIdNotAndProjectId(SprintStatus.ACTIVE, id, projectId);
    }

    default boolean existsByNotFinishedAndProjectId(UUID projectId) {
        return existsByProjectIdAndStatusNotAndDeletedFalse(projectId, SprintStatus.FINISHED);
    }
}
