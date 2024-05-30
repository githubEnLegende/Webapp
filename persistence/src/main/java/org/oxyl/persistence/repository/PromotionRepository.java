package org.oxyl.persistence.repository;

import org.oxyl.persistence.entities.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
}
