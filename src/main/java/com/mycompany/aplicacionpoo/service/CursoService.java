/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.dao.CursoDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class CursoService {
    private final CursoDao GenericDao;
    private final ProgramaService programaService;
    
     public CursoService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createCursoDao();
        this.programaService = factory.createProgramaService();
    }

   
    public void guardarCurso( int id, String nombre, boolean estado, double idPrograma) {
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Curso curso = new Curso( id, nombre,  programa, estado);
        
        if (curso == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(curso);
    }

    public void eliminarCurso(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarCurso(int id, String nombre, boolean estado, double idPrograma) {
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Curso curso = new Curso( id, nombre,  programa, estado);
        
        if (curso == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(curso);
    }

    public List<Curso> mostrarCurso() {
        return GenericDao.mostrarTodos();
    }

    public Curso buscarCurso(int id) {
        return (Curso) GenericDao.buscar(id);
    }
}
