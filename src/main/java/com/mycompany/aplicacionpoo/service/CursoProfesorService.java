/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import com.mycompany.aplicacionpoo.Model.Profesor;
import com.mycompany.aplicacionpoo.dao.CursoProfesorDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class CursoProfesorService {
    private final CursoProfesorDao GenericDao;
    private final ProfesorService profesorService;
    private final CursoService cursoService;
 
    
      public CursoProfesorService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.profesorService = factory.createProfesorService();
        this.cursoService = factory.createCursoService();
        this.GenericDao =  factory.createCursoProfesorDao();
    }

   
    public void guardarCursoProfesor(int año, int semestre, double idProfesor, int idCurso) {
        Profesor profesor = profesorService.buscarProfesor((int)idProfesor);
        Curso curso = cursoService.buscarCurso(idCurso);
        CursoProfesor cursoProfesor = new CursoProfesor( profesor, año, semestre, curso);
        
        if (cursoProfesor == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(cursoProfesor);
    }

    public void eliminarCursoProfesor(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarCursoProfesor(int año, int semestre, double idProfesor, int idCurso) {
        Profesor profesor = profesorService.buscarProfesor((int)idProfesor);
        Curso curso = cursoService.buscarCurso(idCurso);
        CursoProfesor cursoProfesor = new CursoProfesor( profesor, año, semestre, curso);
        
        if (cursoProfesor == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(cursoProfesor);
    }

    public List<CursoProfesor> mostrarCursoProfesor() {
        return GenericDao.mostrarTodos();
    }

    public CursoProfesor buscarCursoProfesor(int id) {
        return (CursoProfesor) GenericDao.buscar(id);
    }
}
