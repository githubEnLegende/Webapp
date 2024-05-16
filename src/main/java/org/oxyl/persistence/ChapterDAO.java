package org.oxyl.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.oxyl.mapper.MapperChapitre;
import org.oxyl.model.Chapitre;
import org.oxyl.model.Stagiaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChapterDAO {

    private static final Logger logger = LoggerFactory.getLogger(ChapterDAO.class);
    private HikariDataSource dataSource;
    private MapperChapitre mapperChapitre;

    public ChapterDAO(HikariDataSource dataSource, MapperChapitre mapperChapitre) {
        this.dataSource = dataSource;
        this.mapperChapitre = mapperChapitre;
    }

    public Optional<List<Chapitre>> getAllChapter() {
        String sql = "SELECT id, name, parent_path FROM chapter";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Chapitre> chapitres = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                chapitres.add(mapperChapitre.rsToChapitre(rs).get());
            }
            return Optional.of(chapitres);
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des stagiaires.", e);
            return Optional.empty();
        }
    }
}
