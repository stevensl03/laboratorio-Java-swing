/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.factory.factoryInterno.DaoFactory;
import com.mycompany.aplicacionpoo.dao.PersonaDao;
import com.mycompany.aplicacionpoo.Model.Persona;

import java.util.List;
/**
 *
 * @author steve
 */
public class PersonaService {


    private final PersonaDao personaDAO;

    public PersonaService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        this.personaDAO = DaoFactory.getPersonaDao();
    }

    public void guardarPersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        personaDAO.guardarPersona(persona);
    }

    public void eliminarPersona(int id) {
        personaDAO.eliminarPersona(id);
    }

    public void actualizarPersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        personaDAO.actualizarPersona(persona);
    }

    public List<Persona> mostrarPersonas() {
        return personaDAO.mostrarPersonas();
    }

    public Persona buscarPersona(int id) {
        return personaDAO.buscarPersona(id);
    }


 
}
