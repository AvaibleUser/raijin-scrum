package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;

@Repository
public interface JpaStageRepository extends JpaRepository<StagesEntity, Long> {

    Optional<StagesEntity> findByIdAndDeletedFalseAndSprintIdAndSprintDeletedFalseAndSprintProjectDeletedFalse(Long id,
            Long sprintId);

    Page<StagesEntity> findBySprintIdAndSprintDeletedFalseAndSprintProjectDeletedFalse(Long sprintId,
            Pageable pageable);

    boolean existsBySprintIdAndIsDefaultTrue(Long sprintId);

    boolean existsByIdNotAndSprintIdAndIsDefaultTrue(Long sprintId, Long stageId);

    default Page<StagesEntity> findPageByEachId(Long sprintId, Pageable pageable) {
        return findBySprintIdAndSprintDeletedFalseAndSprintProjectDeletedFalse(sprintId, pageable);
    }

    default Optional<StagesEntity> findByEachId(Long id, Long sprintId) {
        return findByIdAndDeletedFalseAndSprintIdAndSprintDeletedFalseAndSprintProjectDeletedFalse(id, sprintId);
    }
}
