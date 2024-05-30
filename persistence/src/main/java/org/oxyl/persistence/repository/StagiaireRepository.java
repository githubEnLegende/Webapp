package org.oxyl.persistence.repository;

import org.oxyl.persistence.entities.InternEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiaireRepository extends JpaRepository<InternEntity, Long> {
    @Query("SELECT i FROM InternEntity i JOIN FETCH i.promotion WHERE i.firstName LIKE %:name% OR i.lastName LIKE %:name%")
    Page<InternEntity> findAllByFirstNameOrLastNameContainsIgnoreCaseWithPromotion(@Param("name") String name, Pageable pageable);

    @Query("select i from InternEntity i join fetch i.promotion")
    Page<InternEntity> findAll(Pageable pageable);

    @Query("SELECT COUNT(*) FROM InternEntity i WHERE i.firstName LIKE %:name% OR i.lastName LIKE %:name% ")
    long countByFirstNameOrLastNameContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT MAX(i.id) FROM InternEntity i")
    long findMaxId();
}
