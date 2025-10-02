/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.dao.PersonaDao;
import com.mycompany.aplicacionpoo.Model.Persona;

import java.util.List;
/**
 *
 * @author steve
 */
public class PersonaService {


    private final PersonaDao GenericDao;

    public PersonaService() {
        // Obtiene el DAO adecuado según db.properties (H2, MySQL o Postgres)
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao =  factory.createPersonaDao();
    }

    public void guardarPersona(int id, String nombre, String apellido, String correo, String tipo) {
        Persona persona = new Persona(id, nombre, apellido, correo, tipo);
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.guardar(persona);
    }

    public void eliminarPersona(int id) {
        GenericDao.eliminar(id);
    }

    public void actualizarPersona(int id, String nombre, String apellido, String correo, String tipo) {
        Persona persona = new Persona(id, nombre, apellido, correo, tipo);
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        GenericDao.actualizar(persona);
    }

    public List<Persona> mostrarPersonas() {
        return GenericDao.mostrarTodos();
    }

    public Persona buscarPersona(int id) {
        return (Persona) GenericDao.buscar(id);
    }
}
