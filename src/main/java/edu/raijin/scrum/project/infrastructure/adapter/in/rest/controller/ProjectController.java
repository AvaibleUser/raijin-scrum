package edu.raijin.scrum.project.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.infrastructure.adapter.rest.dto.scrum.ProjectDto;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.commons.util.annotation.CurrentUser;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.usecase.CloseProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.CreateProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.DeleteProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.FetchProjectUseCase;
import edu.raijin.scrum.project.domain.usecase.FetchProjectsUseCase;
import edu.raijin.scrum.project.domain.usecase.UpdateProjectUseCase;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddProjectDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.ProjectIdDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper.ProjectDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final FetchProjectUseCase fetch;
    private final FetchProjectsUseCase fetchAll;
    private final CreateProjectUseCase create;
    private final UpdateProjectUseCase update;
    private final CloseProjectUseCase close;
    private final DeleteProjectUseCase delete;
    private final ProjectDtoMapper mapper;

    @GetMapping
    public Paged<ProjectDto> fetchAll(Pageable pageable) {
        return fetchAll.fetchAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/me")
    public Paged<ProjectDto> fetchAll(@CurrentUser UUID userId, Pageable pageable) {
        return fetchAll.fetchAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public ProjectDto fetch(@PathVariable UUID id) {
        return mapper.toDto(fetch.fetch(id));
    }

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

    @PatchMapping("/{id}/close")
    public ProjectDto close(@PathVariable UUID id) {
        Project closed = close.close(id);
        return mapper.toDto(closed);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        delete.delete(id);
    }
}
