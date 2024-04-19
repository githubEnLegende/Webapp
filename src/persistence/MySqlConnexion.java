package persistence;
import model.Stagiaire;
import model.Promotion;
import model.Question;
import model.Reponse;

import newro.Page;

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

public class MySqlConnexion {
	// Informations de connexion à la base de données MySQL
	private static MySqlConnexion instance;
	private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newro-factory-db";
    private static final String USER = "adminnewro";
    private static final String PASS = "Qw€rty1234";
    
    private MySqlConnexion() {
        try {
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
    	try {
			if(connection.isClosed()) {	
			connection = DriverManager.getConnection(DB_URL, USER, PASS);   
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
    
    public static void afficher(String table, int pageNumber) {
    	Connection conn = MySqlConnexion.getInstance().getConnection();
        Statement stmt = null;
        try {
        	int rowsPerPage = 50;
            // Calcul de l'index de la première ligne de la page
            int startIndex = (pageNumber - 1) * rowsPerPage;

            // Création de l'objet Statement
            stmt = conn.createStatement();

            // Construction de la requête SQL avec une clause LIMIT pour la pagination
            String sql = "SELECT * FROM " + table + " LIMIT " + startIndex + ", " + rowsPerPage;

            // Exécution de la requête SQL
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
    }
    
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
//    		// TODO Auto-generated catch block
//    		e.printStackTrace();
//    	}
//    	
//    	
//    }
    
    public static void afficherPageStagiaire(int pageNumber, Page<Stagiaire> page) {
    	
    	String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern LIMIT ? OFFSET ?";
    	try(Connection conn = MySqlConnexion.getInstance().getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql);){
    			
    		stmt.setInt(1, page.getNbRow());
    		stmt.setInt(2, (pageNumber - 1) * page.getNbRow());
    		ResultSet rs = stmt.executeQuery();
    		while (rs.next()) {
    			page.addContent(new Stagiaire(rs));
    		}
    		page.display();
    		page.emptyContent();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    } 
    
    public static void afficherPagePromotion(int pageNumber, Page<Promotion> page) {
    	
    	String sql = "SELECT id, name FROM promotion LIMIT ? OFFSET ?";

    	try(Connection conn = MySqlConnexion.getInstance().getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql);){
        	stmt.setInt(1, page.getNbRow());
    		stmt.setInt(2, (pageNumber - 1) * page.getNbRow());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				page.addContent(new Promotion(rs));
			}
			
			page.display();
			page.emptyContent();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
    }
    
    public static void detailStagiaire(int id){
    	
    	String sql = "SELECT id, first_name, last_name, arrival, formation_over, promotion_id FROM intern WHERE id = ?";
        try (Connection conn = MySqlConnexion.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
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
    
    public static void getQuestionById(int questionId) {
    	
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try(Connection conn = MySqlConnexion.getInstance().getConnection();) {
            String sql = "SELECT id, title, statement, chapter_id FROM question WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionId);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	Question question = new Question.QuestionBuilder(
            										rs.getInt("id"), 
            										rs.getString("title"), 
            										rs.getString("statement"), 
            										rs.getInt("chapter_id")).build();
                System.out.print(question.toString());
                
                String answer = "SELECT id, text, valid_answer, question_id FROM answer WHERE question_id = ?";
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
    
    
    public static void insertIntern(Stagiaire intern) {
        String sql = "INSERT INTO intern (first_name, last_name, arrival, formation_over, promotion_id)"
        		+ " VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = MySqlConnexion.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql);) {
            
            stmt.setString(1, intern.getFirstName());
            stmt.setString(2, intern.getLastName());
            
            Timestamp timestamp = Timestamp.valueOf(intern.getArrival().atStartOfDay());
            stmt.setTimestamp(3, timestamp);
            stmt.setTimestamp(4, null);
            
            stmt.setInt(5, intern.getPromotion());

            // Exécution de la requête d'insertion
            stmt.executeUpdate();
            System.out.println("Stagiaire inséré avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteIntern(int id) {
    	
        String sql = "DELETE FROM intern WHERE id = ?";

        try(Connection conn = MySqlConnexion.getInstance().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setLong(1, id);

            // Exécution de la requête de suppression
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stagiaire supprimé avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int getMaxID(Connection conn) {
    	int maxId = 0;
    	String sql = "SELECT MAX(ID) FROM intern";
		ResultSet rs = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return maxId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
    }
    
    public static int getTotalPages(String table, int rowsPerPage){
		MySqlConnexion connection = MySqlConnexion.getInstance();
        String countQuery = "SELECT COUNT(*) FROM " + table;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(countQuery);) {
            ResultSet rs = stmt.executeQuery(countQuery);
            rs.next();
            int totalRows = rs.getInt(1);

            // Calcul du nombre total de pages
            return (int) Math.ceil((double) totalRows / rowsPerPage);
        } catch (SQLException e) {
        	e.printStackTrace();
        	return 0;
        }finally {
        	connection.closeConnection();
        }
    }
    
    public static void updateIntern(String prenom, String nom, String arrive, int promo, int id) {
    	PreparedStatement stmt = null;

        try(Connection conn = MySqlConnexion.getInstance().getConnection();) {
        	
        	String sql = "UPDATE intern SET ";
        	if (prenom != null) {
        		sql = sql.concat("first_name = \""+ prenom +"\", ");
        	}
        	
        	if (nom != null) {
        		sql = sql.concat("last_name = \""+ nom +"\", ");
        	}
        	
        	if (promo != 0) {
        		sql = sql.concat("promotion_id = "+ promo +", ");
        	}
        	if(arrive !=null) {
        		sql = sql.concat("arrival = \""
        				+ LocalDate.parse(arrive, DateTimeFormatter.ofPattern("dd-MM-yyyy")) +"\", ");
        	}
        	
        	sql = sql.substring(0, sql.length() - 2);
        	sql = sql.concat(" WHERE id = " + id);
        	
        	System.out.println(sql);
            stmt = conn.prepareStatement(sql);

            // Exécution de la requête de mise à jour
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stagiaire mis à jour avec succès !");
            } else {
                System.out.println("Aucun stagiaire trouvé avec l'ID spécifié.");
            }
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
