package org.oxyl.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.RunScript;


public class H2Config {
    private static H2Config instance;

    private H2Config() {
    }

    public static H2Config getInstance() {
        if (instance == null) {
            instance = new H2Config();
        }
        return instance;
    }

    public void setup() {
        ClassLoader loader =  H2Config.class.getClassLoader();
        try (
                Connection connection = MySqlConnexion.getInstance().getConnection();
                InputStream input = loader.getResourceAsStream("setup.sql")
        ) {
            if (input == null) throw new NullPointerException();

            try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                RunScript.execute(connection, reader);
            }
        } catch (NullPointerException e) {
            System.out.println("Unable to find SETUP.sql");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Failed to load SETUP.sql");
            System.exit(-1);
        } catch (SQLException e) {
            System.out.println("Failed to populate database" + e);
            System.exit(-1);
        }

    }

}

