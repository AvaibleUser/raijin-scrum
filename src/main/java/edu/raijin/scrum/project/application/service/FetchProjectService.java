package edu.raijin.scrum.project.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.persistence.FindProjectPort;
import edu.raijin.scrum.project.domain.usecase.FetchProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.FetchProjectsUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchProjectService implements FetchProjectUseCase, FetchProjectsUseCase {

    private final FindProjectPort findProject;

    @Override
    public Project fetch(UUID projectId) {
        return findProject.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));
    }

    @Override
    public List<Project> fetchAll() {
        return findProject.findAll();
    }
}
