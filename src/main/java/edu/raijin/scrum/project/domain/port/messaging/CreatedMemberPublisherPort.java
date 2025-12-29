package edu.raijin.scrum.project.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.scrum.project.domain.model.Member;

@Port
public interface CreatedMemberPublisherPort {

    void publishCreatedMember(Member member);
}
