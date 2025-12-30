package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
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
    @Transactional
    public Project fetch(UUID projectId) {
        return findProject.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));
    }

    @Override
    @Transactional
    public Paged<Project> fetchAll(Pageable pageable) {
        return findProject.findAll(pageable);
    }

    @Override
    @Transactional
    public Paged<Project> fetchAllAssigned(UUID userId, Pageable pageable) {
        return findProject.findAllAssigned(userId, pageable);
    }
}
