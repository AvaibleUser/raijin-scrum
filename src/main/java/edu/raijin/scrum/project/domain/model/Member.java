package edu.raijin.scrum.project.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

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
public class Member {

    private UUID projectId;

    private UUID userId;

    public void checkValidRegistration() {
        requireNonNull(projectId, () -> new BadRequestException("El proyecto es requerido"));
        requireNonNull(userId, () -> new BadRequestException("El usuario es requerido"));
    }
}
