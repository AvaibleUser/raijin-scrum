package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.port.persistence.FindCriteriaPort;
import edu.raijin.scrum.story.domain.port.persistence.RegisterCriteriaPort;
import edu.raijin.scrum.story.domain.port.persistence.UpdateCriteriaPort;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.CriteriaEntity;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.StoriesEntity;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.mapper.CriteriaEntityMapper;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository.JpaCriteriaRepository;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository.JpaStoryRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class CriteriaRepository implements RegisterCriteriaPort, FindCriteriaPort, UpdateCriteriaPort {

    private final JpaCriteriaRepository criteriaRepository;
    private final JpaStoryRepository storyRepository;
    private final CriteriaEntityMapper mapper;

    @Override
    public Optional<Criteria> findByIdAndStoryId(Long criteriaId, Long storyId) {
        return criteriaRepository.findByIdAndStoryIdAndDeletedFalse(criteriaId, storyId).map(mapper::toDomain);
    }

    @Override
    public Criteria update(Criteria criteria) {
        CriteriaEntity entity = mapper.toEntity(criteria);
        return mapper.toDomain(criteriaRepository.save(entity));
    }

    @Override
    public List<Criteria> findAll(Long storyId) {
        List<CriteriaEntity> criteria = criteriaRepository.findAllByStoryIdAndDeletedFalse(storyId);
        return criteria.stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsStory(Long storyId) {
        return storyRepository.existsByIdAndDeletedFalse(storyId);
    }

    @Override
    public Criteria create(Long storyId, Criteria criteria) {
        StoriesEntity story = storyRepository.findById(storyId).get();
        CriteriaEntity entity = mapper.toEntity(criteria).withStory(story);
        return mapper.toDomain(criteriaRepository.save(entity));
    }
}
