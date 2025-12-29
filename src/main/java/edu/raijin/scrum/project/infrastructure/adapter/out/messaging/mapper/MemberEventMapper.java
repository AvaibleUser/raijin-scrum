package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Member;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface MemberEventMapper {

    MemberEvent toEvent(Member member);
}
