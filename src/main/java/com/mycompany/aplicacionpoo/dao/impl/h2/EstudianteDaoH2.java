/*
 * Implementación H2 del DAO para Estudiante
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.EstudianteDao;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDaoH2 implements EstudianteDao {
    
    private final Connection conn;
    
    public EstudianteDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (id, nombre, apellido, correo, codigo, programa_id, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, estudiante.getId());
            stmt.setString(2, estudiante.getNombres());
            stmt.setString(3, estudiante.getApellidos());
            stmt.setString(4, estudiante.getEmail());
            stmt.setDouble(5, estudiante.getCodigo());
            stmt.setDouble(6, estudiante.getPrograma().getId());
            stmt.setBoolean(7, estudiante.isActivo());
            stmt.setDouble(8, estudiante.getPromedio());
            stmt.executeUpdate();
            System.out.println("✅ Estudiante guardado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (guardarEstudiante): " + e.getMessage());
        }
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Estudiante eliminado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (eliminarEstudiante): " + e.getMessage());
        }
    }
    
    @Override
    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, correo=?, codigo=?, programa_id=?, activo=?, promedio=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombres());
            stmt.setString(2, estudiante.getApellidos());
            stmt.setString(3, estudiante.getEmail());
            stmt.setDouble(4, estudiante.getCodigo());
            stmt.setDouble(5, estudiante.getPrograma().getId());
            stmt.setBoolean(6, estudiante.isActivo());
            stmt.setDouble(7, estudiante.getPromedio());
            stmt.setDouble(8, estudiante.getId());
            stmt.executeUpdate();
            System.out.println("✅ Estudiante actualizado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (actualizarEstudiante): " + e.getMessage());
        }
    }
    
    @Override
    public List<Estudiante> mostrarTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, correo, codigo, programa_id, activo, promedio FROM estudiante";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Crear estudiante básico (sin programa por simplicidad)
                Estudiante e = new Estudiante();
                e.setId(rs.getDouble("id"));
                e.setNombres(rs.getString("nombre"));
                e.setApellidos(rs.getString("apellido"));
                e.setEmail(rs.getString("correo"));
                e.setCodigo(rs.getDouble("codigo"));
                e.setActivo(rs.getBoolean("activo"));
                e.setPromedio(rs.getDouble("promedio"));
                estudiantes.add(e);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (mostrarEstudiantes): " + e.getMessage());
        }
        return estudiantes;
    }
    
    @Override
    public Estudiante buscar(int id) {
        String sql = "SELECT id, nombre, apellido, correo, codigo, programa_id, activo, promedio FROM estudiante WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudiante e = new Estudiante();
                    e.setId(rs.getDouble("id"));
                    e.setNombres(rs.getString("nombre"));
                    e.setApellidos(rs.getString("apellido"));
                    e.setEmail(rs.getString("correo"));
                    e.setCodigo(rs.getDouble("codigo"));
                    e.setActivo(rs.getBoolean("activo"));
                    e.setPromedio(rs.getDouble("promedio"));
                    return e;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (buscarEstudiante): " + e.getMessage());
        }
        return null;
    }
}