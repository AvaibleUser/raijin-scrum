package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.messaging.DeletedStoryPublisherPort;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.DeleteStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteStoryService implements DeleteStoryUseCase {

    private final UpdateStoryPort update;
    private final DeletedStoryPublisherPort eventPublisher;

    @Override
    @Transactional
    public void delete(Long stageId, UUID storyId, UUID actorId) {
        Story story = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrado"));

        story.delete();
        Story saved = update.update(story);

        eventPublisher.publishDeletedStory(saved, actorId);
    }

    @Override
    @Transactional
    public void delete(UUID projectId, UUID storyId, UUID actorId) {
        delete((Long) null, storyId, actorId);
    }
}