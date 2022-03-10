package com.example.demo.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class DBConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.user}")
    private String user;
    @Value("${spring.datasource.pass}")
    private String pass;

    @Bean(name = "dbConnectionConfig")
    void connectDb() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO contacts (first_name, last_name, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "John");
            statement.setString(2, "Smith");
            statement.setString(3, "smith@Gmail.com");
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("A new contact added to the Contact DB");
            }
            System.out.println("Connected to DB");
            connection.close();
        } catch (SQLException ex) {
            System.out.println("DB connection error");
            ex.printStackTrace();
        }
    }
}
