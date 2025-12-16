package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;

@Repository
public interface JpaStageRepository extends JpaRepository<StagesEntity, Long> {

    Optional<StagesEntity> findByIdAndSprintIdAndDeletedFalse(Long id, Long sprintId);

    Page<StagesEntity> findBySprintIdAndDeletedFalse(Long sprintId, Pageable pageable);

    boolean existsByIdAndDeletedFalse(Long id);

    boolean existsBySprintIdAndIsDefaultTrue(Long sprintId);

    boolean existsByIdNotAndSprintIdAndIsDefaultTrue(Long stageId, Long sprintId);
}
