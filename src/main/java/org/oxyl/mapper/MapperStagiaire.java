package org.oxyl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.oxyl.model.Stagiaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MapperStagiaire {

	private static Logger logger = LoggerFactory.getLogger(MapperStagiaire.class);

	public Optional<Stagiaire> rsToStagiaire(ResultSet rs) {
		try {
				return Optional.of(new Stagiaire.StagiaireBuilder(rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getTimestamp("arrival")
								== null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate())
						.formationOver(rs.getTimestamp("formation_over")
								== null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate())
						.promotion(rs.getInt("promotion_id"))

						.build());
		} catch (SQLException e) {
			logger.error("Erreur Base de donnée", e);
			return Optional.empty();
		}
	}
}
