package edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AddMemberDto(
        @NotNull UUID userId) {
}
