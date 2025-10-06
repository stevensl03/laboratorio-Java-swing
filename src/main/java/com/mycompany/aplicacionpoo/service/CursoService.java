package com.mycompany.aplicacionpoo.service;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.dao.CursoDao;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.observer.Observer;
import com.mycompany.aplicacionpoo.observer.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Cursos que implementa el patrón Observer como Subject
 */
public class CursoService implements Subject {
    private static CursoDao GenericDao;
    private static ProgramaService programaService;
    private static CursoService instance;
    
    private final List<Observer> observers = new ArrayList<>();
    
    private CursoService() {
        InternalFactory factory = InternalFactory.getInstance();
        this.GenericDao = factory.createCursoDao();
        this.programaService = factory.createProgramaService();
    }
     
    public static synchronized CursoService getInstance(){
        if(instance == null){
           instance = new CursoService();
        }
        return instance;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registrado. Total observers: " + observers.size());
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println(" Observer eliminado. Total observers: " + observers.size());
    }

    @Override
    public void notifyObservers() {
        System.out.println(" Notificando a " + observers.size() + " observer(s)...");
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void guardarCurso(int id, String nombre, boolean estado, double idPrograma) {
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Curso curso = new Curso(id, nombre, programa, estado);
        
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        GenericDao.guardar(curso);
        System.out.println(" Curso guardado: " + nombre);
        notifyObservers(); // 🔔 Notifica a todos los observers
    }

    public void eliminarCurso(int id) {
        GenericDao.eliminar(id);
        System.out.println(" Curso eliminado: ID " + id);
        notifyObservers(); // 🔔 Notifica a todos los observers
    }

    public void actualizarCurso(int id, String nombre, boolean estado, double idPrograma) {
        Programa programa = programaService.buscarPrograma((int)idPrograma);
        Curso curso = new Curso(id, nombre, programa, estado);
        
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        GenericDao.actualizar(curso);
        System.out.println("Curso actualizado: " + nombre);
        notifyObservers(); // 🔔 Notifica a todos los observers
    }

    public List<Curso> mostrarCurso() {
        return GenericDao.mostrarTodos();
    }

    public Curso buscarCurso(int id) {
        return (Curso) GenericDao.buscar(id);
    }
}
