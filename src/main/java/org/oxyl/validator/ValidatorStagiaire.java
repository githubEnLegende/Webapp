package org.oxyl.validator;

import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorStagiaire {

    private Map<Integer, List<String>> stagiaireValidator = new HashMap<>();
    private boolean valide = true;

    public ValidatorStagiaire(Stagiaire stagiaire){
        // 0 Erreur Nom, 1 Erreur Prénom, 2 Erreur dates, 3 Erreur Promotion

        stagiaireValidator.put(0, nameValidator(stagiaire.getLastName()));
        stagiaireValidator.put(1, nameValidator(stagiaire.getFirstName()));
        stagiaireValidator.put(2, dateValidator(stagiaire.getArrival(), stagiaire.getFormationOver()));
        if(stagiaire.getPromotion() == null){
            List<String> stringPromotion = new ArrayList<>();
            stringPromotion.add("Choisissez une promotion valide");
            stagiaireValidator.put(3,  stringPromotion);
        }else{
            stagiaireValidator.put(3, promotionValidator(stagiaire.getPromotion().getId()));
        }

    }

    public boolean isValide() {
        return valide;
    }

    public Map<Integer, List<String>> getStagiaireValidator() {
        return stagiaireValidator;
    }

    public List<String> nameValidator(String nom){
        List<String> nameValidator = new ArrayList<>();

        if (!nom.matches("^(?U)[\\p{Alpha}\\-'. ]+")) {
            nameValidator.add("Le nom et prénom ne peuvent pas contenir de caractères interdits");
            this.valide = false;
        }

        if (nom.length()<2){
            nameValidator.add("Le nom et prénom doivent contenir 2 lettres ou plus");
            this.valide = false;
        }

        return nameValidator;
    }

    public List<String> dateValidator(LocalDate date1, LocalDate date2){

        List<String> dateValidator = new ArrayList<>();

        if (date2 != null && date2.isBefore(date1)){
            dateValidator.add("La date de fin de formation ne peut pas être avant la date d'arrivée");
            this.valide = false;
        }

        return dateValidator;
    }

    public List<String> promotionValidator(int promotion){

        List<String> promotionValidator = new ArrayList<>();

        if(promotion < 1){
            promotionValidator.add("L'id de la promotion ne peut pas être sous 1");
            this.valide = false;
        }
        else if(promotion > 47){
            promotionValidator.add("L'id de la promotion ne peut pas être supérieur à 47 (la dernière promotion actuelle)");
            this.valide = false;
        }

        return promotionValidator;

    }
}


