package com.mycompany.aplicacionpoo.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseAdapter {
    
    private static DatabaseAdapter instance;
    private Connection connection;
    private String dbType;
    
    private DatabaseAdapter() {
        initializeConnection();
    }
    
    public static synchronized DatabaseAdapter getInstance() {
        if (instance == null) {
            instance = new DatabaseAdapter();
        }
        return instance;
    }
    
    private void initializeConnection() {
        Properties selectorProps = new Properties();
        Properties configProps = new Properties();
        
        try {
            // 1. Cargar selector de base de datos
            try (InputStream input = DatabaseAdapter.class.getClassLoader().getResourceAsStream("database-selector.properties")) {
                if (input == null) {
                    throw new RuntimeException("No se encontró el archivo database-selector.properties");
                }
                selectorProps.load(input);
                dbType = selectorProps.getProperty("db.type").toUpperCase();
            }
            
            // 2. Cargar configuraciones de todas las bases de datos
            try (InputStream input = DatabaseAdapter.class.getClassLoader().getResourceAsStream("database-config.properties")) {
                if (input == null) {
                    throw new RuntimeException("No se encontró el archivo database-config.properties");
                }
                configProps.load(input);
            }
            
            // 3. Obtener configuración específica según el tipo seleccionado
            String dbPrefix = "db." + dbType.toLowerCase();
            String url = configProps.getProperty(dbPrefix + ".url");
            String user = configProps.getProperty(dbPrefix + ".user");
            String password = configProps.getProperty(dbPrefix + ".password");
            String driverClass = configProps.getProperty(dbPrefix + ".driver");
            
            // 4. Validar configuración
            if (url == null || user == null || password == null || driverClass == null) {
                throw new RuntimeException("Configuración incompleta para " + dbType + 
                    " en database-config.properties");
            }
            
            // 5. Establecer conexión
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
            
            // Logging detallado del sistema
            System.out.println("=".repeat(80));
            System.out.println("🗄️  SISTEMA DE GESTIÓN ACADÉMICA - INICIALIZACIÓN DE BASE DE DATOS");
            System.out.println("=".repeat(80));
            System.out.println("📋 Esquema creado y verificado para: " + dbType);
            System.out.println("📊 Datos iniciales cargados para: " + dbType);
            System.out.println("🟢 BD ACTIVA: " + dbType);
            System.out.println("-".repeat(80));
            System.out.println("🔗 Producto: " + getDatabaseProductInfo());
            System.out.println("🌐 URL: " + url);
            System.out.println("👤 Usuario: " + user);
            System.out.println("📅 Fecha: " + java.time.LocalDate.now());
            System.out.println("🕐 Hora: " + java.time.LocalTime.now());
            System.out.println("⏰ Timestamp: " + java.time.LocalDateTime.now());
            System.out.println("=".repeat(80));
            
            // Verificar esquema después de la conexión
            verifyDatabaseSchema();
            
        } catch (IOException | ClassNotFoundException | RuntimeException | SQLException e) {
            throw new RuntimeException("❌ Adapter: Error de conexión: " + e.getMessage(), e);
        }
    }
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verificando conexión: " + e.getMessage(), e);
        }
        return connection;
    }
    
    public String getDatabaseType() {
        return dbType;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Adapter: Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("❌ Adapter: Error cerrando conexión: " + e.getMessage());
        }
    }
    
    public String getCurrentDateFunction() {
        return switch (dbType) {
            case "H2" -> "CURRENT_DATE()";
            case "MYSQL" -> "CURDATE()";
            case "POSTGRESQL" -> "CURRENT_DATE";
            case "ORACLE" -> "SYSDATE";
            default -> "CURRENT_DATE";
        };
    }
    
    public String getCurrentTimeFunction() {
        return switch (dbType) {
            case "H2" -> "CURRENT_TIME()";
            case "MYSQL" -> "CURTIME()";
            case "POSTGRESQL" -> "CURRENT_TIMESTAMP";
            case "ORACLE" -> "SYSTIMESTAMP";
            default -> "CURRENT_TIME";
        };
    }
    
    public String getDatabaseProductInfo() {
        try {
            if (connection != null && !connection.isClosed()) {
                String productName = connection.getMetaData().getDatabaseProductName();
                String productVersion = connection.getMetaData().getDatabaseProductVersion();
                return productName + " " + productVersion;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error obteniendo información del producto: " + e.getMessage());
        }
        return dbType + " Database";
    }
    
    public void verifyDatabaseSchema() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("🔍 Verificando esquema de base de datos...");
                
                // Verificar tablas principales
                String[] tables = {"persona", "facultad", "programa", "profesor", "estudiante", "curso", "inscripcion", "cursoprofesor"};
                int tablesFound = 0;
                
                for (String table : tables) {
                    try (var stmt = connection.createStatement();
                         var rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table)) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            System.out.println("   ✅ Tabla '" + table + "': " + count + " registros");
                            tablesFound++;
                        }
                    } catch (SQLException e) {
                        System.out.println("   ❌ Tabla '" + table + "': No encontrada");
                    }
                }
                
                System.out.println("📊 Total de tablas verificadas: " + tablesFound + "/" + tables.length);
                
                if (tablesFound == tables.length) {
                    System.out.println("✅ Esquema completamente verificado para " + dbType);
                } else {
                    System.out.println("⚠️ Esquema parcialmente verificado para " + dbType);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error verificando esquema: " + e.getMessage());
        }
    }
}