/*
 * Factory Interna - Crea objetos para las capas de Modelo
 * Maneja la creación de entidades, servicios y objetos de negocio
 */
package com.mycompany.aplicacionpoo.factory.factoryInterna;

import com.mycompany.aplicacionpoo.Model.*;
import com.mycompany.aplicacionpoo.service.*;
import com.mycompany.aplicacionpoo.dao.*;
import com.mycompany.aplicacionpoo.dao.impl.h2.*;
import com.mycompany.aplicacionpoo.dao.impl.mysql.*;
import com.mycompany.aplicacionpoo.dao.impl.oracle.*;
import com.mycompany.aplicacionpoo.adapter.DatabaseAdapter;
import java.sql.Date;

/**
 * Factory Interna - Patrón Factory para objetos de la capa de modelo
 * Se encarga de crear entidades, servicios y objetos de negocio
 */
public class InternalFactory {
    
    private static InternalFactory instance;
    private final DatabaseAdapter databaseAdapter;
    
    private InternalFactory() {
        this.databaseAdapter = DatabaseAdapter.getInstance();
    }
    
    public static synchronized InternalFactory getInstance() {
        if (instance == null) {
            instance = new InternalFactory();
        }
        return instance;
    }
    
    // ========== CREACIÓN DE ENTIDADES ==========
    
    /**
     * Crea una entidad Persona
     */
    public Persona createPersona(int id, String nombres, String apellidos, String email, String tipo) {
        return new Persona(id, nombres, apellidos, email, tipo);
    }
    
    /**
     * Crea una entidad Persona vacía
     */
    public Persona createPersona() {
        return new Persona();
    }
    
    /**
     * Crea una entidad Estudiante
     */
    public Estudiante createEstudiante(int id, String nombres, String apellidos, String email,
                                     int codigo, Programa programa, boolean activo, double promedio) {
        return new Estudiante(id, nombres, apellidos, email, codigo, programa, activo, promedio);
    }
    
    /**
     * Crea una entidad Estudiante vacía
     */
    public Estudiante createEstudiante() {
        return new Estudiante();
    }
    
    /**
     * Crea una entidad Profesor
     */
    public Profesor createProfesor(int id, String nombres, String apellidos, String email, String categoria) {
        return new Profesor(id, nombres, apellidos, email, categoria);
    }
    
    /**
     * Crea una entidad Profesor vacía
     */
    public Profesor createProfesor() {
        return new Profesor();
    }
    
    /**
     * Crea una entidad Facultad
     */
    public Facultad createFacultad(int id, String nombre, Persona decano) {
        return new Facultad(id, nombre, decano);
    }
    
    /**
     * Crea una entidad Facultad vacía
     */
    public Facultad createFacultad() {
        return new Facultad();
    }
    
    /**
     * Crea una entidad Programa
     */
    public Programa createPrograma(int id, String nombre, int creditos, Date fechaCreacion, Facultad facultad) {
        return new Programa(id, nombre, creditos, fechaCreacion, facultad);
    }
    
    /**
     * Crea una entidad Programa vacía
     */
    public Programa createPrograma() {
        return new Programa();
    }
    
    /**
     * Crea una entidad Curso
     */
    public Curso createCurso(int id, String nombre, Programa programa, boolean activo) {
        return new Curso(id, nombre, programa, activo);
    }
    
    /**
     * Crea una entidad Curso vacía
     */
    public Curso createCurso() {
        return new Curso();
    }
    
    /**
     * Crea una entidad Inscripcion
     */
    public Inscripcion createInscripcion(Curso curso, int año, int semestre, Estudiante estudiante) {
        return new Inscripcion(curso, año, semestre, estudiante);
    }
    
    /**
     * Crea una entidad Inscripcion vacía
     */
    public Inscripcion createInscripcion() {
        return new Inscripcion();
    }
    
    /**
     * Crea una entidad CursoProfesor
     */
    public CursoProfesor createCursoProfesor(Profesor profesor, int año, int semestre, Curso curso) {
        return new CursoProfesor(profesor, año, semestre, curso);
    }
    
    /**
     * Crea una entidad CursoProfesor vacía
     */
    public CursoProfesor createCursoProfesor() {
        return new CursoProfesor();
    }
    
