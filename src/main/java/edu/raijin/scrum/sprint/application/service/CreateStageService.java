package edu.raijin.scrum.sprint.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.RegisterStagePort;
import edu.raijin.scrum.sprint.domain.usecase.CreateStageUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateStageService implements CreateStageUseCase {

    private final RegisterStagePort register;

    @Override
    @Transactional
    public Stage create(Long sprintId, Stage stage) {
        if (!register.existsSprint(sprintId)) {
            throw new ValueNotFoundException("El sprint no se encuentra registrado");
        }
        if (stage.getIsDefault() && register.existsAnotherDefault(sprintId)) {
            throw new RequestConflictException("El sprint ya tiene un stage por defecto");
        }
        stage.checkValidRegistration();

        Stage saved = register.create(sprintId, stage);
        return saved;
    }
}