package org.oxyl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.oxyl.model.Promotion;


public class MapperPromotion {
	public Promotion rsToPromotion(ResultSet rs) {
		try {
			return new Promotion.PromotionBuilder(rs.getInt("id"), rs.getString("name")).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
