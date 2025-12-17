package edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria;

import jakarta.validation.constraints.NotBlank;

public record AddCriteriaDto(
        @NotBlank(message = "La descripción del criterio de aceptación es requerida") String description,
        Boolean reached,
        Boolean required) {
}
