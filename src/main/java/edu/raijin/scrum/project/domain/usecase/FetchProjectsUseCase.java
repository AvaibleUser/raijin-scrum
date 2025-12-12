package edu.raijin.scrum.project.domain.usecase;

import java.util.List;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.Project;

@UseCase
public interface FetchProjectsUseCase {

    List<Project> fetchAll();
}
