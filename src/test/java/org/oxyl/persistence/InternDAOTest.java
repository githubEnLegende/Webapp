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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.oxyl.persistence.StagiaireDAO.*;
import static org.oxyl.persistence.UtilitairesDAO.getMaxID;

public class InternDAOTest {

    private H2Config h2Config = H2Config.getInstance();


    @BeforeEach
    public void setup(){
        System.setProperty("environment", "test");
        h2Config.setup();
    }

    @Test
    public void testPageStagiaire(){
        Page<Stagiaire> pageStagiaire = new Page<>();
        afficherPageStagiaire(1, pageStagiaire);

    }

    @Test
    public void testIntern() {
        var option = detailStagiaire(20);
        assertTrue(option.isPresent());
        Stagiaire stagiaire = option.get();

        assertNotNull(stagiaire);
        assertEquals(20, stagiaire.getId());
    }

    @Test
    public void testAddIntern(){
        Stagiaire intern = new Stagiaire.StagiaireBuilder(49,
                "feur",
                "feur",
                null,
                4).formationOver(null).build();

        insertIntern(intern);

        Page<Stagiaire> pageStagiaire = new Page<>();
        afficherPageStagiaire(1, pageStagiaire);

        System.out.println();

        Stagiaire stagiaire = detailStagiaire(51).get();
        assertNotNull(stagiaire);
        assertEquals(51, stagiaire.getId());

    }

    @Test
    public void testUpdateIntern(){
        updateIntern("Franck", "Alonso", null, 1, 50);
        Stagiaire stagiaire = detailStagiaire(50).get();


    }

}
