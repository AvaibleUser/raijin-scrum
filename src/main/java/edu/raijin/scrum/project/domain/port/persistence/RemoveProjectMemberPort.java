package edu.raijin.scrum.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Member;

@Port
public interface RemoveProjectMemberPort {

    boolean exists(UUID projectId, UUID userId);

    void remove(Member member);
}
