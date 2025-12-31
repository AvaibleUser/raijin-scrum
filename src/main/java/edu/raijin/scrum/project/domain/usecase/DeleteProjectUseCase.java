package edu.raijin.scrum.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteProjectUseCase {

    void delete(UUID projectId, UUID actorId);
}
