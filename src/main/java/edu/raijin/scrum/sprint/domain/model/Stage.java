package edu.raijin.scrum.sprint.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.util.UUID;

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
public class Stage {

    private Long id;

    private UUID sprintId;

    private String name;

    private String description;

    private Integer orderIndex;

    @Builder.Default
    private Boolean isDefault = false;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(this.name, () -> new BadRequestException("El nombre de la etapa es requerido"));
        requireNonNull(this.orderIndex, () -> new BadRequestException("El orden de la etapa es requerido"));
    }

    public void updateFrom(Stage updated) {
        this.name = firstNonNull(updated.name, name);
        this.description = firstNonNull(updated.description, description);
        this.orderIndex = firstNonNull(updated.orderIndex, orderIndex);
        this.isDefault = firstNonNull(updated.isDefault, isDefault);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
