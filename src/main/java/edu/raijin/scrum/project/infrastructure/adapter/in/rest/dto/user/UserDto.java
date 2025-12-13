package edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.user;

import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String role,
        String color) {
}
