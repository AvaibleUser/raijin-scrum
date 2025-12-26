package edu.raijin.scrum.story.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.port.persistence.UpdateCriteriaPort;
import edu.raijin.scrum.story.domain.usecase.DeleteCriteriaUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteCriteriaService implements DeleteCriteriaUseCase {

    private final UpdateCriteriaPort update;

    @Override
    public void delete(UUID storyId, Long criteriaId) {
        Criteria criteria = update.findByIdAndStoryId(criteriaId, storyId)
                .orElseThrow(() -> new ValueNotFoundException("El criterio no se encuentra registrado"));

        criteria.delete();
        update.update(criteria);
    }
}