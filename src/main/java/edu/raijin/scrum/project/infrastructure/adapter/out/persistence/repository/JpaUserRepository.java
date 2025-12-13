package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UsersEntity, UUID> {

    Page<UsersEntity> findByDeletedAtIsNull(Pageable request);

    boolean existsByEmail(String email);
}
