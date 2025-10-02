
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Estudiante;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.EstudianteService;
import java.awt.HeadlessException;

import javax.swing.*;
import java.util.List;

public class EstudianteController {

    private static EstudianteController instance;
    private final EstudianteService estudianteService;

    private EstudianteController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.estudianteService = factory.createEstudianteService();
    }
    
    public static synchronized EstudianteController getInstance(){
        if (instance == null){
            return new EstudianteController();
        }
        return instance;
    }

    // Mostrar todos los estudiantes
    public List<Estudiante> mostrarEstudiante() {
        try {
            return estudianteService.mostrarEstudiante();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al mostrar estudiantes: " + e.getMessage());
            return List.of();
        }
    }

    // Guardar estudiante
    public void agregarEstudiante(double id, double codigo, double promedio, boolean estado, double idPrograma) {
        try {
            estudianteService.guardarEstudiante(id, codigo, promedio, estado, idPrograma);
            JOptionPane.showMessageDialog(null, "✅ Estudiante guardado correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "⚠️ " + e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al guardar estudiante: " + e.getMessage());
        }
    }

    // Actualizar estudiante
    public void actualizarEstudiante(double id, double codigo, double promedio, boolean estado, double idPrograma) {
        try {
            estudianteService.actualizarEstudiante(id, codigo, promedio, estado, idPrograma);
            JOptionPane.showMessageDialog(null, "✅ Estudiante actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "⚠️ " + e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al actualizar estudiante: " + e.getMessage());
        }
    }

    // Eliminar estudiante
    public void eliminarEstudiante(double id) {
        try {
            estudianteService.eliminarEstudiante((int) id);
            JOptionPane.showMessageDialog(null, "✅ Estudiante eliminado correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar estudiante: " + e.getMessage());
        }
    }

    // Buscar estudiante
    public Estudiante buscarEstudiante(double id) {
        try {
            Estudiante estudiante = estudianteService.buscarEstudiante((int) id);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el estudiante con ID " + id);
            }
            return estudiante;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar estudiante: " + e.getMessage());
            return null;
        }
    }
}

