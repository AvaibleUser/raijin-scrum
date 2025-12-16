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
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.domain.usecase.CreateStoryUseCase;
import edu.raijin.scrum.story.domain.usecase.DeleteStoryUseCase;
import edu.raijin.scrum.story.domain.usecase.FetchStoriesUseCase;
import edu.raijin.scrum.story.domain.usecase.UpdateStoryUseCase;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.AddStoryDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.StoryDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.mapper.StoryDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping({ "/stages/{stageId}/stories", "/stories" })
@RequiredArgsConstructor
public class StoryController {

    private final FetchStoriesUseCase fetch;
    private final CreateStoryUseCase create;
    private final UpdateStoryUseCase update;
    private final DeleteStoryUseCase delete;
    private final StoryDtoMapper mapper;

    @GetMapping
    public List<StoryDto> fetchAll(@PathVariable(required = false) Long stageId) {
        return fetch.fetchAll(stageId).stream().map(mapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public StoryDto create(@PathVariable(required = false) Long stageId, @RequestBody @Valid AddStoryDto story) {
        Story created = create.create(stageId, mapper.toDomain(story));
        return mapper.toDto(created);
    }

    @PutMapping("/{storyId}")
    public StoryDto update(@PathVariable(required = false) Long stageId, @PathVariable(required = false) Long storyId,
            @RequestBody AddStoryDto story) {
        Story updated = update.update(stageId, storyId, mapper.toDomain(story));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{storyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable(required = false) Long stageId, @PathVariable(required = false) Long storyId) {
        delete.delete(stageId, storyId);
    }
}
