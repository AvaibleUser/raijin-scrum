package edu.raijin.scrum.sprint.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteSprintUseCase {

    void delete(UUID projectId, UUID sprintId, UUID actorId);
}