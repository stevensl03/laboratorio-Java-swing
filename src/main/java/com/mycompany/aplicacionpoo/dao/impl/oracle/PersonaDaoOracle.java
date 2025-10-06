package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.PersonaDao;
import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoOracle implements PersonaDao {
 
    private static InternalFactory factory;
    private final Connection conn;

    public PersonaDaoOracle(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }

    @Override
    public void guardar(Persona persona) {
        String sql = "INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, persona.getId());
            stmt.setString(2, persona.getNombres()); 
            stmt.setString(3, persona.getApellidos()); 
            stmt.setString(4, persona.getEmail()); 
            stmt.setString(5, persona.getTipo());
            stmt.executeUpdate();
            System.out.println("✅ Persona guardada en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (guardarPersona): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Persona eliminada en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (eliminarPersona): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE persona SET nombre=?, apellido=?, correo=?, tipo=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombres());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTipo());
            stmt.setDouble(5, persona.getId());
            stmt.executeUpdate();
            System.out.println("✅ Persona actualizada en H2");
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (actualizarPersona): " + e.getMessage());
        }
    }

    @Override
    public List<Persona> mostrarTodos() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, correo, tipo FROM persona";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona p = factory.createPersona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("tipo")
                );
                personas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (mostrarPersonas): " + e.getMessage());
        }
        return personas;
    }

    @Override
    public Persona buscar(int id) {
        String sql = "SELECT id, nombre, apellido, correo, tipo FROM persona WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return factory.createPersona(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en H2 (buscarPersona): " + e.getMessage());
        }
        return null;
    }

}