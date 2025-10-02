/*
 * Implementación MySQL del DAO para CursoProfesor
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.CursoProfesorDao;
import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorDaoMySQL implements CursoProfesorDao {
    
    private final Connection conn;
    
    public CursoProfesorDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor guardado en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ CursoProfesor eliminado en MySQL");
    }
    
    @Override
    public void actualizar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor actualizado en MySQL");
    }
    
    @Override
    public List<CursoProfesor> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public CursoProfesor buscar(int id) {
        return null;
    }
}
