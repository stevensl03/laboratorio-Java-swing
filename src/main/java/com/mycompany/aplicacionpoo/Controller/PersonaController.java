
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.PersonaService;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {

    private final PersonaService personaService;

    public PersonaController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.personaService = factory.createPersonaService();
    }

    public void guardarPersona(int id, String nombre, String apellido, String correo, String tipo) {
        try {   
            personaService.guardarPersona(id, nombre, apellido, correo, tipo);
            JOptionPane.showMessageDialog(null, "Agregado correctamente");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar persona: " + e.getMessage());
        }
    }

    public void eliminarPersona(int id) {
        try {
            personaService.eliminarPersona(id);
            JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }

    public void actualizarPersona(int id, String nombre, String apellido, String correo, String tipo) {
        try {
            personaService.actualizarPersona(id, nombre, apellido, correo, tipo);
            JOptionPane.showMessageDialog(null, "Persona actualizada correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    }

    public List<Persona> mostrarPersona() {
        try {
            return personaService.mostrarPersonas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar personas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Persona buscarPersona(int id) {
        try {
            Persona persona = personaService.buscarPersona(id);
            if (persona != null) {
                return persona;
            } else {
                JOptionPane.showMessageDialog(null, "No existe ninguna persona con ese ID.");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e.getMessage());
        }
        return null;
    }
}

