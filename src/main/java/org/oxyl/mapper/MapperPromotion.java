package org.oxyl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.oxyl.model.Promotion;

import javax.swing.text.html.Option;


public class MapperPromotion {
	public Optional<Promotion> rsToPromotion(ResultSet rs) {
		try {
			return Optional.of(new Promotion.PromotionBuilder(rs.getInt("id"),
					rs.getString("name")).build());
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
