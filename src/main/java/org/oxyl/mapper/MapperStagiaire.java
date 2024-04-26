package org.oxyl.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.oxyl.mapper.MapperPromotion;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.MySqlConnexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.oxyl.persistence.PromotionDAO.getPromotion;


public class MapperStagiaire {

	private static Logger logger = LoggerFactory.getLogger(MapperStagiaire.class);

	public Optional<Stagiaire> rsToStagiaire(ResultSet rs) {
		Promotion promo = null;
		try {
				return Optional.of(new Stagiaire.StagiaireBuilder(rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getTimestamp("arrival")
								== null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate(),
						rs.getInt("promotion_id")).formationOver(rs.getTimestamp("formation_over")
						== null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate()).build());
		} catch (SQLException e) {
			logger.error("Erreur Base de donnée", e.getMessage());
			return Optional.empty();
		}
	}
}
