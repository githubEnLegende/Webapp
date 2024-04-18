package newro;

import static newro.FonctionDIsplay.*;

import java.sql.Connection;
import java.util.Scanner;

import persistence.MySqlConnexion;

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
	    			DisplayAfficherPageStagiaire(conn, sc);
	    			break;
	    			
	    		case "2": 
	    			DisplayAfficherPagePromotion(conn, sc);
	    			break;
	    			
	    		case "3": 
	    			DisplayAfficherStagiaire(conn, sc);
	    			break;
	    			
	    		case "4": 
	    			DisplayAfficherQuestion(conn, sc);
	    			break;
	    			
	    		case "5": 
	    			DisplayAjouterStagiaire(conn, sc);
	    			break;
	    			
	    		case "6": 
	    			DisplayModifierStagiaire(conn, sc);
	    			break;
	    		
    			case "7": 
    				DiplaySupprimerStagiaire(conn, sc);
    				break;
    				
    			default: System.out.println("Invalide, réessayez"); break;
    		}

    		
    	}
		sc.close();
    	MySqlConnexion.getInstance().closeConnection();

	}

}
