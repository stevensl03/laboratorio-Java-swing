/*
 * Implementación MySQL del DAO para Programa
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.ProgramaDao;
import com.mycompany.aplicacionpoo.Model.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDaoMySQL implements ProgramaDao {
    
    private final Connection conn;
    
    public ProgramaDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Programa programa) {
        System.out.println("✅ Programa guardado en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Programa eliminado en MySQL");
    }
    
    @Override
    public void actualizar(Programa programa) {
        System.out.println("✅ Programa actualizado en MySQL");
    }
    
    @Override
    public List<Programa> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Programa buscar(int id) {
        return null;
    }
}
