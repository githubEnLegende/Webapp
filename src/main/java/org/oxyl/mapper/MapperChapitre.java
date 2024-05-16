package org.oxyl.mapper;

import org.oxyl.model.Chapitre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class MapperChapitre {

    private final static Logger logger = LoggerFactory.getLogger(MapperChapitre.class);

    public MapperChapitre() {}

    public Optional<Chapitre> rsToChapitre(ResultSet rs)  {
        try{
            return Optional.of(new Chapitre.ChapitreBuilder(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("parent_path")).build());
        } catch (SQLException e) {
            logger.error("Erreur lors du passage de rs à Chapitre", e);
            return Optional.empty();
        }
    }

}
