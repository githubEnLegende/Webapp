package org.oxyl.persistence;

import org.oxyl.mapper.MapperChapitre;
import org.oxyl.model.Chapitre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class ChapterDAO {

    private static final Logger logger = LoggerFactory.getLogger(ChapterDAO.class);
    private MapperChapitre mapperChapitre;
    private final JdbcTemplate jdbcTemplate;


    public ChapterDAO(MapperChapitre mapperChapitre, JdbcTemplate jdbcTemplate) {
        this.mapperChapitre = mapperChapitre;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Chapitre>> getAllChapter() {
        String sql = "SELECT id, name, parent_path FROM chapter";
        try {
            List<Chapitre> chapitres = jdbcTemplate.query(sql, mapperChapitre);
            return Optional.of(chapitres);
        } catch (DataAccessException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }
}
