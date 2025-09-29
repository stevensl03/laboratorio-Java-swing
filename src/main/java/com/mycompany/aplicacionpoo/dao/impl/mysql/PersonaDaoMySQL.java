/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;
import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.dao.PersonaDao;
import java.sql.*;
import java.util.ArrayList; 
import java.util.List;
/**
 *
 * @author steve
 */
public class PersonaDaoMySQL implements PersonaDao{
    private final Connection conn;
    public PersonaDaoMySQL(Connection conn) { this.conn = conn; }
    
    @Override 
    public void guardarPersona(Persona persona) {
        String sql = "INSERT INTO persona (id, nombre, apellido, correo, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (int) persona.getId());
            stmt.setString(2, persona.getNombres());
            stmt.setString(3, persona.getApellidos());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error en MySQL (guardarPersona): " + e.getMessage());
        } 
    } 
    
    @Override
    public void eliminarPersona(int id) { String sql = "DELETE FROM persona WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) { stmt.setInt(1, id); stmt.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("❌ Error en MySQL (eliminarPersona): " + e.getMessage());
        }
    }
    
    @Override
    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE persona SET nombre=?, apellido=?, correo=?, tipo=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombres());
            stmt.setString(2, persona.getApellidos()); stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTipo()); stmt.setInt(5, (int) persona.getId()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error en MySQL (actualizarPersona): " + e.getMessage());
        } 
    }
    
    @Override 
    public List<Persona> mostrarPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona"; 
        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { Persona persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombres(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellido"));
                persona.setEmail(rs.getString("correo"));
                persona.setTipo(rs.getString("tipo")); lista.add(persona);
            } 
        }
        catch (SQLException e) {
            System.out.println("❌ Error en MySQL (mostrarPersonas): " + e.getMessage());
        } return lista;
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
            System.out.println("❌ Error en MySQL (buscarPersona): " + e.getMessage());
        }
        return null;
    }

}
