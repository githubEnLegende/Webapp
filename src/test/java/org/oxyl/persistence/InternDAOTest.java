package org.oxyl.persistence;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.oxyl.context.Context;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class InternDAOTest {

    private final H2Config h2Config = H2Config.getInstance();
    private StagiaireDAO stagiaireDAO = Context.getInstance().getContext().getBean(StagiaireDAO.class);

    @BeforeEach
    public void setup(){
        h2Config.setup();

    }

    @Test
    public void testPageStagiaire(){
        Page<Stagiaire> pageStagiaire = new Page<>();
        stagiaireDAO.getPageStagiaire(pageStagiaire);

    }

    @Test
    public void testIntern() {
        setup();
        var option = stagiaireDAO.detailStagiaire(20);
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

        stagiaireDAO.insertIntern(intern);

        Page<Stagiaire> pageStagiaire = new Page<>();
        stagiaireDAO.getPageStagiaire(pageStagiaire);
        stagiaireDAO.getPageStagiaire(pageStagiaire);

        System.out.println();

        Stagiaire stagiaire = stagiaireDAO.detailStagiaire(51).get();
        assertNotNull(stagiaire);
        assertEquals(51, stagiaire.getId());

        stagiaireDAO.deleteIntern(51);
        stagiaireDAO.getPageStagiaire(pageStagiaire);
        stagiaireDAO.getPageStagiaire(pageStagiaire);

    }

    @Test
    public void testUpdateIntern(){
        stagiaireDAO.updateIntern("Franck", "Alonso", null, null, 1, 50);
        Stagiaire stagiaire = stagiaireDAO.detailStagiaire(50).get();


    }

}
