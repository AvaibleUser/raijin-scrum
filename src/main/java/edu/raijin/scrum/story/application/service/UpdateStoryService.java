package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.messaging.UpdatedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryService implements UpdateStoryUseCase {

    private final UpdateStoryPort update;
    private final UpdatedStoryPublisherPort eventPublisher;

    @Override
    @Transactional
    public Story update(Long stageId, UUID storyId, Story story, UUID actorId) {
        Story updated = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrado"));

        updated.updateFrom(story);
        updated.checkValidRegistration();

        Story saved = update.update(updated);

        eventPublisher.publishUpdatedStory(saved, actorId, false);
        return saved;
    }

    @Override
    @Transactional
    public Story update(UUID projectId, UUID storyId, Story story, UUID actorId) {
        return update((Long) null, storyId, story, actorId);
    }
}