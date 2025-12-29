package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.messaging.DeletedSprintPublisherPort;
import edu.raijin.scrum.sprint.domain.port.persistence.UpdateSprintPort;
import edu.raijin.scrum.sprint.domain.usecase.DeleteSprintUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteSprintService implements DeleteSprintUseCase {

    private final UpdateSprintPort update;
    private final DeletedSprintPublisherPort eventPublisher;

    @Override
    @Transactional
    public void delete(UUID projectId, UUID sprintId) {
        Sprint deleted = update.findByIdAndProjectId(sprintId, projectId)
                .orElseThrow(() -> new ValueNotFoundException("El sprint no se encuentra registrado"));

        deleted.delete();
        Sprint deletedSprint = update.update(deleted);

        eventPublisher.publishDeletedSprint(deletedSprint);
    }
}