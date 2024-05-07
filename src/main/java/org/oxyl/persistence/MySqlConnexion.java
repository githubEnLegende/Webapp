package org.oxyl.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public class MySqlConnexion {

    private static Logger logger = LoggerFactory.getLogger(MySqlConnexion.class);
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
                connection = DriverManager.getConnection("jdbc:h2:mem:newro-factory-db;" + "DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;" + "INIT=RUNSCRIPT FROM 'classpath:init.sql';", "testnewro", "T4st3r!");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la connexion", e);
        } catch (ClassNotFoundException e) {
            logger.error("Erreur lors de la récupération du driver", e);
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
                        this.connection = DriverManager.getConnection("jdbc:h2:mem:newro-factory-db;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'classpath:init.sql';", "testnewro", "T4st3r!");
                    } else {

                        // Configuration pour la base de production
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);

                    }
                } catch (SQLException e) {
                    logger.error("Erreur lors de la connexion", e.getMessage());

                } catch (ClassNotFoundException e) {
                    logger.error("Erreur lors de la récupération du driver", e);
                }
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la connexion", e.getMessage());
        }
        return connection;
    }
}
