package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.type.SprintStatus;
import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateSprintPort;
import edu.raijin.scrum.sprint.domain.usecase.UpdateSprintUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateSprintService implements UpdateSprintUseCase {

    private final UpdateSprintPort update;

    @Override
    @Transactional
    public Sprint update(UUID projectId, Long sprintId, Sprint sprint) {
        Sprint updated = update.findByIdAndProjectId(sprintId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El sprint no se encuentra registrado"));

        if (sprint.getStatus() == SprintStatus.ACTIVE && update.existsAnotherActive(projectId, sprintId)) {
            throw new RequestConflictException("El proyecto ya tiene un sprint activo");
        }

        updated.updateFrom(sprint);
        updated.checkValidRegistration();

        return update.update(updated);
    }
}
