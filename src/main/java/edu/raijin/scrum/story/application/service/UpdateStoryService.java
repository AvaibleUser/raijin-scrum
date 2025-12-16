package edu.raijin.scrum.story.application.service;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryService implements UpdateStoryUseCase {

    private final UpdateStoryPort update;

    @Override
    public Story update(Long stageId, Long storyId, Story story) {
        Story updated = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("El story no se encuentra registrado"));

        updated.updateFrom(story);
        updated.checkValidRegistration();

        return update.update(updated);
    }
}