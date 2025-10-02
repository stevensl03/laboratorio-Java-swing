/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Profesor;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.ProfesorService;
import java.awt.HeadlessException;

import java.util.List;
import javax.swing.JOptionPane;

public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.profesorService = factory.createProfesorService();
    }

    public void agregarProfesor(int id, String tipoContrato) {
        try {
            profesorService.guardarProfesor(id, tipoContrato);
            JOptionPane.showMessageDialog(null, "Profesor agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }

    public void actualizarProfesor(int id, String tipoContrato) {
        try {
            profesorService.actualizarProfesor(id, tipoContrato);
            JOptionPane.showMessageDialog(null, "Profesor actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    }

    public List<Profesor> mostrarProfesor() {
        return profesorService.mostrarProfesor();
    }

    public void eliminarProfesor(int id) {
        try {
            profesorService.eliminarProfesor(id);
            JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }

    public Profesor buscarProfesor(int id) {
        try {
            Profesor profesor = profesorService.buscarProfesor(id);
            if (profesor == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún profesor con ese ID.");
            }
            return profesor;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e.getMessage());
            return null;
        }
    }
}
