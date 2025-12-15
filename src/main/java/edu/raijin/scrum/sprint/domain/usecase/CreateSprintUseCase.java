package edu.raijin.scrum.sprint.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@UseCase
public interface CreateSprintUseCase {

    Sprint create(UUID projectId, Sprint sprint);
}
