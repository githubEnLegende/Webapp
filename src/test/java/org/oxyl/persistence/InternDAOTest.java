package org.oxyl.persistence;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.oxyl.persistence.StagiaireDAO.*;

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
        StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);

    }

    @Test
    public void testIntern() {
        var option = StagiaireDAO.getInstance().detailStagiaire(20);
        assertTrue(option.isPresent());
        Stagiaire stagiaire = option.get();

        assertNotNull(stagiaire);
        assertEquals(20, stagiaire.getId());
    }

    @Test
    public void testAddAndDeleteIntern(){
        System.setProperty("environment", "test");
        h2Config.setup();
        Promotion promo = new Promotion.PromotionBuilder(1, "Février 2011").build();
        Stagiaire intern = new Stagiaire.StagiaireBuilder(51,
                "feur",
                "feur",
                null)
                .promotion(promo)
                .formationOver(null).build();

        StagiaireDAO.getInstance().insertIntern(intern);

        Page<Stagiaire> pageStagiaire = new Page<>();
        StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);
        StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);

        System.out.println();

        Stagiaire stagiaire = StagiaireDAO.getInstance().detailStagiaire(51).get();
        assertNotNull(stagiaire);
        assertEquals(51, stagiaire.getId());

        StagiaireDAO.getInstance().deleteIntern(51);
        StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);
        StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);

    }

    @Test
    public void testUpdateIntern(){
        StagiaireDAO.getInstance().updateIntern("Franck", "Alonso", null, null, 1, 50);
        Stagiaire stagiaire = StagiaireDAO.getInstance().detailStagiaire(50).get();


    }

}
