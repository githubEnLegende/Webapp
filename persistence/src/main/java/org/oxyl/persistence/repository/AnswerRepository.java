package org.oxyl.persistence.repository;

import org.oxyl.persistence.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM AnswerEntity a Where a.question.id = :id")
    void deleteByQuestionId(@Param("id") long id);
}
