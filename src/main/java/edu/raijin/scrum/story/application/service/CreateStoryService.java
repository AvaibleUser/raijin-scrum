package edu.raijin.scrum.story.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.RegisterStoryPort;
import edu.raijin.scrum.story.domain.usecase.CreateStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateStoryService implements CreateStoryUseCase {

    private final RegisterStoryPort register;

    @Override
    @Transactional
    public Story create(Long stageId, Story story) {
        if (!register.existsStage(stageId)) {
            throw new ValueNotFoundException("El stage no se encuentra registrado");
        }
        if (story.getProductOwnerId() != null && !register.existsUser(story.getProductOwnerId())) {
            throw new ValueNotFoundException("El product owner no se encuentra registrado");
        }
        if (story.getDeveloperId() != null && !register.existsUser(story.getDeveloperId())) {
            throw new ValueNotFoundException("El desarrollador no se encuentra registrado");
        }
        story.checkValidRegistration();

        Story saved = register.create(stageId, story);
        return saved;
    }
}