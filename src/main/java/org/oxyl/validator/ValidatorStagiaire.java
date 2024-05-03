package org.oxyl.validator;

import org.oxyl.dto.StagiaireDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ValidatorStagiaire {

    private static ValidatorStagiaire Instance;

    private ValidatorStagiaire() {
    }

    public static ValidatorStagiaire getInstance() {
        if (Instance == null) {
            Instance = new ValidatorStagiaire();
        }
        return Instance;
    }

    public Map<Integer, String> stagiaireValidator(StagiaireDTO stagiaire) {
        // 0 Erreur Nom, 1 Erreur Prénom, 2 Erreur dates, 3 Erreur Promotion

        Map<Integer, String> stagiaireValidator = new HashMap<>();

        if (!nameValidator(stagiaire.nom())) {
            stagiaireValidator.put(0, "Le nom doit contenir au moins deux lettres et seulement des caractères valides");
        }

        if (!nameValidator(stagiaire.prenom())) {
            stagiaireValidator.put(1, "Le prénom doit contenir au moins deux lettres et seulement des caractères valides");
        }

        LocalDate finFormationDate = null;

        if (stagiaire.finFormation() != null && !stagiaire.finFormation().isEmpty()) {
            finFormationDate = LocalDate.parse(stagiaire.finFormation());
        }

        if (dateValidator(LocalDate.parse(stagiaire.arrival()), finFormationDate)) {
            stagiaireValidator.put(2, "La date d'arrivée ne peut pas être après la date de fin de formation");
        }

        if (!promotionValidator(stagiaire.promotionId())) {
            stagiaireValidator.put(3, "Choisissez une promotion valide");
        }

        return stagiaireValidator;

    }

    public boolean nameValidator(String nom) {
        return nom.matches("^(?U)[\\p{Alpha}\\-'. ]+") && (nom.length() > 2);


    }

    public boolean dateValidator(LocalDate date1, LocalDate date2) {
        if (date2 == null) {
            return false;
        }
        return date2.isBefore(date1);
    }

    public boolean promotionValidator(String promotion) {
        int promotionId = Integer.parseInt(promotion);
        return promotionId != 0;

    }
}


