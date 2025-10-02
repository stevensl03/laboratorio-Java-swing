
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.ProgramaService;

import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ProgramaController {

    private final ProgramaService programaService;

    public ProgramaController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.programaService = factory.createProgramaService();
    }

    // Usado en ProgramaView para cargar la tabla
    public List<Programa> mostrarPrograma() {
        try {
            return programaService.mostrarPrograma();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar programas: " + e.getMessage());
            return List.of();
        }
    }

    // Usado en guardarActionPerformed
    public void guardarPrograma(double id, String nombre, double duracion, Date registro, double idFacultad) {
        try {
            programaService.guardarPrograma(id, nombre, duracion, registro, idFacultad);
            JOptionPane.showMessageDialog(null, "Programa guardado exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar programa: " + e.getMessage());
        }
    }

    // Usado en actualizarActionPerformed
    public void actualizarPrograma(double id, String nombre, double duracion, Date registro, double idFacultad) {
        try {
            programaService.actualizarPrograma(id, nombre, duracion, registro, idFacultad);
            JOptionPane.showMessageDialog(null, "Programa actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar programa: " + e.getMessage());
        }
    }

    // Usado en eliminarActionPerformed
    public void eliminarPrograma(double id) {
        try {
            programaService.eliminarPrograma((int) id);
            JOptionPane.showMessageDialog(null, "Programa eliminado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar programa: " + e.getMessage());
        }
    }

    // Usado en buscarActionPerformed
    public Programa buscarPrograma(double id) {
        try {
            Programa programa = programaService.buscarPrograma((int) id);
            if (programa == null) {
                JOptionPane.showMessageDialog(null, "No se encontró programa con ese ID.");
            }
            return programa;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar programa: " + e.getMessage());
            return null;
        }
    }
}

