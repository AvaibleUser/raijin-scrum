package edu.raijin.scrum.story.application.service;

import org.springframework.stereotype.Service;

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
    public void delete(Long stageId, Long storyId) {
        Story story = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("El story no se encuentra registrado"));

        story.delete();
        update.update(story);
    }
}