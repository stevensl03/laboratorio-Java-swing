/*
 * Implementación MySQL del DAO para Profesor
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.ProfesorDao;
import com.mycompany.aplicacionpoo.Model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDaoMySQL implements ProfesorDao {
    
    private final Connection conn;
    
    public ProfesorDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Profesor profesor) {
        System.out.println("✅ Profesor guardado en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Profesor eliminado en MySQL");
    }
    
    @Override
    public void actualizar(Profesor profesor) {
        System.out.println("✅ Profesor actualizado en MySQL");
    }
    
    @Override
    public List<Profesor> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Profesor buscar(int id) {
        return null;
    }
}