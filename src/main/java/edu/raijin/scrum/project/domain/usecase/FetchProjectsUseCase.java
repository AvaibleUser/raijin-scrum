package edu.raijin.scrum.project.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.Project;

@UseCase
public interface FetchProjectsUseCase {

    Paged<Project> fetchAll(Pageable pageable);
}
