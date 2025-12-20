package edu.raijin.scrum.story.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.port.persistence.FindStoryPort;
import edu.raijin.scrum.story.domain.usecase.FetchStoriesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchStoriesService implements FetchStoriesUseCase {

    private final FindStoryPort find;

    @Override
    @Transactional
    public List<Story> fetchAll(Long stageId) {
        return find.findAll(stageId);
    }

    @Override
    @Transactional
    public List<Story> fetchAll(UUID projectId) {
        return find.findAll(projectId);
    }
}