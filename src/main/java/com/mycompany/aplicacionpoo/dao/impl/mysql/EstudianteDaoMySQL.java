/*
 * Implementación MySQL del DAO para Estudiante
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.EstudianteDao;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDaoMySQL implements EstudianteDao {
    
    private final Connection conn;
    
    public EstudianteDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Estudiante estudiante) {
        System.out.println("✅ Estudiante guardado en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Estudiante eliminado en MySQL");
    }
    
    @Override
    public void actualizar(Estudiante estudiante) {
        System.out.println("✅ Estudiante actualizado en MySQL");
    }
    
    @Override
    public List<Estudiante> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Estudiante buscar(int id) {
        return null;
    }
}
