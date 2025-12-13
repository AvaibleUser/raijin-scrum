package edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Member;
import edu.raijin.scrum.project.domain.model.User;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddMemberDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.user.UserDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDtoMapper {

    Member toDomain(AddMemberDto dto, UUID projectId);

    Member toDomain(UUID projectId, UUID userId);

    UserDto toDto(User user);
}
