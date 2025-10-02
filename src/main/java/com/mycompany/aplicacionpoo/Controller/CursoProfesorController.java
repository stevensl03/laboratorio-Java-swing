
package com.mycompany.aplicacionpoo.Controller;

import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.service.CursoProfesorService;
import java.util.List;

public class CursoProfesorController {

    private final CursoProfesorService cursoProfesorService;

    public CursoProfesorController() {
        InternalFactory factory = InternalFactory.getInstance();
        this.cursoProfesorService = factory.createCursoProfesorService();
    }

    // Mostrar todos
    public List<CursoProfesor> mostrarCursoProfesor() {
        return cursoProfesorService.mostrarCursoProfesor();
    }

    // Agregar (usado en guardarActionPerformed)
    public void agregarCursoProfesor(int año, int semestre, double idProfesor, int idCurso) {
        cursoProfesorService.guardarCursoProfesor(año, semestre, idProfesor, idCurso);
    }

    // Actualizar
    public void actualizarCursoProfesor(int año, int semestre, double idProfesor, int idCurso) {
        cursoProfesorService.actualizarCursoProfesor(año, semestre, idProfesor, idCurso);
    }

    // Eliminar (usa profesor y curso como en tu vista)
    public void eliminarCursoProfesor(double idProfesor, int idCurso) {
        cursoProfesorService.eliminarCursoProfesor(idCurso);
    }

    // Buscar (también con profesor y curso)
    public CursoProfesor buscarCursoProfesor(double idProfesor, int idCurso) {
        return cursoProfesorService.buscarCursoProfesor(idCurso);
    }
}

