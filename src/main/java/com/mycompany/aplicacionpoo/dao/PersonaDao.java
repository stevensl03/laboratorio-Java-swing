/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.aplicacionpoo.dao;
import java.util.List;
import com.mycompany.aplicacionpoo.Model.Persona;
/**
 *
 * @author steve
 */
public interface PersonaDao {
    // Crear
    void guardarPersona(Persona persona);
    // Eliminar por ID 
    void eliminarPersona(int id);
    // Actualizar registro
    void actualizarPersona(Persona persona);
    // Consultar todos los registros
    List<Persona> mostrarPersonas(); 
    // Consultar por ID 
    Persona buscarPersona(int id);
}
