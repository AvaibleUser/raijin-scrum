package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;

@Repository
public interface JpaProjectRepository extends JpaRepository<ProjectsEntity, UUID> {

}
