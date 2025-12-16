package edu.raijin.scrum.sprint.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateStagePort;
import edu.raijin.scrum.sprint.domain.usecase.UpdateStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStageService implements UpdateStageUseCase {

    private final UpdateStagePort update;

    @Override
    @Transactional
    public Stage update(Long sprintId, Long stageId, Stage stage) {
        Stage updated = update.findBySprintIdAndId(sprintId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("El stage no se encuentra registrado"));

        updated.updateFrom(stage);
        if (updated.getIsDefault() && update.existsAnotherDefault(sprintId, stageId)) {
            throw new RequestConflictException("El sprint ya tiene un stage por defecto");
        }
        updated.checkValidRegistration();

        return update.update(updated);
    }
}