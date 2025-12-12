package edu.raijin.scrum.project.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.usecase.CreateProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.UpdateProjectUseCase;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddProjectDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.ProjectDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.ProjectIdDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper.ProjectDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final CreateProjectUseCase create;
    private final UpdateProjectUseCase update;
    private final ProjectDtoMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public ProjectIdDto create(@RequestBody @Valid AddProjectDto project) {
        UUID id = create.create(mapper.toDomain(project));
        return new ProjectIdDto(id);
    }

    @PutMapping("/{id}")
    public ProjectDto update(@PathVariable UUID id, @RequestBody AddProjectDto project) {
        Project updated = update.update(id, mapper.toDomain(project));
        return mapper.toDto(updated);
    }
}
