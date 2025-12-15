package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

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
    public Sprint update(UUID projectId, Long sprintId, Sprint sprint) {
        Sprint updated = update.findByIdAndProjectId(sprintId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El sprint no se encuentra registrado"));

        if (update.existsActiveSprint(projectId)) {
            throw new RequestConflictException("El proyecto tiene un sprint activo");
        }

        updated.updateFrom(sprint);
        updated.checkValidRegistration();

        return update.update(updated);
    }
}
