package edu.raijin.scrum.project.domain.usecase;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.User;

@UseCase
public interface FetchProjectMembersUseCase {

    Paged<User> fetchAll(UUID projectId, Pageable pageable);
}
