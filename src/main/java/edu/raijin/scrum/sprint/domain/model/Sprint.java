package edu.raijin.scrum.sprint.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;
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
public class Sprint {

    private UUID id;

    private UUID projectId;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private SprintStatus status = SprintStatus.PENDING;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(name, () -> new BadRequestException("El nombre del sprint es requerido"));
        requireNonNull(startDate, () -> new BadRequestException("La fecha de inicio del sprint es requerida"));
        requireNonNull(endDate, () -> new BadRequestException("La fecha de fin del sprint es requerida"));
        requireNonNull(status, () -> new BadRequestException("El estado del sprint es requerido"));
        if (startDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("La fecha de inicio no puede ser anterior a la fecha actual");
        }
        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }

    public void updateFrom(Sprint updated) {
        this.name = firstNonNull(updated.name, name);
        this.description = firstNonNull(updated.description, description);
        this.startDate = firstNonNull(updated.startDate, startDate);
        this.endDate = firstNonNull(updated.endDate, endDate);
        this.status = firstNonNull(updated.status, status);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
