package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateStagePort;
import edu.raijin.scrum.sprint.domain.usecase.DeleteStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteStageService implements DeleteStageUseCase {

    private final UpdateStagePort update;

    @Override
    @Transactional
    public void delete(UUID sprintId, Long stageId) {
        Stage deleted = update.findBySprintIdAndId(sprintId, stageId)
                .orElseThrow(() -> new ValueNotFoundException("El stage no se encuentra registrado"));

        deleted.delete();
        update.update(deleted);
    }
}