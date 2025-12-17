package edu.raijin.scrum.story.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria.AddCriteriaDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.criteria.CriteriaDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CriteriaDtoMapper {

    Criteria toDomain(AddCriteriaDto criteriaDto);

    CriteriaDto toDto(Criteria criteria);
}
