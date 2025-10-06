package com.mycompany.aplicacionpoo.factory.factoryExterna;

import com.mycompany.aplicacionpoo.dto.*;
import com.mycompany.aplicacionpoo.Controller.*;
import com.mycompany.aplicacionpoo.View.*;
import com.mycompany.aplicacionpoo.Model.*;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.observer.CursoConsoleObserver;

public class ExternalFactory {
    
    private static ExternalFactory instance;
    
    private ExternalFactory() {}
    
    public static synchronized ExternalFactory getInstance() {
        if (instance == null) {
            instance = new ExternalFactory();
        }
        return instance;
    }
    
    
    public PersonaDTO createPersonaDTO(Persona persona) {
        if (persona == null) return null;
        
        return new PersonaDTO(
            (int) persona.getId(),
            persona.getNombres(),
            persona.getApellidos(),
            persona.getEmail(),
            persona.getTipo()
        );
    }
    
    /**
     * Crea un PersonaDTO con datos específicos
     */
    public PersonaDTO createPersonaDTO(int id, String nombres, String apellidos, String email, String tipo) {
        return new PersonaDTO(id, nombres, apellidos, email, tipo);
    }
    
    public EstudianteDTO createEstudianteDTO(Estudiante estudiante) {
        if (estudiante == null) return null;
        
        return new EstudianteDTO(
            (int) estudiante.getId(),
            estudiante.getNombres(),
            estudiante.getApellidos(),
            estudiante.getEmail(),
            (int) estudiante.getCodigo(),
            estudiante.getPrograma() != null ? estudiante.getPrograma().getNombre() : "Sin programa",
            estudiante.isActivo(),
            estudiante.getPromedio()
        );
    }
    
    /**
     * Crea un EstudianteDTO con datos específicos
     */
    public EstudianteDTO createEstudianteDTO(int id, String nombres, String apellidos, String email,
                                           int codigo, String programaNombre, boolean activo, double promedio) {
        return new EstudianteDTO(id, nombres, apellidos, email, codigo, programaNombre, activo, promedio);
    }
    
    
    public PersonaController createPersonaController() {
        return PersonaController.getInstance();
    }
    
    public EstudianteController createEstudianteController() {
        return EstudianteController.getInstance();
    }
    
    public ProfesorController createProfesorController() {
        return ProfesorController.getInstance();
    }
    
    public CursoController createCursoController() {
        return CursoController.getInstance();
    }
    
    public FacultadController createFacultadController() {
        return FacultadController.getInstance();
    }
    
    public ProgramaController createProgramaController() {
        return ProgramaController.getInstance();
    }
    
    public InscripcionController createInscripcionController() {
        return InscripcionController.getInstance();
    }
    
    public CursoProfesorController createCursoProfesorController() {
        return CursoProfesorController.getInstance();
    }
    
    
    public CursoConsoleObserver createCursoConsoleObserver(){
       return  new CursoConsoleObserver(InternalFactory.getInstance().createCursoService());
    }
    
    
    public VentanaPrincipal createVentanaPrincipal() {
        return new VentanaPrincipal();
    }
    
    public PersonasView createPersonasView() {
        return new PersonasView();
    }
    
    public EstudianteView createEstudianteView() {
        return new EstudianteView();
    }
    
    public ProfesorView createProfesorView() {
        return new ProfesorView();
    }
    
    public CursoView createCursoView() {
        return new CursoView();
    }
    
    public FacultadView createFacultadView() {
        return new FacultadView();
    }
    
    public ProgramaView createProgramaView() {
        return new ProgramaView();
    }
    
    public InscripcionView createInscripcionView() {
        return new InscripcionView();
    }
    
    public CursoProfesorView createCursoProfesorView() {
        return new CursoProfesorView();
    }
    
    public CursoObserverView createCursoObserverView(){
        return new CursoObserverView(InternalFactory.getInstance().createCursoService()); 
    }
    
    
    public PersonaDTO convertToDTO(Persona persona) {
        return createPersonaDTO(persona);
    }
    
    public EstudianteDTO convertToDTO(Estudiante estudiante) {
        return createEstudianteDTO(estudiante);
    }
    public boolean isValidDTO(PersonaDTO dto) {
        return dto != null && dto.isValid();
    }
    
    public boolean isValidDTO(EstudianteDTO dto) {
        return dto != null && dto.isValid();
    }
}
