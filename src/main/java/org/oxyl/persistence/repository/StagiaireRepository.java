package org.oxyl.persistence.repository;

import org.oxyl.persistence.entities.InternEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<InternEntity, Integer> {
    //Page<InternEntity> findAll(String statement, Pageable pageable);
}
