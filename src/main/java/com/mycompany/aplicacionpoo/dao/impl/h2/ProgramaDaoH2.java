/*
 * Implementación H2 del DAO para Programa
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.Model.Facultad;
import com.mycompany.aplicacionpoo.dao.ProgramaDao;
import com.mycompany.aplicacionpoo.Model.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDaoH2 implements ProgramaDao {
    
    private static InternalFactory factory;
    private final Connection conn;
    
    public ProgramaDaoH2(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
    
    @Override
    public void guardar(Programa programa) {
        String sql = "INSERT INTO programa (id, nombre, duracion, registro, facultad_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (int) programa.getId());
            stmt.setString(2, programa.getNombre());
            stmt.setDouble(3, programa.getDuracion());
            stmt.setDate(4, (Date) programa.getRegistro());
            stmt.setInt(5, (int) programa.getFacultad().getId());
            stmt.executeUpdate();
            System.out.println("✅ Programa guardado");
        } catch (SQLException e) {
            System.out.println("❌ Error (guardarPrograma): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Programa programa) {
        String sql = "UPDATE programa SET nombre = ?, duracion = ?, registro = ?, facultad_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programa.getNombre());
            stmt.setDouble(2, programa.getDuracion());
            stmt.setDate(3, (Date) programa.getRegistro());
            stmt.setInt(4, (int) programa.getFacultad().getId());
            stmt.setInt(5, (int) programa.getId());
            stmt.executeUpdate();
            System.out.println("✅ Programa actualizado");
        } catch (SQLException e) {
            System.out.println("❌ Error (actualizarPrograma): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM programa WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Programa eliminado");
        } catch (SQLException e) {
            System.out.println("❌ Error (eliminarPrograma): " + e.getMessage());
        }
    }

    @Override
    public List<Programa> mostrarTodos() {
        List<Programa> listaPrograma = new ArrayList<>();
        String sql = """
                     SELECT pr.id, pr.nombre, pr.duracion, pr.registro,
                            f.id AS id_facultad, f.nombre AS nombre_facultad
                     FROM programa pr
                     INNER JOIN facultad f ON pr.facultad_id = f.id
                     """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Programa programa = factory.createPrograma();
                programa.setId(rs.getInt("id"));
                programa.setNombre(rs.getString("nombre"));
                programa.setDuracion(rs.getDouble("duracion"));
                programa.setRegistro(rs.getDate("registro"));

                Facultad facultad = factory.createFacultad();
                facultad.setId(rs.getInt("id_facultad"));
                facultad.setNombre(rs.getString("nombre_facultad"));

                programa.setFacultad(facultad);
                listaPrograma.add(programa);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error (mostrarPrograma): " + e.getMessage());
        }
        return listaPrograma;
    }

    @Override
    public Programa buscar(int id) {
        String sql = """
                     SELECT pr.id, pr.nombre, pr.duracion, pr.registro,
                            f.id AS id_facultad, f.nombre AS nombre_facultad
                     FROM programa pr
                     INNER JOIN facultad f ON pr.facultad_id = f.id
                     WHERE pr.id = ?
                     """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Programa programa = factory.createPrograma();
                    programa.setId(rs.getInt("id"));
                    programa.setNombre(rs.getString("nombre"));
                    programa.setDuracion(rs.getDouble("duracion"));
                    programa.setRegistro(rs.getDate("registro"));

                    Facultad facultad = factory.createFacultad();
                    facultad.setId(rs.getInt("id_facultad"));
                    facultad.setNombre(rs.getString("nombre_facultad"));

                    programa.setFacultad(facultad);
                    return programa;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (buscarPrograma): " + e.getMessage());
        }
        return null;
    }
}
