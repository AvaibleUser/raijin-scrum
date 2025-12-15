package edu.raijin.scrum.sprint.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@UseCase
public interface FetchSprintsUseCase {

    Paged<Sprint> fetchAll(UUID projectId, Pageable pageable);
}