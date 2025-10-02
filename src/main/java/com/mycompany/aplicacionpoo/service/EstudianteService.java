/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Estudiante;
import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.dao.EstudianteDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class EstudianteService {
    private final EstudianteDao GenericDao;
    private final PersonaService personaService;
    private final ProgramaService programaService;
    
      public EstudianteService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createEstudianteDao();
        this.personaService = factory.createPersonaService();
        this.programaService = factory.createProgramaService();
    }

   
    public void guardarEstudiante( double id, double codigo, double promedio, boolean estado, double idPrograma) {
        Persona persona = personaService.buscarPersona((int)id);
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Estudiante estudiante = new Estudiante(id,  persona.getNombres(),  persona.getApellidos(),  persona.getEmail(),  codigo,  programa,  estado,  promedio);
        
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(estudiante);
    }

    public void eliminarEstudiante(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarEstudiante(double id, double codigo, double promedio, boolean estado, double idPrograma) {
        Persona persona = personaService.buscarPersona((int)id);
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Estudiante estudiante = new Estudiante(id,  persona.getNombres(),  persona.getApellidos(),  persona.getEmail(),  codigo,  programa,  estado,  promedio);
        
        if (estudiante == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(estudiante);
    }

    public List<Estudiante> mostrarEstudiante() {
        return GenericDao.mostrarTodos();
    }

    public Estudiante buscarEstudiante(int id) {
        return (Estudiante) GenericDao.buscar(id);
    }
}
