package newro;

import java.sql.Connection;
import java.util.Scanner;

import Persistence.MySqlConnexion;
import model.Stagiaire;
import static Persistence.MySqlConnexion.*;

public class Display {

	public static void feur() {
		System.out.println("Connexion à la base de donnée");
		Connection conn = MySqlConnexion.getInstance().getConnection();
		
    	boolean quit = false;
		Scanner sc = new Scanner(System.in);

    	while(!quit) {
    		
    		System.out.println("Choisissez : ");
    		System.out.println("0: Quitter ");
    		System.out.println("1: Lister les stagiaires ");
    		System.out.println("2: Lister les promotions ");
    		System.out.println("3: Afficher les détails d'un stagiaire ");
    		System.out.println("4: Afficher une question ");
    		System.out.println("5: Ajouter un stagiaire ");
    		System.out.println("6: Modifier un stagiaire ");
    		System.out.println("7: Supprimer un stagiaire ");
    		
    		System.out.print("Choix : ");
    		String choix = sc.nextLine();
    		
    		switch(choix) {
    		
	    		case "0":
	    			System.out.println("Ciao");
	    			quit=true; 
	    			break;
	    			
	    		case "1": 
	    			afficher("intern", conn); 
	    			break;
	    			
	    		case "2": 
	    			afficher("promotion", conn); 
	    			break;
	    			
	    		case "3": 
	    			System.out.print("Entrez l'ID d'un stagiaire :");
	    			String userChoice = sc.nextLine();
	    			try {
	    				int id = Integer.parseInt(userChoice);
		    			detailStagiaire(conn, id); 
	    			}catch(NumberFormatException e) {
	    				System.out.println("not a valid ID");
	    				break;
	    			}
	    			break;
	    			
	    		case "4": 
	    			System.out.print("Entrez l'ID d'une question :");
	    			userChoice = sc.nextLine();
	    			try {
	    				int id = Integer.parseInt(userChoice);
	    				getQuestionById(conn, id); 
	    			}catch(NumberFormatException e) {
	    				System.out.println("not a valid ID");
	    				break;
	    			}
	    			break;
	    			
	    		case "5": 
	    			Stagiaire random = new Stagiaire("Franck", "Alonso", null, null, 1);
	    			insertIntern(conn, random);
	    			break;
	    		case "6": quit=true; break;
    			case "7": quit=true; break;
    			default: System.out.println("Invalide, réessayez"); break;
    		}

    		
    	}
		sc.close();
    	MySqlConnexion.getInstance().closeConnection();



	}

}
