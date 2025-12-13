package edu.raijin.scrum.project.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.scrum.project.domain.model.Member;
import edu.raijin.scrum.project.domain.port.persistence.RemoveProjectMemberPort;
import edu.raijin.scrum.project.domain.usecase.RemoveProjectMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveProjectMemberService implements RemoveProjectMemberUseCase {

    private final RemoveProjectMemberPort removeMember;

    @Override
    @Transactional
    public void remove(Member member) {
        if (!removeMember.exists(member.getProjectId(), member.getUserId())) {
            return;
        }
        removeMember.remove(member);
    }
}
