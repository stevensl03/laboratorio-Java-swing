/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.service.CursoService;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.awt.HeadlessException;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CursoController {

    private static CursoController instance;
    private final CursoService cursoService;

    private CursoController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.cursoService = factory.createCursoService();
    }
    
     public static synchronized CursoController getInstance(){
         if (instance == null){
            return new CursoController();
         }
         return instance;
     }
    
    // =============================
    // Mostrar todos los cursos
    // =============================
    public ArrayList<Curso> mostrarCurso() {
        try {
            List<Curso> cursos = cursoService.mostrarCurso();
            return new ArrayList<>(cursos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ No se pudieron mostrar los cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // =============================
    // Agregar curso
    // =============================
    public void agregarCurso(int id, String nombre, boolean estado, double idPrograma) {
        try {
            cursoService.guardarCurso(id, nombre, estado, idPrograma);
            JOptionPane.showMessageDialog(null, "✅ Curso agregado exitosamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al agregar curso: " + e.getMessage());
        }
    }

    // =============================
    // Eliminar curso
    // =============================
    public void eliminarCurso(int id) {
        try {
            cursoService.eliminarCurso(id);
            JOptionPane.showMessageDialog(null, "✅ Curso eliminado correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar curso: " + e.getMessage());
        }
    }

    // =============================
    // Buscar curso
    // =============================
    public Curso buscarCurso(int id) {
        try {
            Curso curso = cursoService.buscarCurso(id);
            if (curso != null) {
                return curso;
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró ningún curso con ese ID.");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar curso: " + e.getMessage());
        }
        return null;
    }

    // =============================
    // Actualizar curso
    // =============================
    public void actualizarCurso(int id, String nombre, boolean estado, double idPrograma) {
        try {
            cursoService.actualizarCurso(id, nombre, estado, idPrograma);
            JOptionPane.showMessageDialog(null, "✅ Curso actualizado correctamente.");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al actualizar curso: " + e.getMessage());
        }
    }
}
