package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.ProfesorDao;
import com.mycompany.aplicacionpoo.Model.Profesor;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDaoOracle implements ProfesorDao {
    
    private static InternalFactory factory;
    private final Connection conn;
    
    public ProfesorDaoOracle(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
    
    @Override
    public void guardar(Profesor profesor) {
        String sql = "INSERT INTO profesor (id, tipo_contrato) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, profesor.getId());
            stmt.setString(2, profesor.getTipoContrato());
            stmt.executeUpdate();
            System.out.println("✅ Profesor guardado");
        } catch (SQLException e) {
            System.out.println("❌ Error (guardarProfesor): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM profesor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Profesor eliminado");
        } catch (SQLException e) {
            System.out.println("❌ Error (eliminarProfesor): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Profesor profesor) {
        String sql = "UPDATE profesor SET tipo_contrato=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profesor.getTipoContrato());
            stmt.setDouble(2, profesor.getId());
            stmt.executeUpdate();
            System.out.println("✅ Profesor actualizado");
        } catch (SQLException e) {
            System.out.println("❌ Error (actualizarProfesor): " + e.getMessage());
        }
    }

    @Override
    public List<Profesor> mostrarTodos() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = """
                     SELECT p.id, p.nombre, p.apellido, p.correo, pr.tipo_contrato
                     FROM persona p
                     INNER JOIN profesor pr ON p.id = pr.id
                     """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Profesor p = factory.createProfesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("tipo_contrato")
                );
                profesores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (mostrarProfesores): " + e.getMessage());
        }
        return profesores;
    }

    @Override
    public Profesor buscar(int id) {
        String sql = """
                     SELECT p.id, p.nombre, p.apellido, p.correo, pr.tipo_contrato
                     FROM persona p
                     INNER JOIN profesor pr ON p.id = pr.id
                     WHERE p.id = ?
                     """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return factory.createProfesor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("tipo_contrato")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (buscarProfesor): " + e.getMessage());
        }
        return null;
    }
}