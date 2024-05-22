//package org.oxyl.newro;
//
//import static org.oxyl.newro.FonctionDIsplay.*;
//
//import java.util.Scanner;
//
//
//public class Display {
//
//    public static void feur() {
//        System.out.println("Connexion à la base de donnée");
//
//        boolean quit = false;
//
//        Scanner sc = new Scanner(System.in);
//
//        while (!quit) {
//
//            System.out.println("Choisissez : ");
//            System.out.println("0: Quitter ");
//            System.out.println("1: Lister les stagiaires ");
//            System.out.println("2: Lister les promotions ");
//            System.out.println("3: Afficher les détails d'un stagiaire ");
//            System.out.println("4: Afficher une question ");
//            System.out.println("5: Ajouter un stagiaire ");
//            System.out.println("6: Modifier un stagiaire ");
//            System.out.println("7: Supprimer un stagiaire ");
//            System.out.println("8: Supprimer une question ");
//
//            System.out.print("Choix : ");
//            String choix = sc.nextLine();
//
//            switch (choix) {
//
//                case "0":
//                    System.out.println("Ciao");
//                    quit = true;
//                    break;
//
//                case "1":
//                    DisplayAfficherPageStagiaire(sc);
//                    break;
//
//                case "2":
//                    DisplayAfficherPagePromotion(sc);
//                    break;
//
//                case "3":
//                    DisplayAfficherStagiaire(sc);
//                    break;
//
//                case "4":
//                    DisplayAfficherQuestion(sc);
//                    break;
//
//                case "5":
//                    DisplayAjouterStagiaire(sc);
//                    break;
//
//                case "6":
//                    DisplayModifierStagiaire(sc);
//                    break;
//
//                case "7":
//                    DiplaySupprimerStagiaire(sc);
//                    break;
//
//                case "8":
//                    DisplaySupprimerQuestion(sc);
//                    break;
//
//                default:
//                    System.out.println("Invalide, réessayez");
//                    break;
//            }
//
//
//        }
//        sc.close();
//    }
//
//}
