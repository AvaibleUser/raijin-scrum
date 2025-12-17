package edu.raijin.scrum.story.application.service;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.domain.port.persistence.RegisterCriteriaPort;
import edu.raijin.scrum.story.domain.usecase.CreateCriteriaUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateCriteriaService implements CreateCriteriaUseCase {

    private final RegisterCriteriaPort register;

    @Override
    public Criteria create(Long storyId, Criteria criteria) {
        if (!register.existsStory(storyId)) {
            throw new ValueNotFoundException("La historia no se encuentra registrado");
        }
        criteria.checkValidRegistration();

        Criteria saved = register.create(storyId, criteria);
        return saved;
    }
}