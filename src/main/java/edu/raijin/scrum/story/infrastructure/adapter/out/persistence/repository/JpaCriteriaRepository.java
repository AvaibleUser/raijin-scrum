package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.CriteriaEntity;

@Repository
public interface JpaCriteriaRepository extends JpaRepository<CriteriaEntity, Long> {

    Optional<CriteriaEntity> findByIdAndStoryIdAndDeletedFalse(Long id, Long storyId);

    List<CriteriaEntity> findAllByStoryIdAndDeletedFalse(Long storyId);
}
