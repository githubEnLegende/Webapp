package org.oxyl.persistence;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Promotion;
import org.oxyl.model.Question;
import org.oxyl.model.Reponse;

import org.oxyl.newro.Page;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.oxyl.mapper.MapperPromotion;
import org.oxyl.mapper.MapperStagiaire;

public class MySqlConnexion {
	// Informations de connexion à la base de données MySQL
	private static MySqlConnexion instance;
	private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newro-factory-db";
    private static final String USER = "adminnewro";
    private static final String PASS = "Qw€rty1234";
    
    private MySqlConnexion() {
        try {
            if ("test".equals(System.getProperty("environment"))) {
                // Configuration pour la base de données H2 en mémoire
                this.connection = DriverManager
                        .getConnection("jdbc:h2:mem:newro-factory-db;" +
                                "DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;" +
                                "INIT=RUNSCRIPT FROM 'classpath:init.sql';", "testnewro", "T4st3r!");
            }
            else {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MySqlConnexion getInstance() {
        if (instance == null) {
            instance = new MySqlConnexion();
        }
        return instance;
    }
    
    public Connection getConnection() {

        try {
            if (connection.isClosed()) {
                try {
                    // Utiliser une configuration différente si en mode test
                    if ("test".equals(System.getProperty("environment"))) {
                        // Configuration pour la base de données H2 en mémoire
                        this.connection = DriverManager
                                .getConnection("jdbc:h2:mem:newro-factory-db;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'classpath:init.sql';",
                                        "testnewro",
                                        "T4st3r!");
                    } else {

                        // Configuration pour la base de production
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*public static void afficher(String table, Connection conn) {
    	Statement stmt = null;
        try {
            // Création de l'objet Statement
            stmt = conn.createStatement();
            
            // Exécution de la requête SQL pour récupérer toutes les données de la table
            String sql = "SELECT * FROM ";
            sql = sql.concat(table);
            ResultSet rs = stmt.executeQuery(sql);
            
            // Récupération des métadonnées du résultat
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            
            // Affichage des données
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + "\t");
                }
                System.out.println();
            }
            
            // Fermeture des ressources
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // Gestion des erreurs SQL
            se.printStackTrace();
        } catch (Exception e) {
            // Gestion des autres erreurs
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }*/
    
//    public static void afficher(String table, int pageNumber) {
//    	Connection conn = MySqlConnexion.getInstance().getConnection();
//        Statement stmt = null;
//        try {
//        	int rowsPerPage = 50;
//            // Calcul de l'index de la première ligne de la page
//            int startIndex = (pageNumber - 1) * rowsPerPage;
//
//            // Création de l'objet Statement
//            stmt = conn.createStatement();
//
//            // Construction de la requête SQL avec une clause LIMIT pour la pagination
//            String sql = "SELECT * FROM " + table + " LIMIT " + startIndex + ", " + rowsPerPage;
//
//            // Exécution de la requête SQL
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // Récupération des métadonnées du résultat
//            ResultSetMetaData metaData = rs.getMetaData();
//            int numColumns = metaData.getColumnCount();
//
//            // Affichage des données
//            while (rs.next()) {
//                for (int i = 1; i <= numColumns; i++) {
//                    System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + "\t");
//                }
//                System.out.println();
//            }
//
//            // Fermeture des ressources
//            rs.close();
//            stmt.close();
//        } catch (SQLException se) {
//            // Gestion des erreurs SQL
//            se.printStackTrace();
//        } catch (Exception e) {
//            // Gestion des autres erreurs
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }
    
//    public static <T> void afficherPage(Connection conn, int pageNumber, Page<T> page, String table) {
//    	Statement stmt = null;
//    	try {
//    		String sql = "SELECT * FROM " + table + " LIMIT " + page.getNbRow() + ", "
//    				+ (pageNumber - 1) * page.getNbRow();
//    		
//    		stmt = conn.createStatement();
//    		ResultSet rs = stmt.executeQuery(sql);
//    		
//    		while (rs.next()) {
//    			page.addContent(new Stagiaire(rs));
//    		}
//    		
//    		page.display();
//    		
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//    	
//    	
//    } 
    

    

	
	

    

    
    

    

    

    


    
    
    
}
