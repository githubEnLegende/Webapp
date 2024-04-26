package org.oxyl.model;

import junit.framework.TestCase;

import java.time.LocalDate;

public class StagiaireTest extends TestCase {

    public void testCreationStagiaire() {
        Promotion promo = new Promotion.PromotionBuilder(1, "Février 2011").build();
        // Création d'un Stagiaire avec des valeurs valides
        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(1, "John", "Doe",
                LocalDate.of(2022, 1, 1))
                .promotion(promo)
                .formationOver(LocalDate.of(2022, 12, 31))
                .build();

        // Vérification des valeurs assignées
        assertEquals(1, stagiaire.getId());
        assertEquals("John", stagiaire.getFirstName());
        assertEquals("Doe", stagiaire.getLastName());
        assertEquals(LocalDate.of(2022, 1, 1), stagiaire.getArrival());
        assertEquals(LocalDate.of(2022, 12, 31), stagiaire.getFormationOver());
        assertEquals(1, stagiaire.getPromotion().getId());
    }

    public void testPromotionIdTropEleve() {
        // Création d'un Stagiaire avec un promotionId trop élevé
        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(1, "John", "Doe",
                LocalDate.of(2022, 1, 1))
                .promotion(100)
                .formationOver(LocalDate.of(2022, 12, 31))
                .build();

        // Vérification que le promotionId est fixé à 47
        assertEquals(47, stagiaire.getPromotion().getId());
    }

    public void testPromotionIdTropBas() {
        // Création d'un Stagiaire avec un promotionId trop élevé
        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(1, "John", "Doe",
                LocalDate.of(2022, 1, 1))
                .promotion(-10)
                .formationOver(LocalDate.of(2022, 12, 31))
                .build();

        // Vérification que le promotionId est fixé à 47
        assertEquals(1, stagiaire.getPromotion().getId());
    }



}