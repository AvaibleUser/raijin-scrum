package edu.raijin.scrum.sprint.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.port.persistence.FindStagePort;
import edu.raijin.scrum.sprint.domain.usecase.FetchStagesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchStageService implements FetchStagesUseCase {

    private final FindStagePort find;

    @Override
    public List<Stage> fetchAll(Long sprintId) {
        return find.findAll(sprintId);
    }
}