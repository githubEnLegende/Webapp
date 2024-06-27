package org.oxyl.console.newro;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Display {

    private final FonctionDisplay fonctionDisplay;

    public Display(FonctionDisplay fonctionDisplay) {
        this.fonctionDisplay = fonctionDisplay;
    }

    public void feur() {
        System.out.println("Connexion à la base de donnée");

        boolean quit = false;

        Scanner sc = new Scanner(System.in);

        while (!quit) {

            System.out.println("Choisissez : ");
            System.out.println("0: Quitter ");
            System.out.println("1: Lister les stagiaires ");
            System.out.println("2: Lister les promotions ");
            System.out.println("3: Afficher les détails d'un stagiaire ");
            System.out.println("4: Afficher une question ");
            System.out.println("5: Ajouter un stagiaire ");
            System.out.println("6: Modifier un stagiaire ");
            System.out.println("7: Supprimer un stagiaire ");
            System.out.println("8: Supprimer une question ");

            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {

                case "0":
                    System.out.println("Ciao");
                    quit = true;
                    break;

                case "1":
                    fonctionDisplay.DisplayAfficherPageStagiaire(sc);
                    break;

                case "2":
//                    fonctionDisplay.DisplayAfficherPagePromotion(sc);
                    break;

                case "3":
                    fonctionDisplay.DisplayAfficherStagiaire(sc);
                    break;

                case "4":
                    fonctionDisplay.DisplayAfficherQuestion(sc);
                    break;

                case "5":
                    fonctionDisplay.DisplayAjouterStagiaire(sc);
                    break;

                case "6":
                    fonctionDisplay.DisplayModifierStagiaire(sc);
                    break;

                case "7":
                    fonctionDisplay.DiplaySupprimerStagiaire(sc);
                    break;

                case "8":
                    fonctionDisplay.DisplaySupprimerQuestion(sc);
                    break;

                default:
                    System.out.println("Invalide, réessayez");
                    break;
            }


        }
        sc.close();
    }

}
