package org.oxyl.console.newro;

import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.persistence.PromotionDAO;
import org.oxyl.persistence.QuestionDAO;
import org.oxyl.persistence.StagiaireDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class FonctionDisplay {

    private static final Logger logger = LoggerFactory.getLogger(FonctionDisplay.class);

    private final StagiaireDAO stagiaireDAO;
    private final PromotionDAO promotionDAO;
    private final QuestionDAO questionDAO;

    public FonctionDisplay(StagiaireDAO stagiaireDAO,
                           PromotionDAO promotionDAO, QuestionDAO questionDAO) {
        this.stagiaireDAO = stagiaireDAO;
        this.promotionDAO = promotionDAO;
        this.questionDAO = questionDAO;
    }

    public void DisplayAfficherPageStagiaire(Scanner sc) {
        Page<Stagiaire> pageStagiaire = new Page<>();
        boolean boucle = true;
        long pageNumber = 1;
        long totalPages = 0;

        while (boucle) {
            System.out.println("Page " + pageNumber + " sur " + totalPages + ":");
            stagiaireDAO.getPageStagiaire(pageStagiaire);
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

//    public void DisplayAfficherPagePromotion(Scanner sc) {
//
//
//        Page<Promotion> pagePromo = new Page<>();
//        boolean boucle = true;
//        long pageNumber = 1;
//        long totalPages = utilitairesDAO.getTotalPages("promotion", pagePromo.getNbRow());
//
//        while (boucle) {
//            System.out.println("Page " + pageNumber + " sur " + totalPages + ":");
//            //promotionDAO.afficherPagePromotion(pagePromo);
//            System.out.println("1: Page suivante, 2: Page précédente, 3: Choisissez la page, 0: Quitter");
//            System.out.println("Choix :");
//
//            String choice = sc.next();
//
//            if (choice.equals("1") && pageNumber < totalPages) {
//                // Aller à la page suivante, si ce n'est pas la dernière page
//                pageNumber++;
//            } else if (choice.equals("2") && pageNumber > 1) {
//                // Aller à la page précédente, si ce n'est pas la première page
//                pageNumber--;
//            } else if (choice.equals("3")) {
//                try {
//                    System.out.println("Entrez un nombre :");
//                    pageNumber = sc.nextInt();
//                } catch (NumberFormatException e) {
//                    System.out.println("not a valid number");
//                    break;
//                }
//                if (pageNumber > totalPages) {
//                    pageNumber = totalPages;
//                } else if (pageNumber < 1) {
//                    pageNumber = 1;
//                }
//            } else if (choice.equals("0")) {
//                // Quitter la boucle
//                boucle = false;
//            } else {
//                System.out.println("Choix invalide !");
//            }
//        }
//    }

    public void DisplayAfficherStagiaire(Scanner sc) {
        System.out.print("Entrez l'ID d'un stagiaire :");
        String userChoice = sc.nextLine();

        try {
            int id = Integer.parseInt(userChoice);
            stagiaireDAO.detailStagiaire(id);
        } catch (NumberFormatException e) {
            System.out.println("not a valid ID");
        }
    }

    public void DisplayAfficherQuestion(Scanner sc) {
        System.out.print("Entrez l'ID d'une question :");
        String userChoice = sc.nextLine();
        try {
            int id = Integer.parseInt(userChoice);
            questionDAO.getQuestionById(id);
        } catch (NumberFormatException e) {
            System.out.println("not a valid ID");
        }
    }

    public void DisplayAjouterStagiaire(Scanner sc) {
        try {
            System.out.println("Entrez son prénom, nom, date d'arrivée et l'id de sa promotion :");
            Stagiaire random = new Stagiaire.StagiaireBuilder()
                    .id(stagiaireDAO.getMaxID()+1)
                    .firstName(sc.next())
                    .lastName(sc.next())
                    .arrival(LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .build();

            stagiaireDAO.insertIntern(random);
        } catch (InputMismatchException e) {
            System.out.println("not a valid Promotion ID");
        } catch (DateTimeParseException e) {
            System.out.println("not a valid Date");
        }
    }

    public void DisplayModifierStagiaire(Scanner sc) {
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
                //stagiaireDAO.updateIntern(prenom, nom, arrive, finFormation, promo, id);
                break;

            case "2":
                nom = sc.nextLine();
                //stagiaireDAO.updateIntern(prenom, nom, arrive, finFormation, promo, id);
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
                    // stagiaireDAO.updateIntern(prenom, nom, arrive, finFormation, promo, id);
                } catch (NumberFormatException e) {
                    System.out.println("not a valid ID");
                    break;
                }
                break;

            case "4":
                arrive = sc.nextLine();
                //stagiaireDAO.updateIntern(prenom, nom, arrive, finFormation, promo, id);
                break;

            case "5":
                prenom = sc.next();
                nom = sc.next();
                arrive = sc.next();
                choixUtilisateur = sc.next();
                try {
                    promo = Integer.parseInt(choixUtilisateur);
                    //stagiaireDAO.updateIntern(prenom, nom, arrive, finFormation, promo, id);
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


    public void DiplaySupprimerStagiaire(Scanner sc) {
        System.out.print("Entrez l'ID du stagiaire à supprimer :");
        String userChoice = sc.nextLine();
        try {
            int id = Integer.parseInt(userChoice);
            stagiaireDAO.deleteIntern(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalide");
        }
    }

    public void DisplaySupprimerQuestion(Scanner sc) {
        System.out.println("Entrez l'ID de la question à supprimer : ");
        String userChoice = sc.nextLine();
        try {
            int id = Integer.parseInt(userChoice);
            questionDAO.deleteQuestion(id);
        } catch (NumberFormatException e) {
            System.out.println("ID invalide");
        }
    }
}
