package edu.raijin.scrum.story.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.FindStoryPort;
import edu.raijin.scrum.story.domain.usecase.FetchStoriesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchStoriesService implements FetchStoriesUseCase {

    private final FindStoryPort find;

    @Override
    public List<Story> fetchAll(Long stageId) {
        return find.findAll(stageId);
    }
}