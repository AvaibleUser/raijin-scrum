package edu.raijin.scrum.story.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.usecase.CreateCriteriaUseCase;
import edu.raijin.scrum.story.domain.usecase.DeleteCriteriaUseCase;
import edu.raijin.scrum.story.domain.usecase.FetchCriteriaUseCase;
import edu.raijin.scrum.story.domain.usecase.UpdateCriteriaUseCase;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria.AddCriteriaDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria.CriteriaDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.mapper.CriteriaDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/stories/{storyId}/criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final FetchCriteriaUseCase fetch;
    private final CreateCriteriaUseCase create;
    private final UpdateCriteriaUseCase update;
    private final DeleteCriteriaUseCase delete;
    private final CriteriaDtoMapper mapper;

    @GetMapping
    public List<CriteriaDto> fetchAll(@PathVariable Long storyId) {
        return fetch.fetchAll(storyId).stream().map(mapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CriteriaDto create(@PathVariable Long storyId, @RequestBody @Valid AddCriteriaDto criteria) {
        Criteria created = create.create(storyId, mapper.toDomain(criteria));
        return mapper.toDto(created);
    }

    @PutMapping("/{criteriaId}")
    public CriteriaDto update(@PathVariable Long storyId, @PathVariable Long criteriaId,
            @RequestBody AddCriteriaDto criteria) {
        Criteria updated = update.update(storyId, criteriaId, mapper.toDomain(criteria));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{criteriaId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long storyId, @PathVariable Long criteriaId) {
        delete.delete(storyId, criteriaId);
    }
}
