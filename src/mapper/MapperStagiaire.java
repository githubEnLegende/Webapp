package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Optional;

import model.Stagiaire;


public class MapperStagiaire {
	public Stagiaire rsToStagiaire(ResultSet rs) {
		try {
			return new Stagiaire.StagiaireBuilder(rs.getInt("id"),
												rs.getString("first_name"),
												rs.getString("last_name"),
												rs.getTimestamp("arrival")
												== null ? null : rs.getTimestamp("arrival").toLocalDateTime().toLocalDate(),
												rs.getInt("promotion_id")).formationOver(rs.getTimestamp("formation_over") 
												== null ? null : rs.getTimestamp("formation_over").toLocalDateTime().toLocalDate()).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
