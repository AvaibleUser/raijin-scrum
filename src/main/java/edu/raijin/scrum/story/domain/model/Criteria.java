package edu.raijin.scrum.story.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;

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
public class Criteria {

    private Long id;

    private Long storyId;

    private String description;

    @Builder.Default
    private Boolean reached = false;

    @Builder.Default
    private Boolean required = false;

    @Builder.Default
    private Boolean deleted = false;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    public void checkValidRegistration() {
        requireNonBlank(description, () -> new BadRequestException("La descripción es requerida"));
    }

    public void updateFrom(Criteria updated) {
        this.description = firstNonNull(updated.description, description);
        this.reached = firstNonNull(updated.reached, reached, false);
        this.required = firstNonNull(updated.required, required, false);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
