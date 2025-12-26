package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.port.persistence.UpdateCriteriaPort;
import edu.raijin.scrum.story.domain.usecase.UpdateCriteriaUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCriteriaService implements UpdateCriteriaUseCase {

    private final UpdateCriteriaPort update;

    @Override
    public Criteria update(UUID storyId, Long criteriaId, Criteria criteria) {
        Criteria updated = update.findByIdAndStoryId(criteriaId, storyId)
                .orElseThrow(() -> new ValueNotFoundException("El criterio no se encuentra registrado"));

        updated.updateFrom(criteria);
        updated.checkValidRegistration();

        return update.update(updated);
    }
}