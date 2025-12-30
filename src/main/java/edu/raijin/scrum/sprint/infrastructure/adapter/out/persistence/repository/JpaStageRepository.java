package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;

@Repository
public interface JpaStageRepository extends JpaRepository<StagesEntity, Long> {

    Optional<StagesEntity> findByIdAndSprintIdAndDeletedFalse(Long id, UUID sprintId);

    Optional<StagesEntity> findFirstBySprintIdAndDeletedFalseOrderByIsDefaultDescIdAsc(UUID sprintId);

    List<StagesEntity> findBySprintIdAndDeletedFalse(UUID sprintId);

    boolean existsByIdAndDeletedFalse(Long id);

    boolean existsBySprintIdAndIsDefaultTrue(UUID sprintId);

    boolean existsByIdNotAndSprintIdAndIsDefaultTrue(Long stageId, UUID sprintId);

    default Optional<StagesEntity> findBySprintId(UUID sprintId) {
        return findFirstBySprintIdAndDeletedFalseOrderByIsDefaultDescIdAsc(sprintId);
    }
}
