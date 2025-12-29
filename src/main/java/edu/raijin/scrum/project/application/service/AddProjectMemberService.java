package edu.raijin.scrum.project.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.scrum.project.domain.model.Member;
import edu.raijin.scrum.project.domain.port.messaging.CreatedMemberPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.AddProjectMemberPort;
import edu.raijin.scrum.project.domain.usecase.AddProjectMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddProjectMemberService implements AddProjectMemberUseCase {

    private final AddProjectMemberPort addMember;
    private final CreatedMemberPublisherPort eventPublisher;

    @Override
    @Transactional
    public void add(Member member) {
        if (addMember.exists(member.getProjectId(), member.getUserId())) {
            throw new RequestConflictException("El miembro ya se encuentra registrado en el proyecto");
        }
        member.checkValidRegistration();
        addMember.add(member);

        eventPublisher.publishCreatedMember(member);
    }
}
