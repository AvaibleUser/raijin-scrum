package edu.raijin.scrum.story.application.service;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.UpdateStoryPort;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryStageService implements UpdateStoryStageUseCase {

    private final UpdateStoryPort update;

    @Override
    public Story update(Long stageId, Long storyId, Long newStageId) {
        Story updated = update.findByIdAndStageId(storyId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrado"));

        updated.setStageId(newStageId);
        updated.checkValidRegistration();

        return update.update(updated);
    }
}
