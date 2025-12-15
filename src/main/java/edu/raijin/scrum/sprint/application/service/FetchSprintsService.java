package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.persistence.FindSprintPort;
import edu.raijin.scrum.sprint.domain.usecase.FetchSprintsUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchSprintsService implements FetchSprintsUseCase {

    private final FindSprintPort find;

    @Override
    public Paged<Sprint> fetchAll(UUID projectId, Pageable pageable) {
        return find.findAll(projectId, pageable);
    }
}