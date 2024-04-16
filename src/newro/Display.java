package newro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	    			Stagiaire random = new Stagiaire(getMaxID(conn)+1, "Franck", "Alonso", null, null, 1);
	    			insertIntern(conn, random);
	    			break;
	    			
	    		case "6": 
	    			System.out.print("Entrez l'ID du stagiaire que vous voulez modifier : ");
	    			
    				String choixUtilisateur = sc.nextLine();
    				int id = 0;
    				try {
	    				id = Integer.parseInt(choixUtilisateur);
	    			}catch(NumberFormatException e) {
	    				System.out.println("not a valid ID");
	    				break;
	    			}
    				
    				System.out.println("Que voulez vous modifier?");
	    			System.out.println("0: Annuler");
	    			System.out.println("1: Son prénom");
	    			System.out.println("2: Son nom");
	    			System.out.println("3: Sa promotion");
	    			System.out.println("4: Les trois");
	        		System.out.print("Choix : ");
	        		
	    			userChoice = sc.nextLine();
	    			
	    			String prenom = null;
	        		String nom = null;
	        		int promo = 0;
	    			
	    			switch(userChoice) {
		    			case "0":
		    				break;
		    				
		    			case "1":
		    				prenom = sc.nextLine();
		    				updateIntern(conn, prenom, nom, promo, id);
		    				break;
		    				
		    			case "2":
		    				nom = sc.nextLine();
		    				updateIntern(conn, prenom, nom, promo, id);
		    				break;
		    				
		    			case "3":
		    				choixUtilisateur = sc.nextLine();
		    				try {
			    				promo = Integer.parseInt(choixUtilisateur);
			    				updateIntern(conn, prenom, nom, promo, id);
			    			}catch(NumberFormatException e) {
			    				System.out.println("not a valid ID");
			    				break;
			    			}
		    				break;
		    				
		    			case "4":
		    				prenom = sc.next();
		    				nom = sc.next();
		    				choixUtilisateur = sc.next();
		    				try {
			    				promo = Integer.parseInt(choixUtilisateur);
			    				updateIntern(conn, prenom, nom, promo, id);
			    			}catch(NumberFormatException e) {
			    				System.out.println("not a valid ID");
			    				break;
			    			}
		    				break;

	    				default:
	    					System.out.println("Choix invalide");
	    					break;
	    			}
	    			break;
	    		
    			case "7": 
    				System.out.print("Entrez l'ID du stagiaire à supprimer :");
    				userChoice = sc.nextLine();
    				try {
	    				id = Integer.parseInt(userChoice);
	    				deleteIntern(conn, id);
	    			}catch(NumberFormatException e) {
	    				System.out.println("ID invalide");
	    				break;
	    			}
    				break;
    				
    			default: System.out.println("Invalide, réessayez"); break;
    		}

    		
    	}
		sc.close();
    	MySqlConnexion.getInstance().closeConnection();



	}

}
