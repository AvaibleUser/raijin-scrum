package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.usecase.CreateSprintUseCase;
import edu.raijin.scrum.sprint.domain.usecase.DeleteSprintUseCase;
import edu.raijin.scrum.sprint.domain.usecase.FetchSprintsUseCase;
import edu.raijin.scrum.sprint.domain.usecase.UpdateSprintUseCase;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.AddSprintDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.SprintDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.UpdateSprintDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.mapper.SprintDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/projects/{projectId}/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final FetchSprintsUseCase fetch;
    private final CreateSprintUseCase create;
    private final UpdateSprintUseCase update;
    private final DeleteSprintUseCase delete;
    private final SprintDtoMapper mapper;

    @GetMapping
    public Paged<SprintDto> fetchAll(Pageable pageable, @PathVariable UUID projectId) {
        return fetch.fetchAll(projectId, pageable).map(mapper::toDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SprintDto create(@PathVariable UUID projectId, @RequestBody @Valid AddSprintDto sprint) {
        Sprint created = create.create(projectId, mapper.toDomain(sprint));
        return mapper.toDto(created);
    }

    @PutMapping("/{sprintId}")
    public SprintDto update(@PathVariable UUID projectId, @PathVariable Long sprintId,
            @RequestBody @Valid UpdateSprintDto sprint) {
        Sprint updated = update.update(projectId, sprintId, mapper.toDomain(sprint));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{sprintId}")
    public void delete(@PathVariable UUID projectId, @PathVariable Long sprintId) {
        delete.delete(projectId, sprintId);
    }
}
