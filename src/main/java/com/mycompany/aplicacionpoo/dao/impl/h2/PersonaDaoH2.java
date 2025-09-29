/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.PersonaDao;
import com.mycompany.aplicacionpoo.Model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author steve
 */
public class PersonaDaoH2 implements PersonaDao {
    
    private final Connection conn;

    public PersonaDaoH2(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void guardarPersona(Persona persona) {
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
    public void eliminarPersona(int id) {
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
    public void actualizarPersona(Persona persona) {
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
    public List<Persona> mostrarPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, correo, tipo FROM persona";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona p = new Persona(
                        rs.getDouble("id"),
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
    public Persona buscarPersona(int id) {
        String sql = "SELECT id, nombre, apellido, correo, tipo FROM persona WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
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
            System.out.println("❌ Error en H2 (buscarPersona): " + e.getMessage());
        }
        return null;
    }

}
