package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Criteria;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.CriteriaEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CriteriaEntityMapper {

    @Mapping(target = "storyId", source = "story.id")
    Criteria toDomain(CriteriaEntity entity);

    @Mapping(target = "story.id", source = "storyId")
    CriteriaEntity toEntity(Criteria Criteria);
}
