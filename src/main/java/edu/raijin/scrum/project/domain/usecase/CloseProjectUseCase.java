package edu.raijin.scrum.project.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.Project;

@UseCase
public interface CloseProjectUseCase {

    Project close(UUID projectId);
}
