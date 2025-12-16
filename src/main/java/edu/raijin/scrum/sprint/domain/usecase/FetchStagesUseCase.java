package edu.raijin.scrum.sprint.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Stage;

@UseCase
public interface FetchStagesUseCase {

    Paged<Stage> fetchAll(Long sprintId, Pageable pageable);
}