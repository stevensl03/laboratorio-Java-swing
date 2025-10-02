/*
 * Implementación H2 del DAO para Curso
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.CursoDao;
import com.mycompany.aplicacionpoo.Model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoH2 implements CursoDao {
    
    private final Connection conn;
    
    public CursoDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Curso curso) {
        String sql = "INSERT INTO curso (id, nombre, programa_id, activo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, curso.getId());
            stmt.setString(2, curso.getNombre());
            stmt.setDouble(3, curso.getPrograma().getId());
            stmt.setBoolean(4, curso.isActivo());
            stmt.executeUpdate();
            System.out.println("✅ Curso guardado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (guardarCurso): " + e.getMessage());
        }
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Curso eliminado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (eliminarCurso): " + e.getMessage());
        }
    }
    
    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE curso SET nombre=?, programa_id=?, activo=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setDouble(2, curso.getPrograma().getId());
            stmt.setBoolean(3, curso.isActivo());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();
            System.out.println("✅ Curso actualizado en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (actualizarCurso): " + e.getMessage());
        }
    }
    
    @Override
    public List<Curso> mostrarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, nombre, programa_id, activo FROM curso";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setActivo(rs.getBoolean("activo"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (mostrarCursos): " + e.getMessage());
        }
        return cursos;
    }
    
    @Override
    public Curso buscar(int id) {
        String sql = "SELECT id, nombre, programa_id, activo FROM curso WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso c = new Curso();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setActivo(rs.getBoolean("activo"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (buscarCurso): " + e.getMessage());
        }
        return null;
    }
}