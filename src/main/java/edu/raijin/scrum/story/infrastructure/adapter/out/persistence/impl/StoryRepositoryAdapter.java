package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
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
    private final JpaProjectRepository projectRepository;
    private final JpaUserRepository userRepository;
    private final StoryEntityMapper mapper;

    private UsersEntity getUser(UUID userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findByIdAndDeletedFalse)
                .orElse(null);
    }

    @Override
    public Optional<Story> findByIdAndStageId(Long storyId, Long stageId) {
        return storyRepository.findByIdAndStageIdAndDeletedFalse(storyId, stageId).map(mapper::toDomain);
    }

    @Override
    public Story update(Story story) {
        StoriesEntity entity = mapper.toEntity(story);
        if (entity.getStage().getId() == null) {
            entity.setStage(null);
        }
        return mapper.toDomain(storyRepository.save(entity));
    }

    @Override
    public List<Story> findAll(Long storyId) {
        List<StoriesEntity> stories = storyRepository.findByStageIdAndDeletedFalse(storyId);
        return stories.stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Story> findAll(UUID projectId) {
        List<StoriesEntity> stories = storyRepository.findByProjectIdAndDeletedFalse(projectId);
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
    public boolean existsProject(UUID projectId) {
        return projectRepository.existsByIdAndDeletedFalse(projectId);
    }

    @Override
    public Story create(Long stageId, Story story) {
        UsersEntity productOwner = getUser(story.getProductOwnerId());
        UsersEntity developer = getUser(story.getDeveloperId());

        StagesEntity stage = stageRepository.findById(stageId).get();
        StoriesEntity entity = mapper.toEntity(story)
                .toBuilder()
                .stage(stage)
                .project(stage.getSprint().getProject())
                .productOwner(productOwner)
                .developer(developer)
                .build();

        return mapper.toDomain(storyRepository.save(entity));
    }

    @Override
    public Story create(UUID projectId, Story story) {
        UsersEntity productOwner = getUser(story.getProductOwnerId());
        UsersEntity developer = getUser(story.getDeveloperId());

        ProjectsEntity project = projectRepository.findByIdAndDeletedFalse(projectId).get();
        StoriesEntity entity = mapper.toEntity(story)
                .toBuilder()
                .stage(null)
                .project(project)
                .productOwner(productOwner)
                .developer(developer)
                .build();

        return mapper.toDomain(storyRepository.save(entity));
    }
}
