package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.domain.usecase.CreateStageUseCase;
import edu.raijin.scrum.sprint.domain.usecase.DeleteStageUseCase;
import edu.raijin.scrum.sprint.domain.usecase.FetchStagesUseCase;
import edu.raijin.scrum.sprint.domain.usecase.UpdateStageUseCase;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage.AddStageDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage.StageDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.mapper.StageDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/sprints/{sprintId}/stages")
@RequiredArgsConstructor
public class StageController {

    private final FetchStagesUseCase fetch;
    private final CreateStageUseCase create;
    private final UpdateStageUseCase update;
    private final DeleteStageUseCase delete;
    private final StageDtoMapper mapper;

    @GetMapping
    public Paged<StageDto> fetchAll(Pageable pageable, @PathVariable Long sprintId) {
        return fetch.fetchAll(sprintId, pageable).map(mapper::toDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public StageDto create(@PathVariable Long sprintId, @RequestBody @Valid AddStageDto stage) {
        Stage created = create.create(sprintId, mapper.toDomain(stage));
        return mapper.toDto(created);
    }

    @PutMapping("/{stageId}")
    public StageDto update(@PathVariable Long sprintId, @PathVariable Long stageId, @RequestBody AddStageDto stage) {
        Stage updated = update.update(sprintId, stageId, mapper.toDomain(stage));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{stageId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long sprintId, @PathVariable Long stageId) {
        delete.delete(sprintId, stageId);
    }
}
