/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.factory.factoryInterno;
import com.mycompany.aplicacionpoo.Config.ConexionDB;
import com.mycompany.aplicacionpoo.dao.*;
import com.mycompany.aplicacionpoo.dao.impl.h2.*;
import com.mycompany.aplicacionpoo.dao.impl.mysql.*;
import com.mycompany.aplicacionpoo.dao.impl.postgres.*;



import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author steve
 */
public class DaoFactory {
    private static Connection conn = ConexionDB.conectar();
    private static String dbType;

    static {
        try (InputStream input = DaoFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(input);
            dbType = props.getProperty("db.type").toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración de base de datos", e);
        }
    }

    public static PersonaDao getPersonaDao() {
        switch (dbType) {
            case "H2" -> {
                return new PersonaDaoH2(conn);
            }
            case "MYSQL" -> {
                return new PersonaDaoMySQL(conn);
            }
            case "POSTGRESQL" -> {
                return new PersonaDaoPostgres(conn);
            }
            default -> throw new RuntimeException("DB no soportada: " + dbType);
        }
    }

    // aquí luego pones también getEstudianteDAO(), getCursoDAO(), etc.
}
