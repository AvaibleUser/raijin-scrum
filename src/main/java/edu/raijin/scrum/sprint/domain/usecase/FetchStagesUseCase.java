package edu.raijin.scrum.sprint.domain.usecase;

import java.util.List;
import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Stage;

@UseCase
public interface FetchStagesUseCase {

    List<Stage> fetchAll(UUID sprintId);
}