    // ========== CREACIÓN DE SERVICIOS ==========
    
    /**
     * Crea un PersonaService
     */
    public PersonaService createPersonaService() {
        return new PersonaService();
    }
    
    /**
     * Crea un EstudianteService
     */
    public EstudianteService createEstudianteService() {
        return new EstudianteService();
    }
    
    /**
     * Crea un ProfesorService
     */
    public ProfesorService createProfesorService() {
        return new ProfesorService();
    }
    
    /**
     * Crea un CursoService
     */
    public CursoService createCursoService() {
        return new CursoService();
    }
    
    /**
     * Crea un FacultadService
     */
    public FacultadService createFacultadService() {
        return new FacultadService();
    }
    
    /**
     * Crea un ProgramaService
     */
    public ProgramaService createProgramaService() {
        return new ProgramaService();
    }
    
    /**
     * Crea un InscripcionService
     */
    public InscripcionService createInscripcionService() {
        return new InscripcionService();
    }
    
    /**
     * Crea un CursoProfesorService
     */
    public CursoProfesorService createCursoProfesorService() {
        return new CursoProfesorService();
    }
    
    // ========== CREACIÓN DE DAOs ==========
    
    /**
     * Crea un PersonaDao según la base de datos configurada
     */
    public PersonaDao createPersonaDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.PersonaDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.PersonaDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new PersonaDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un EstudianteDao según la base de datos configurada
     */
    public EstudianteDao createEstudianteDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.EstudianteDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.EstudianteDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new EstudianteDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un ProfesorDao según la base de datos configurada
     */
    public ProfesorDao createProfesorDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.ProfesorDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.ProfesorDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new ProfesorDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un CursoDao según la base de datos configurada
     */
    public CursoDao createCursoDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.CursoDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.CursoDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new CursoDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un FacultadDao según la base de datos configurada
     */
    public FacultadDao createFacultadDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.FacultadDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.FacultadDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new FacultadDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un ProgramaDao según la base de datos configurada
     */
    public ProgramaDao createProgramaDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.ProgramaDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.ProgramaDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new ProgramaDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un InscripcionDao según la base de datos configurada
     */
    public InscripcionDao createInscripcionDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.InscripcionDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.InscripcionDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new InscripcionDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    /**
     * Crea un CursoProfesorDao según la base de datos configurada
     */
    public CursoProfesorDao createCursoProfesorDao() {
        String dbType = databaseAdapter.getDatabaseType();
        
        switch (dbType) {
            case "H2":
                return new com.mycompany.aplicacionpoo.dao.impl.h2.CursoProfesorDaoH2(databaseAdapter.getConnection());
            case "MYSQL":
                return new com.mycompany.aplicacionpoo.dao.impl.mysql.CursoProfesorDaoMySQL(databaseAdapter.getConnection());
            case "ORACLE":
                return new CursoProfesorDaoOracle(databaseAdapter.getConnection());
            default:
                throw new RuntimeException("Tipo de base de datos no soportado: " + dbType);
        }
    }
    
    // ========== MÉTODOS DE UTILIDAD ==========
    
    /**
     * Obtiene el adaptador de base de datos
     */
    public DatabaseAdapter getDatabaseAdapter() {
        return databaseAdapter;
    }
    
    /**
     * Obtiene la función de fecha actual según la base de datos
     */
    public String getCurrentDateFunction() {
        return databaseAdapter.getCurrentDateFunction();
    }
    
    /**
     * Obtiene la función de tiempo actual según la base de datos
     */
    public String getCurrentTimeFunction() {
        return databaseAdapter.getCurrentTimeFunction();
    }
    
    /**
     * Valida si una entidad es válida
     */
    public boolean isValidEntity(Persona persona) {
        return persona != null && 
               persona.getNombres() != null && !persona.getNombres().trim().isEmpty() &&
               persona.getApellidos() != null && !persona.getApellidos().trim().isEmpty() &&
               persona.getEmail() != null && !persona.getEmail().trim().isEmpty();
    }
    
    /**
     * Valida si una entidad es válida
     */
    public boolean isValidEntity(Estudiante estudiante) {
        return estudiante != null && 
               isValidEntity(estudiante) &&
               estudiante.getCodigo() > 0 &&
               estudiante.getPrograma() != null;
    }


}
