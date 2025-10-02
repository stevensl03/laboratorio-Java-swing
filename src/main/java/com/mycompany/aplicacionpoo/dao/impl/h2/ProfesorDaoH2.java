/*
 * Implementación H2 del DAO para Profesor
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.ProfesorDao;
import com.mycompany.aplicacionpoo.Model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDaoH2 implements ProfesorDao {
    
    private final Connection conn;
    
    public ProfesorDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Profesor profesor) {
        String sql = "INSERT INTO profesor (id, nombre, apellido, correo, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, profesor.getId());
            stmt.setString(2, profesor.getNombres());
            stmt.setString(3, profesor.getApellidos());
            stmt.setString(4, profesor.getEmail());
            stmt.setString(5, profesor.getTipoContrato());
            stmt.executeUpdate();
            System.out.println("✅ Profesor guardado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (guardarProfesor): " + e.getMessage());
        }
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM profesor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Profesor eliminado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (eliminarProfesor): " + e.getMessage());
        }
    }
    
    @Override
    public void actualizar(Profesor profesor) {
        String sql = "UPDATE profesor SET nombre=?, apellido=?, correo=?, categoria=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profesor.getNombres());
            stmt.setString(2, profesor.getApellidos());
            stmt.setString(3, profesor.getEmail());
            stmt.setString(4, profesor.getTipoContrato());
            stmt.setDouble(5, profesor.getId());
            stmt.executeUpdate();
            System.out.println("✅ Profesor actualizado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (actualizarProfesor): " + e.getMessage());
        }
    }
    
    @Override
    public List<Profesor> mostrarTodos() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, correo, categoria FROM profesor";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setId(rs.getDouble("id"));
                p.setNombres(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellido"));
                p.setEmail(rs.getString("correo"));
                p.setTipoContrato(rs.getString("categoria"));
                profesores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (mostrarProfesores): " + e.getMessage());
        }
        return profesores;
    }
    
    @Override
    public Profesor buscar(int id) {
        String sql = "SELECT id, nombre, apellido, correo, categoria FROM profesor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Profesor p = new Profesor();
                    p.setId(rs.getDouble("id"));
                    p.setNombres(rs.getString("nombre"));
                    p.setApellidos(rs.getString("apellido"));
                    p.setEmail(rs.getString("correo"));
                    p.setTipoContrato(rs.getString("categoria"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (buscarProfesor): " + e.getMessage());
        }
        return null;
    }
}