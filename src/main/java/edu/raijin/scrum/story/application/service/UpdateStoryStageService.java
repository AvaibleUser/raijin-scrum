package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.messaging.UpdatedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryStageService implements UpdateStoryStageUseCase {

    private final UpdateStoryPort update;
    private final UpdatedStoryPublisherPort eventPublisher;

    @Override
    @Transactional
    public Story update(Long stageId, UUID storyId, Long newStageId) {
        Story updated = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrada"));

        updated.setStageId(newStageId);
        updated.checkValidRegistration();

        Story saved = update.update(updated);

        eventPublisher.publishUpdatedStory(saved);
        return saved;
    }

    @Override
    @Transactional
    public Story update(UUID projectId, UUID storyId, Long newStageId) {
        return update((Long) null, storyId, newStageId);
    }
}
