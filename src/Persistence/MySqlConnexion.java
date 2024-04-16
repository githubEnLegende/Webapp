package Persistence;
import model.Stagiaire;
import model.Question;
import model.Reponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnexion {
	// Informations de connexion à la base de données MySQL
	private static MySqlConnexion instance;
	private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newro-factory-db";
    private static final String USER = "adminnewro";
    private static final String PASS = "Qw€rty1234";
    
    private MySqlConnexion() {
        try {
            // Création de la connexion à la base de données
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
    
    public static void afficher(String table, Connection conn) {
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
            // Fermeture de la connexion
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public static void detailStagiaire(Connection conn, int id){
    	
    	String sql = "SELECT * FROM intern WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id); // Remplacez 1 par l'ID du stagiaire désiré
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Affichage des données
                    System.out.println("ID : " + rs.getInt("id"));
                    System.out.println("First Name : " + rs.getString("first_name"));
                    System.out.println("Last Name : " + rs.getString("last_name"));
                    System.out.println("Arrival : " + rs.getTimestamp("arrival"));
                    System.out.println("Formation Over : " + rs.getTimestamp("formation_over"));
                    System.out.println("Promotion ID : " + rs.getInt("promotion_id"));
                } else {
                    System.out.println("Aucun résultat trouvé.");
                }
            }
        }
	     catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    public static void getQuestionById(Connection conn, int questionId) {
    	Question question = new Question();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM question WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Création d'un objet Question avec les données récupérées
                question.setId(rs.getInt("id"));
                question.setTitle(rs.getString("title"));
                question.setStatement(rs.getString("statement"));
                question.setChapitreId(rs.getInt("chapter_id"));
                System.out.print(question.toString());
                
                String answer = "SELECT * FROM answer WHERE question_id = ?";
                stmt = conn.prepareStatement(answer);
                stmt.setInt(1, question.getId());
                rs = stmt.executeQuery();
                
                while(rs.next()) {
                	Reponse reponse = new Reponse(rs);
                	System.out.println(reponse.toString());
                }
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void insertIntern(Connection conn, Stagiaire intern) {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO intern (first_name, last_name, arrival, formation_over, promotion_id) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, intern.getFirstName());
            stmt.setString(2, intern.getLastName());
            stmt.setTimestamp(3, intern.getArrival());
            stmt.setTimestamp(4, intern.getFormationOver());
            stmt.setInt(5, intern.getPromotion());

            // Exécution de la requête d'insertion
            stmt.executeUpdate();
            System.out.println("Stagiaire inséré avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    
}
