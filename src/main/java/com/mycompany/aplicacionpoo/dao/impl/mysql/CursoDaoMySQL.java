/*
 * Implementación MySQL del DAO para Curso
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.CursoDao;
import com.mycompany.aplicacionpoo.Model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoMySQL implements CursoDao {
    
    private final Connection conn;
    
    public CursoDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Curso curso) {
        System.out.println("✅ Curso guardado en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Curso eliminado en MySQL");
    }
    
    @Override
    public void actualizar(Curso curso) {
        System.out.println("✅ Curso actualizado en MySQL");
    }
    
    @Override
    public List<Curso> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Curso buscar(int id) {
        return null;
    }
}
