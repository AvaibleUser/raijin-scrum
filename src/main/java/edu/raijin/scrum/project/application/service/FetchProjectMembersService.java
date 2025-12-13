package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.domain.port.persistence.FindProjectMembersPort;
import edu.raijin.scrum.project.domain.usecase.FetchProjectMembersUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchProjectMembersService implements FetchProjectMembersUseCase {

    private final FindProjectMembersPort findProjectMembers;

    @Override
    public Paged<User> fetchAll(UUID projectId, Pageable pageable) {
        if (!findProjectMembers.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no existe");
        }
        return findProjectMembers.findAll(projectId, pageable);
    }
}
