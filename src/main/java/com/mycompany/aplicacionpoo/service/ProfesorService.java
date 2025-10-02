/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.Model.Profesor;
import com.mycompany.aplicacionpoo.dao.ProfesorDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class ProfesorService {

    private final ProfesorDao GenericDao;
    private final PersonaService personaService;
    
      public ProfesorService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao =  factory.createProfesorDao();
        this.personaService = new PersonaService();
    }

    
    public void guardarProfesor(int id, String tipoContrato) {
        Persona persona = personaService.buscarPersona(id);
        Profesor profesor = new Profesor( id,  persona.getNombres(), persona.getApellidos(),  persona.getEmail(),  tipoContrato);
        
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(profesor);
    }

    public void eliminarProfesor(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarProfesor(int id, String tipoContrato) {
        Persona persona = personaService.buscarPersona(id);
        Profesor profesor = new Profesor( id,  persona.getNombres(), persona.getApellidos(),  persona.getEmail(),  tipoContrato);

        if (profesor == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(profesor);
    }

    public List<Profesor> mostrarProfesor() {
        return GenericDao.mostrarTodos();
    }

    public Profesor buscarProfesor(int id) {
        return (Profesor) GenericDao.buscar(id);
    }
}
