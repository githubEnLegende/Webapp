package org.oxyl.persistence;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.oxyl.mapper.MapperStagiaire;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.oxyl.persistence.StagiaireDAO.afficherPageStagiaire;
import static org.oxyl.persistence.StagiaireDAO.detailStagiaire;

public class InternDAOTest {

    private final H2Config h2Config = H2Config.getInstance();


    @BeforeEach
    public void setup(){
        h2Config.setup();
    }

    @Test
    public void testPageStagiaire(){
        System.setProperty("environment", "test");
        Page<Stagiaire> pageStagiaire = new Page<Stagiaire>();
        afficherPageStagiaire(1, pageStagiaire);

    }

    @Test
    public void testIntern() {
        System.setProperty("environment", "test");
        Stagiaire stagiaire = detailStagiaire(20).get();
        assertNotNull(stagiaire);
        assertEquals(20, stagiaire.getId());
    }

}
