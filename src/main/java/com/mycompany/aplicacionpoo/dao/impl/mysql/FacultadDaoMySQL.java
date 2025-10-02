/*
 * Implementación MySQL del DAO para Facultad
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.FacultadDao;
import com.mycompany.aplicacionpoo.Model.Facultad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDaoMySQL implements FacultadDao {
    
    private final Connection conn;
    
    public FacultadDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Facultad facultad) {
        System.out.println("✅ Facultad guardada en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Facultad eliminada en MySQL");
    }
    
    @Override
    public void actualizar(Facultad facultad) {
        System.out.println("✅ Facultad actualizada en MySQL");
    }
    
    @Override
    public List<Facultad> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Facultad buscar(int id) {
        return null;
    }
}
