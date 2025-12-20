package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.StoriesEntity;

@Repository
public interface JpaStoryRepository extends JpaRepository<StoriesEntity, Long> {

    boolean existsByIdAndDeletedFalse(Long id);

    Optional<StoriesEntity> findByIdAndStageIdAndDeletedFalse(Long id, Long stageId);

    List<StoriesEntity> findByStageIdAndDeletedFalse(Long stageId);

    List<StoriesEntity> findByProjectIdAndDeletedFalse(UUID projectId);
}
