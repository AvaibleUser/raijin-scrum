package edu.raijin.scrum.project.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.scrum.project.domain.model.Member;

@UseCase
public interface AddProjectMemberUseCase {

    void add(Member member);
}
