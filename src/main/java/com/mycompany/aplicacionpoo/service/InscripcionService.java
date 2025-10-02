/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import com.mycompany.aplicacionpoo.Model.Inscripcion;
import com.mycompany.aplicacionpoo.dao.InscripcionDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class InscripcionService {
     private final InscripcionDao GenericDao;
    private final EstudianteService estudianteService;
    private final CursoService cursoService;
    
      public InscripcionService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createInscripcionDao();
        this.estudianteService = factory.createEstudianteService();
        this.cursoService = factory.createCursoService();
    }

   
    public void guardarInscripcion(int año, int semestre, double idEstudiante, int idCurso) {
        Estudiante estudiante = estudianteService.buscarEstudiante((int)idEstudiante);
        Curso curso = cursoService.buscarCurso(idCurso);
        Inscripcion inscripcion = new Inscripcion( curso,  año,  semestre,  estudiante);
        
        if (inscripcion == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(inscripcion);
    }

    public void eliminarInscripcion(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarInscripcion(int año, int semestre, double idEstudiante, int idCurso) {
        Estudiante estudiante = estudianteService.buscarEstudiante((int)idEstudiante);
        Curso curso = cursoService.buscarCurso(idCurso);
        Inscripcion inscripcion = new Inscripcion( curso,  año,  semestre,  estudiante);
        
        if (inscripcion == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(inscripcion);
    }

    public List<Inscripcion> mostrarInscripcion() {
        return GenericDao.mostrarTodos();
    }

    public Inscripcion buscarInscripcion(int id) {
        return (Inscripcion) GenericDao.buscar(id);
    }
}
