package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.messaging.CreatedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.persistence.RegisterStoryPort;
import edu.raijin.scrum.story.domain.usecase.CreateStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateStoryService implements CreateStoryUseCase {

    private final RegisterStoryPort register;
    private final CreatedStoryPublisherPort eventPublisher;

    @Override
    @Transactional
    public Story create(Long stageId, Story story, UUID actorId) {
        if (!register.existsStage(stageId)) {
            throw new ValueNotFoundException("La columna no se encuentra registrada");
        }
        if (story.getProductOwnerId() != null && !register.existsUser(story.getProductOwnerId())) {
            throw new ValueNotFoundException("El product owner no se encuentra registrado");
        }
        if (story.getDeveloperId() != null && !register.existsUser(story.getDeveloperId())) {
            throw new ValueNotFoundException("El desarrollador no se encuentra registrado");
        }
        story.checkValidRegistration();

        Story saved = register.create(stageId, story);

        eventPublisher.publishCreatedStory(saved, actorId);
        return saved;
    }

    @Override
    @Transactional
    public Story create(UUID projectId, Story story, UUID actorId) {
        if (!register.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        if (story.getProductOwnerId() != null && !register.existsUser(story.getProductOwnerId())) {
            throw new ValueNotFoundException("El product owner no se encuentra registrado");
        }
        if (story.getDeveloperId() != null && !register.existsUser(story.getDeveloperId())) {
            throw new ValueNotFoundException("El desarrollador no se encuentra registrado");
        }
        story.checkValidRegistration();

        Story saved = register.create(projectId, story);

        eventPublisher.publishCreatedStory(saved, actorId);
        return saved;
    }
}