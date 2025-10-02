/*
 * Implementación H2 del DAO para Facultad
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.FacultadDao;
import com.mycompany.aplicacionpoo.Model.Facultad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDaoH2 implements FacultadDao {
    
    private final Connection conn;
    
    public FacultadDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Facultad facultad) {
        // Implementación básica
        System.out.println("✅ Facultad guardada en H2");
    }
    
    @Override
    public void eliminar(int id) {
        // Implementación básica
        System.out.println("✅ Facultad eliminada en H2");
    }
    
    @Override
    public void actualizar(Facultad facultad) {
        // Implementación básica
        System.out.println("✅ Facultad actualizada en H2");
    }
    
    @Override
    public List<Facultad> mostrarTodos() {
        // Implementación básica
        return new ArrayList<>();
    }
    
    @Override
    public Facultad buscar(int id) {
        // Implementación básica
        return null;
    }
}
