package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.DeleteStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteStoryService implements DeleteStoryUseCase {

    private final UpdateStoryPort update;

    @Override
    @Transactional
    public void delete(Long stageId, UUID storyId) {
        Story story = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrado"));

        story.delete();
        update.update(story);
    }

    @Override
    @Transactional
    public void delete(UUID projectId, UUID storyId) {
        delete((Long) null, storyId);
    }
}