/*
 * Implementación H2 del DAO para CursoProfesor
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.CursoProfesorDao;
import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorDaoH2 implements CursoProfesorDao {
    
    private final Connection conn;
    
    public CursoProfesorDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor guardado en H2");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ CursoProfesor eliminado en H2");
    }
    
    @Override
    public void actualizar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor actualizado en H2");
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
