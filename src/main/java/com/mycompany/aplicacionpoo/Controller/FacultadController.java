
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Facultad;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.FacultadService;
import java.awt.HeadlessException;

import javax.swing.*;
import java.util.List;

public class FacultadController {

    private final FacultadService facultadService;

    public FacultadController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.facultadService = factory.createFacultadService();
    }

    // Usado en FacultadView para cargar la tabla
    public List<Facultad> mostrarFacultad() {
        try {
            return facultadService.mostrarFacultad();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al mostrar facultades: " + e.getMessage());
            return null;
        }
    }

    // Guardar (llamado como agregarFacultad en el View)
    public void agregarFacultad(int id, String nombreFacultad, int idDecano) {
        try {
            facultadService.guardarFacultad(id, nombreFacultad, idDecano);
            JOptionPane.showMessageDialog(null, "✅ Facultad guardada correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "⚠️ " + e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al guardar facultad: " + e.getMessage());
        }
    }

    // Actualizar
    public void actualizarFacultad(int id, String nombreFacultad, int idDecano) {
        try {
            facultadService.actualizarFacultad(id, nombreFacultad, idDecano);
            JOptionPane.showMessageDialog(null, "✅ Facultad actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "⚠️ " + e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al actualizar facultad: " + e.getMessage());
        }
    }

    // Eliminar
    public void eliminarFacultad(int id) {
        try {
            facultadService.eliminarFacultad(id);
            JOptionPane.showMessageDialog(null, "✅ Facultad eliminada correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar facultad: " + e.getMessage());
        }
    }

    // Buscar
    public Facultad buscarFacultad(int id) {
        try {
            return facultadService.buscarFacultad(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar facultad: " + e.getMessage());
            return null;
        }
    }
}


