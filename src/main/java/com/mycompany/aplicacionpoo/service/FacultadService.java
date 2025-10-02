/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Facultad;
import com.mycompany.aplicacionpoo.Model.Persona;
import com.mycompany.aplicacionpoo.dao.FacultadDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.util.List;

/**
 *
 * @author steve
 */
public class FacultadService {
    private final FacultadDao GenericDao;
    private final PersonaService personaService;
    
      public FacultadService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createFacultadDao();
        this.personaService = factory.createPersonaService();
    }

    
    public void guardarFacultad(int id, String nombreFacultad, int idDecano) {
        Persona persona = personaService.buscarPersona(idDecano);
        Facultad facultad = new Facultad(id, nombreFacultad, persona );
        
        if (facultad == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(facultad);
    }

    public void eliminarFacultad(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarFacultad(int id, String nombreFacultad, int idDecano) {
        Persona persona = personaService.buscarPersona(idDecano);
        Facultad facultad = new Facultad(id, nombreFacultad, persona );
        
        if (facultad == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(facultad);
    }

    public List<Facultad> mostrarFacultad() {
        return GenericDao.mostrarTodos();
    }

    public Facultad buscarFacultad(int id) {
        return (Facultad) GenericDao.buscar(id);
    }
}
