package edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story;

import org.hibernate.validator.constraints.UUID;

import edu.raijin.commons.domain.type.StoryPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddStoryDto(
        @UUID(message = "El id del propietario del producto es requerido") String productOwnerId,
        @UUID(message = "El id del desarrollador es requerido") String developerId,
        @NotBlank(message = "El nombre de la historia es requerido") String name,
        String description,
        Integer points,
        @NotNull(message = "La prioridad de la historia es requerida") StoryPriority priority) {
}
