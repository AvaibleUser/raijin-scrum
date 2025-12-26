package edu.raijin.scrum.story.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.port.persistence.FindCriteriaPort;
import edu.raijin.scrum.story.domain.usecase.FetchCriteriaUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchCriteriaService implements FetchCriteriaUseCase {

    private final FindCriteriaPort find;

    @Override
    public List<Criteria> fetchAll(UUID storyId) {
        return find.findAll(storyId);
    }
}