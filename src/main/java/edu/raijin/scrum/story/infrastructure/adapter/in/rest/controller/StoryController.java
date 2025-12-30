package edu.raijin.scrum.story.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.usecase.CreateStoryUseCase;
import edu.raijin.scrum.story.domain.usecase.DeleteStoryUseCase;
import edu.raijin.scrum.story.domain.usecase.FetchStoriesUseCase;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryStageUseCase;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryUseCase;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.AddStoryDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.ChangeStorySprintDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.ChangeStoryStageDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.StoryDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.mapper.StoryDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequiredArgsConstructor
public class StoryController {

    private final FetchStoriesUseCase fetch;
    private final CreateStoryUseCase create;
    private final UpdateStoryUseCase update;
    private final UpdateStoryStageUseCase updateStage;
    private final DeleteStoryUseCase delete;
    private final StoryDtoMapper mapper;

    @GetMapping("/stages/{stageId}/stories")
    public List<StoryDto> fetchAll(@PathVariable Long stageId) {
        return fetch.fetchAll(stageId).stream().map(mapper::toDto).toList();
    }

    @GetMapping("/projects/{projectId}/stories")
    public List<StoryDto> fetchAll(@PathVariable UUID projectId) {
        return fetch.fetchAll(projectId).stream().map(mapper::toDto).toList();
    }

    @GetMapping("/sprints/{sprintId}/stories")
    public List<StoryDto> fetchAllBySprint(@PathVariable UUID sprintId) {
        return fetch.fetchAllBySprint(sprintId).stream().map(mapper::toDto).toList();
    }

    @PostMapping("/stages/{stageId}/stories")
    @ResponseStatus(CREATED)
    public StoryDto create(@PathVariable Long stageId, @RequestBody @Valid AddStoryDto story) {
        Story created = create.create(stageId, mapper.toDomain(story));
        return mapper.toDto(created);
    }

    @PostMapping("/projects/{projectId}/stories")
    @ResponseStatus(CREATED)
    public StoryDto create(@PathVariable UUID projectId, @RequestBody @Valid AddStoryDto story) {
        Story created = create.create(projectId, mapper.toDomain(story));
        return mapper.toDto(created);
    }

    @PutMapping("/stages/{stageId}/stories/{storyId}")
    public StoryDto update(@PathVariable Long stageId, @PathVariable UUID storyId, @RequestBody AddStoryDto story) {
        Story updated = update.update(stageId, storyId, mapper.toDomain(story));
        return mapper.toDto(updated);
    }

    @PutMapping("/projects/{projectId}/stories/{storyId}")
    public StoryDto update(@PathVariable UUID projectId, @PathVariable UUID storyId, @RequestBody AddStoryDto story) {
        Story updated = update.update(projectId, storyId, mapper.toDomain(story));
        return mapper.toDto(updated);
    }

    @PatchMapping("/stages/{stageId}/stories/{storyId}/stage")
    public StoryDto update(@PathVariable Long stageId, @PathVariable UUID storyId,
            @RequestBody ChangeStoryStageDto story) {
        Story updated = updateStage.update(stageId, storyId, story.stageId());
        return mapper.toDto(updated);
    }

    @PatchMapping("/projects/{projectId}/stories/{storyId}/stage")
    public StoryDto update(@PathVariable UUID projectId, @PathVariable UUID storyId,
            @RequestBody ChangeStoryStageDto story) {
        Story updated = updateStage.update(projectId, storyId, story.stageId());
        return mapper.toDto(updated);
    }

    @PatchMapping("/projects/{projectId}/stories/{storyId}/sprint")
    public StoryDto update(@PathVariable UUID projectId, @PathVariable UUID storyId,
            @RequestBody ChangeStorySprintDto story) {
        Story updated = updateStage.update(story.sprintId(), storyId);
        return mapper.toDto(updated);
    }

    @DeleteMapping("/stages/{stageId}/stories/{storyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long stageId, @PathVariable UUID storyId) {
        delete.delete(stageId, storyId);
    }

    @DeleteMapping("/projects/{projectId}/stories/{storyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID projectId, @PathVariable UUID storyId) {
        delete.delete(projectId, storyId);
    }
}
