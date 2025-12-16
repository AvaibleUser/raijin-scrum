package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.messaging.CreatedSprintPublisherPort;
import edu.raijin.scrum.sprint.domain.port.persistence.RegisterSprintPort;
import edu.raijin.scrum.sprint.domain.usecase.CreateSprintUseCase;
import edu.raijin.scrum.sprint.domain.usecase.CreateStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSprintService implements CreateSprintUseCase {

    private final RegisterSprintPort registerSprint;
    private final CreateStageUseCase createStage;
    private final CreatedSprintPublisherPort eventPublisher;

    private Stage getDefaultStage(Long sprintId) {
        return Stage.builder()
                .sprintId(sprintId)
                .name("Pendientes")
                .description("Historias pendientes de desarrollo")
                .orderIndex(0)
                .isDefault(true)
                .build();
    }

    private Stage getInDevelopmentStage(Long sprintId) {
        return Stage.builder()
                .sprintId(sprintId)
                .name("En desarrollo")
                .description("Historias en desarrollo")
                .orderIndex(1)
                .isDefault(false)
                .build();
    }

    private Stage getDoneStage(Long sprintId) {
        return Stage.builder()
                .sprintId(sprintId)
                .name("Finalizadas")
                .description("Historias finalizadas")
                .orderIndex(2)
                .isDefault(false)
                .build();
    }

    @Override
    @Transactional
    public Sprint create(UUID projectId, Sprint sprint) {
        if (!registerSprint.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        sprint.checkValidRegistration();

        Sprint created = registerSprint.create(projectId, sprint);
        Long id = created.getId();

        createStage.create(id, getDefaultStage(id));
        createStage.create(id, getInDevelopmentStage(id));
        createStage.create(id, getDoneStage(id));

        eventPublisher.publishCreatedSprint(created);
        return created;
    }
}
