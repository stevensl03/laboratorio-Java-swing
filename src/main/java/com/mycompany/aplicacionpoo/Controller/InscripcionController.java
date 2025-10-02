
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Inscripcion;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.InscripcionService;
import java.awt.HeadlessException;

import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionController {

    private static InscripcionController instance;
    private final InscripcionService inscripcionService;

    private InscripcionController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.inscripcionService = factory.createInscripcionService();
    }
    
    public static synchronized InscripcionController getInstance(){
        if (instance == null){
            return new InscripcionController();
        }
        return instance;
    }

    // Mostrar todas las inscripciones
    public List<Inscripcion> mostrarInscripcion() {
        try {
            return inscripcionService.mostrarInscripcion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar inscripciones: " + e.getMessage());
            return List.of();
        }
    }

    // Guardar una nueva inscripción
    public void agregarInscripcion(int año, int semestre, double idEstudiante, int idCurso) {
        try {
            inscripcionService.guardarInscripcion(año, semestre, idEstudiante, idCurso);
            JOptionPane.showMessageDialog(null, "Inscripción agregada exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar inscripción: " + e.getMessage());
        }
    }

    // Actualizar inscripción
    public void actualizarInscripcion(int año, int semestre, double idEstudiante, int idCurso) {
        try {
            inscripcionService.actualizarInscripcion(año, semestre, idEstudiante, idCurso);
            JOptionPane.showMessageDialog(null, "Inscripción actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar inscripción: " + e.getMessage());
        }
    }

    // Eliminar inscripción
    public void eliminarInscripcion(double idEstudiante, int idCurso) {
        try {
            // ⚠️ Aquí habría que adaptar GenericDao.eliminar() para manejar 2 IDs compuestos.
            // Por ahora lo dejamos genérico con el ID del estudiante.
            inscripcionService.eliminarInscripcion((int) idEstudiante);
            JOptionPane.showMessageDialog(null, "Inscripción eliminada correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inscripción: " + e.getMessage());
        }
    }

    // Buscar inscripción por estudiante y curso
    public Inscripcion buscarInscripcion(double idEstudiante, int idCurso) {
        try {
            // ⚠️ Igual que en eliminar, el DAO debería aceptar clave compuesta.
            Inscripcion inscripcion = inscripcionService.buscarInscripcion((int) idEstudiante);
            if (inscripcion == null) {
                JOptionPane.showMessageDialog(null, "No se encontró inscripción con ese ID.");
            }
            return inscripcion;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar inscripción: " + e.getMessage());
            return null;
        }
    }
}

