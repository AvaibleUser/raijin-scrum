package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.repository.JpaStageRepository;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.FindStoryPort;
import edu.raijin.scrum.story.domain.port.persistence.RegisterStoryPort;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.StoriesEntity;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.mapper.StoryEntityMapper;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.repository.JpaStoryRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryRepositoryAdapter implements RegisterStoryPort, FindStoryPort, UpdateStoryPort {

    private final JpaStoryRepository storyRepository;
    private final JpaStageRepository stageRepository;
    private final JpaUserRepository userRepository;
    private final StoryEntityMapper mapper;

    @Override
    public Optional<Story> findByIdAndStageId(Long storyId, Long stageId) {
        return storyRepository.findByIdAndStageIdAndDeletedFalse(storyId, stageId).map(mapper::toDomain);
    }

    @Override
    public Story update(Story story) {
        StoriesEntity entity = mapper.toEntity(story);
        return mapper.toDomain(storyRepository.save(entity));
    }

    @Override
    public List<Story> findAll(Long storyId) {
        List<StoriesEntity> stories = storyRepository.findByStageIdAndDeletedFalse(storyId);
        return stories.stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsUser(UUID id) {
        return userRepository.existsByIdAndDeletedFalse(id);
    }

    @Override
    public boolean existsStage(Long stageId) {
        return stageRepository.existsByIdAndDeletedFalse(stageId);
    }

    @Override
    public Story create(Long stageId, Story story) {
        UsersEntity productOwner = Optional.ofNullable(story.getProductOwnerId())
                .flatMap(userRepository::findByIdAndDeletedFalse)
                .orElse(null);

        UsersEntity developer = Optional.ofNullable(story.getDeveloperId())
                .flatMap(userRepository::findByIdAndDeletedFalse)
                .orElse(null);

        StagesEntity stage = stageRepository.findById(stageId).get();
        StoriesEntity entity = mapper.toEntity(story)
                .toBuilder()
                .stage(stage)
                .productOwner(productOwner)
                .developer(developer)
                .build();

        return mapper.toDomain(storyRepository.save(entity));
    }
}
