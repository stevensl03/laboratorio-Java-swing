/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;


import com.mycompany.aplicacionpoo.Model.Facultad;
import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.dao.ProgramaDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author steve
 */
public class ProgramaService {
    private final ProgramaDao GenericDao;
    private final FacultadService facultadService;
    
      public ProgramaService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createProgramaDao();
        this.facultadService = factory.createFacultadService();
    }

    
    public void guardarPrograma(double id, String nombrePrograma, double duracion, Date registro, double idFacultad) {
        Facultad facultad = facultadService.buscarFacultad((int)idFacultad);
        Programa programa = new Programa(id, nombrePrograma, duracion, registro, facultad );
        
        if (programa == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(programa);
    }

    public void eliminarPrograma(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarPrograma(double id, String nombrePrograma, double duracion, Date registro, double idFacultad) {
        Facultad facultad = facultadService.buscarFacultad((int)idFacultad);
        Programa programa = new Programa(id, nombrePrograma, duracion, registro, facultad );
        
        if (programa == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(programa);
    }

    public List<Programa> mostrarPrograma() {
        return GenericDao.mostrarTodos();
    }

    public Programa buscarPrograma(int id) {
        return (Programa) GenericDao.buscar(id);
    }
}
