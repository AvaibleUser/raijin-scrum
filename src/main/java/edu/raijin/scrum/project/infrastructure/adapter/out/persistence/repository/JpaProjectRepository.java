package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;

@Repository
public interface JpaProjectRepository extends JpaRepository<ProjectsEntity, UUID> {

    Optional<ProjectsEntity> findByIdAndDeletedFalse(UUID id);

    Page<ProjectsEntity> findAllByDeletedFalse(Pageable request);

    boolean existsByIdAndDeletedFalse(UUID id);

    boolean existsByIdAndMembersIdAndDeletedFalse(UUID projectId, UUID userId);
}
