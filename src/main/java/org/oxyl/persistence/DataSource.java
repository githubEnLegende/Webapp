package org.oxyl.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "org.oxyl")
public class DataSource {

    private static final HikariConfig config = new HikariConfig();

    @Bean
    public HikariDataSource DataSource() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }

        HikariConfig config = new HikariConfig("/application.properties");
        return new HikariDataSource(config);

//        config.setJdbcUrl("jdbc:mysql://localhost:3306/newro-factory-db");
//        config.setUsername("adminnewro");
//        config.setPassword("Qw€rty1234");
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        return new HikariDataSource(config);
    }

    public DataSource() {
    }


}
