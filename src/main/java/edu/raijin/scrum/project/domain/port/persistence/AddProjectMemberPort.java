package edu.raijin.scrum.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Member;

@Port
public interface AddProjectMemberPort {

    boolean exists(UUID projectId, UUID userId);

    void add(Member member);
}
