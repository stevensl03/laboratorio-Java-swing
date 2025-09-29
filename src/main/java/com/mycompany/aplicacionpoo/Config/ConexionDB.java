/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.aplicacionpoo.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConexionDB {

    public static Connection conectar() {
        Properties props = new Properties();

        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo db.properties en resources/");
            }

            props.load(input);

            // Leemos el tipo de base de datos
            String dbType = props.getProperty("db.type").toUpperCase();

            String url;
            String user;
            String password;
            String driverClass;

            switch (dbType) {
                case "H2":
                    url = props.getProperty("db.h2.url");
                    user = props.getProperty("db.h2.user");
                    password = props.getProperty("db.h2.password");
                    driverClass = "org.h2.Driver";
                    break;
                case "MYSQL":
                    url = props.getProperty("db.mysql.url");
                    user = props.getProperty("db.mysql.user");
                    password = props.getProperty("db.mysql.password");
                    driverClass = "com.mysql.cj.jdbc.Driver";
                    break;
                case "POSTGRESQL":
                    url = props.getProperty("db.postgres.url");
                    user = props.getProperty("db.postgres.user");
                    password = props.getProperty("db.postgres.password");
                    driverClass = "org.postgresql.Driver";
                    break;
                default:
                    throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
            }

            // Registrar el driver explícitamente
            Class.forName(driverClass);

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a " + dbType + " (" + url + ")");
            return conn;

        } catch (Exception e) {
            throw new RuntimeException("❌ No se pudo conectar: " + e.getMessage(), e);
        }
    }
}




