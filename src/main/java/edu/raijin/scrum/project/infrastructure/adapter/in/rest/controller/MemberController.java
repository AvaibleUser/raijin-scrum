package edu.raijin.scrum.project.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.usecase.AddProjectMemberUseCase;
import edu.raijin.scrum.project.domain.usecase.FetchProjectMembersUseCase;
import edu.raijin.scrum.project.domain.usecase.RemoveProjectMemberUseCase;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddMemberDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.user.UserDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper.MemberDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/projects/{projectId}/members")
@RequiredArgsConstructor
public class MemberController {

    private final FetchProjectMembersUseCase fetchAll;
    private final AddProjectMemberUseCase addMember;
    private final RemoveProjectMemberUseCase removeMember;
    private final MemberDtoMapper mapper;

    @GetMapping
    public Paged<UserDto> fetchAll(@PathVariable UUID projectId, Pageable pageable) {
        return fetchAll.fetchAll(projectId, pageable).map(mapper::toDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addMember(@PathVariable UUID projectId, @RequestBody @Valid AddMemberDto project) {
        addMember.add(mapper.toDomain(project, projectId));
    }

    @DeleteMapping("/{userId}")
    public void removeMember(@PathVariable UUID projectId, @PathVariable UUID userId) {
        removeMember.remove(mapper.toDomain(projectId, userId));
    }
}
