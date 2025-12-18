package edu.raijin.scrum.story.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static edu.raijin.commons.util.exception.Exceptions.requirePositive;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.domain.type.StoryPriority;
import edu.raijin.commons.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Story {

    private Long id;

    private Long stageId;

    private UUID productOwnerId;

    private UUID developerId;

    private String name;

    private String description;

    private Integer points;

    private StoryPriority priority;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(this.name, () -> new BadRequestException("El nombre de la historia es requerido"));
        requirePositive(this.points, () -> new BadRequestException("Los puntos de la historia son requeridos"));
        requireNonNull(this.priority, () -> new BadRequestException("La prioridad de la historia es requerida"));
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public void updateFrom(Story updated) {
        this.name = firstNonNull(updated.name, name);
        this.productOwnerId = updated.productOwnerId;
        this.developerId = updated.developerId;
        this.description = firstNonNull(updated.description, description);
        this.points = firstNonNull(updated.points, points);
        this.priority = firstNonNull(updated.priority, priority);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
