package edu.raijin.scrum.project.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.usecase.CreateProjectUseCase;
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

    private final CreateProjectUseCase create;
    private final ProjectDtoMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public ProjectIdDto create(@RequestBody @Valid AddProjectDto project) {
        UUID id = create.create(mapper.toDomain(project));
        return new ProjectIdDto(id);
    }
}
