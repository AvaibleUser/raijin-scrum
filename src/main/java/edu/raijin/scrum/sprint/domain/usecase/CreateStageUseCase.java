package edu.raijin.scrum.sprint.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Stage;

@UseCase
public interface CreateStageUseCase {

    Stage create(UUID sprintId, Stage stage);
}