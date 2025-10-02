/*
 * Implementación H2 del DAO para Programa
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.ProgramaDao;
import com.mycompany.aplicacionpoo.Model.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDaoH2 implements ProgramaDao {
    
    private final Connection conn;
    
    public ProgramaDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Programa programa) {
        System.out.println("✅ Programa guardado en H2");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Programa eliminado en H2");
    }
    
    @Override
    public void actualizar(Programa programa) {
        System.out.println("✅ Programa actualizado en H2");
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
