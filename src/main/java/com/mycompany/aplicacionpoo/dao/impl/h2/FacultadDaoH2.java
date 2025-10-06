/*
 * Implementación H2 del DAO para Facultad
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.dao.FacultadDao;
import com.mycompany.aplicacionpoo.Model.Facultad;
import com.mycompany.aplicacionpoo.Model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FacultadDaoH2 implements FacultadDao {
    
    private static InternalFactory factory;
    private final Connection conn;
    
    public FacultadDaoH2(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
    
        @Override
    public void guardar(Facultad facultad) {
        String validarSql = "SELECT tipo FROM persona WHERE id = ?";
        String sql = "INSERT INTO facultad (id, nombre, decano_id) VALUES (?, ?, ?)";

        try (PreparedStatement stmtValidar = conn.prepareStatement(validarSql)) {
            stmtValidar.setInt(1, (int) facultad.getDecano().getId());
            try (ResultSet rs = stmtValidar.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if (!"profesor".equalsIgnoreCase(tipo)) {
                        JOptionPane.showMessageDialog(null,
                                "El ID corresponde a una persona tipo " + tipo +
                                        ", no puede agregarse como decano.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ninguna Persona con ese ID.");
                    return;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar decano: " + ex.getMessage());
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (int) facultad.getId());
            stmt.setString(2, facultad.getNombre());
            stmt.setInt(3, (int) facultad.getDecano().getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Facultad agregada exitosamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar Facultad: " + ex.getMessage());
        }
    }
    
    @Override
    public void actualizar(Facultad facultad) {
        String sql = "UPDATE facultad SET decano_id = ?, nombre = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (int) facultad.getDecano().getId());
            stmt.setString(2, facultad.getNombre());
            stmt.setInt(3, (int) facultad.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Facultad actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe Facultad con ese ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Facultad: " + ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM facultad WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Facultad eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe ninguna Facultad con ese ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Facultad: " + ex.getMessage());
        }
    }

    @Override
    public List<Facultad> mostrarTodos() {
        List<Facultad> lista = new ArrayList<>();
        String sql = """
                SELECT facultad.id, facultad.nombre,
                       persona.id AS id_decano, persona.nombre AS nombres_decano
                FROM persona
                INNER JOIN facultad ON persona.id = facultad.decano_id
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Facultad facultad = factory.createFacultad();
                facultad.setId(rs.getInt("id"));
                facultad.setNombre(rs.getString("nombre"));

                Persona decano = factory.createPersona();
                decano.setId(rs.getInt("id_decano"));
                decano.setNombres(rs.getString("nombres_decano"));

                facultad.setDecano(decano);
                lista.add(facultad);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar Facultad: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Facultad buscar(int id) {
        String sql = """
                SELECT persona.id, persona.nombre, persona.apellido, persona.correo,
                       facultad.nombre AS nombre_facultad, facultad.id AS id_facultad
                FROM persona
                INNER JOIN facultad ON persona.id = facultad.decano_id
                WHERE facultad.id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Facultad facultad = factory.createFacultad();
                facultad.setId(rs.getInt("id_facultad"));
                facultad.setNombre(rs.getString("nombre_facultad"));

                Persona decano = factory.createPersona();
                decano.setId(rs.getInt("id"));
                decano.setNombres(rs.getString("nombre"));
                decano.setApellidos(rs.getString("apellido"));
                decano.setEmail(rs.getString("correo"));

                facultad.setDecano(decano);
                return facultad;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Facultad: " + ex.getMessage());
        }
        return null;
    }



}
