package org.oxyl.newro;

import static org.oxyl.persistence.UtilitairesDAO.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FonctionDIsplay {

    private static final Logger logger = LoggerFactory.getLogger(FonctionDIsplay.class);

    public static void DisplayAfficherPageStagiaire(Scanner sc) {
        Page<Stagiaire> pageStagiaire = new Page<>();
        boolean boucle = true;
        int pageNumber = 1;
        int totalPages = UtilitairesDAO.getInstance().getTotalPages("intern", pageStagiaire.getNbRow());

        while (boucle) {
            System.out.println("Page " + pageNumber + " sur " + totalPages + ":");
            StagiaireDAO.getInstance().getPageStagiaire(pageStagiaire);
            pageStagiaire.display();
            pageStagiaire.emptyContent();
            System.out.println("1: Page suivante, 2: Page précédente, 3: Choisissez la page, 0: Quitter");
            System.out.println("Choix :");
            String choice = sc.next();

            if (choice.equals("1") && pageNumber < totalPages) {
                pageNumber++;
            } else if (choice.equals("2") && pageNumber > 1) {
                pageNumber--;
            } else if (choice.equals("3")) {
                try {
                    System.out.println("Entrez un nombre :");
                    pageNumber = sc.nextInt();
                } catch (final InputMismatchException e) {
                    System.out.println("not a valid number");
                }
                if (pageNumber > totalPages) {
                    pageNumber = totalPages;
                } else if (pageNumber < 1) {
                    pageNumber = 1;
                }
            } else if (choice.equals("0")) {
                boucle = false;
            } else {
                System.out.println("Choix invalide !");
            }
        }
    }

    public static void DisplayAfficherPagePromotion(Scanner sc) {


        Page<Promotion> pagePromo = new Page<>();
        boolean boucle = true;
        int pageNumber = 1;
        int totalPages = UtilitairesDAO.getInstance().getTotalPages("promotion", pagePromo.getNbRow());

        while (boucle) {
            System.out.println("Page " + pageNumber + " sur " + totalPages + ":");
            PromotionDAO.getInstance().afficherPagePromotion(pageNumber, pagePromo);
            System.out.println("1: Page suivante, 2: Page précédente, 3: Choisissez la page, 0: Quitter");
            System.out.println("Choix :");

            String choice = sc.next();

            if (choice.equals("1") && pageNumber < totalPages) {
                // Aller à la page suivante, si ce n'est pas la dernière page
                pageNumber++;
            } else if (choice.equals("2") && pageNumber > 1) {
                // Aller à la page précédente, si ce n'est pas la première page
                pageNumber--;
            } else if (choice.equals("3")) {
                try {
                    System.out.println("Entrez un nombre :");
                    pageNumber = sc.nextInt();
                } catch (NumberFormatException e) {
                    System.out.println("not a valid number");
                    break;
                }
                if (pageNumber > totalPages) {
                    pageNumber = totalPages;
                } else if (pageNumber < 1) {
                    pageNumber = 1;
                }
            } else if (choice.equals("0")) {
                // Quitter la boucle
                boucle = false;
            } else {
                System.out.println("Choix invalide !");
            }
        }
    }

    public static void DisplayAfficherStagiaire(Scanner sc) {
        System.out.print("Entrez l'ID d'un stagiaire :");
        String userChoice = sc.nextLine();

        try {
            int id = Integer.parseInt(userChoice);
            StagiaireDAO.getInstance().detailStagiaire(id);
        } catch (NumberFormatException e) {
            System.out.println("not a valid ID");
        }
    }

    public static void DisplayAfficherQuestion(Scanner sc) {
        System.out.print("Entrez l'ID d'une question :");
        String userChoice = sc.nextLine();
        try {
            int id = Integer.parseInt(userChoice);
            QuestionDAO.getInstance().getQuestionById(id);
        } catch (NumberFormatException e) {
            System.out.println("not a valid ID");
        }
    }

    public static void DisplayAjouterStagiaire(Scanner sc) {
        try (Connection conn = MySqlConnexion.getInstance().getConnection()) {
            System.out.println("Entrez son prénom, nom, date d'arrivée et l'id de sa promotion :");
            Stagiaire random = new Stagiaire.StagiaireBuilder(UtilitairesDAO.getInstance().getMaxID() + 1, sc.next(), sc.next(), LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))).promotion(sc.nextInt()).build();

            StagiaireDAO.getInstance().insertIntern(random);
        } catch (InputMismatchException e) {
            System.out.println("not a valid Promotion ID");
        } catch (DateTimeParseException e) {
            System.out.println("not a valid Date");
        } catch (SQLException e) {
            logger.error("Erreur lors de la connexion", e);
        }
    }

    public static void DisplayModifierStagiaire(Scanner sc) {
        System.out.print("Entrez l'ID du stagiaire que vous voulez modifier : ");

        String choixUtilisateur = sc.nextLine();
        int id = 0;
        try {
            id = Integer.parseInt(choixUtilisateur);
        } catch (NumberFormatException e) {
            System.out.println("not a valid ID");
        }

        System.out.println("Que voulez vous modifier?");
        System.out.println("0: Annuler");
        System.out.println("1: Son prénom");
        System.out.println("2: Son nom");
        System.out.println("3: Sa promotion");
        System.out.println("4: Sa date d'arrivée");
        System.out.println("5: Les trois");
        System.out.print("Choix : ");

        String userChoice = sc.nextLine();

        String prenom = null;
        String nom = null;
        String arrive = null;
        String finFormation = null;
        int promo = 0;

        switch (userChoice) {
            case "0":
                break;

            case "1":
                prenom = sc.nextLine();
                StagiaireDAO.getInstance().updateIntern(prenom, nom, arrive, finFormation, promo, id);
                break;

            case "2":
                nom = sc.nextLine();
                StagiaireDAO.getInstance().updateIntern(prenom, nom, arrive, finFormation, promo, id);
                break;

            case "3":
                choixUtilisateur = sc.nextLine();
                try {
                    promo = Integer.parseInt(choixUtilisateur);
                    if (promo > 47) {
                        promo = 47;
                    } else if (promo < 1) {
                        promo = 1;
                    }
                    StagiaireDAO.getInstance().updateIntern(prenom, nom, arrive, finFormation, promo, id);
                } catch (NumberFormatException e) {
                    System.out.println("not a valid ID");
                    break;
                }
                break;

            case "4":
                arrive = sc.nextLine();
                StagiaireDAO.getInstance().updateIntern(prenom, nom, arrive, finFormation, promo, id);
                break;

            case "5":
                prenom = sc.next();
                nom = sc.next();
                arrive = sc.next();
                choixUtilisateur = sc.next();
                try {
                    promo = Integer.parseInt(choixUtilisateur);
                    StagiaireDAO.getInstance().updateIntern(prenom, nom, arrive, finFormation, promo, id);
                } catch (NumberFormatException e) {
                    System.out.println("not a valid ID");
                    break;
                }
                break;

            default:
                System.out.println("Choix invalide");
                break;
        }
    }


    public static void DiplaySupprimerStagiaire(Scanner sc) {
        System.out.print("Entrez l'ID du stagiaire à supprimer :");
        String userChoice = sc.nextLine();
        try {
            int id = Integer.parseInt(userChoice);
            StagiaireDAO.getInstance().deleteIntern(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalide");
        }
    }
}
