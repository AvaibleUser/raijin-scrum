package edu.raijin.scrum.sprint.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.FindStagePort;
import edu.raijin.scrum.sprint.domain.usecase.FetchStagesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchStageService implements FetchStagesUseCase {

    private final FindStagePort find;

    @Override
    public Paged<Stage> fetchAll(Long sprintId, Pageable pageable) {
        return find.findAll(sprintId, pageable);
    }
}