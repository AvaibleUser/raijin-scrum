package edu.raijin.scrum.sprint.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteStageUseCase {

    void delete(Long sprintId, Long stageId);
}