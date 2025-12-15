package edu.raijin.scrum.sprint.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@UseCase
public interface UpdateSprintUseCase {

    Sprint update(UUID projectId, Long sprintId, Sprint sprint);
}