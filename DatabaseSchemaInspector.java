import java.sql.*;

public class DatabaseSchemaInspector {
    public static void main(String[] args) {
        String url = "jdbc:h2:./data/testdb;AUTO_SERVER=TRUE";
        String user = "sa";
        String password = "";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("=== TABLAS EN LA BASE DE DATOS ===");
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabla: " + tableName);
                
                // Obtener columnas de cada tabla
                ResultSet columns = meta.getColumns(null, null, tableName, null);
                System.out.println("  Columnas:");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String dataType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    String nullable = columns.getString("IS_NULLABLE");
                    System.out.println("    " + columnName + " " + dataType + "(" + columnSize + ") " + 
                                     (nullable.equals("YES") ? "NULL" : "NOT NULL"));
                }
                columns.close();
                
                // Obtener algunas filas de ejemplo
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 3")) {
                    System.out.println("  Datos de ejemplo:");
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    
                    while (rs.next()) {
                        System.out.print("    ");
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(rsmd.getColumnName(i) + "=" + rs.getString(i) + " | ");
                        }
                        System.out.println();
                    }
                } catch (Exception e) {
                    System.out.println("    Error obteniendo datos: " + e.getMessage());
                }
                System.out.println();
            }
            tables.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}