package edu.raijin.scrum.sprint.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Stage;

@UseCase
public interface UpdateStageUseCase {

    Stage update(Long sprintId, Long stageId, Stage stage);
}