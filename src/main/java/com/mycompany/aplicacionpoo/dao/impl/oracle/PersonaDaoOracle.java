package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.PersonaDao;
import com.mycompany.aplicacionpoo.Model.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoOracle implements PersonaDao {
    private Connection connection;

    public PersonaDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Persona persona) {
        String sql = "INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, persona.getId());
            stmt.setString(2, persona.getNombres());
            stmt.setString(3, persona.getApellidos());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error en Oracle (guardarPersona): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error en Oracle (eliminarPersona): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE persona SET nombre = ?, apellido = ?, correo = ?, tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombres());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTipo());
            stmt.setDouble(5, persona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error en Oracle (actualizarPersona): " + e.getMessage());
        }
    }

    @Override
    public List<Persona> mostrarTodos() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getDouble("id"));
                persona.setNombres(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellido"));
                persona.setEmail(rs.getString("correo"));
                persona.setTipo(rs.getString("tipo"));
                lista.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en Oracle (mostrarPersonas): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Persona buscar(int id) {
        String sql = "SELECT id, nombre, apellido, correo, tipo FROM persona WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Persona(
                        rs.getDouble("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en Oracle (buscarPersona): " + e.getMessage());
        }
        return null;
    }
}
