package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.StoriesEntity;

@Repository
public interface JpaStoryRepository extends JpaRepository<StoriesEntity, UUID> {

    boolean existsByIdAndDeletedFalse(UUID id);

    Optional<StoriesEntity> findByIdAndStageIdAndDeletedFalse(UUID id, Long stageId);

    List<StoriesEntity> findByStageIdAndDeletedFalse(Long stageId);

    List<StoriesEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
