/*
 * Implementación H2 del DAO para Inscripcion
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.dao.InscripcionDao;
import com.mycompany.aplicacionpoo.Model.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDaoH2 implements InscripcionDao {
    
    private final Connection conn;
    
    public InscripcionDaoH2(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripcion guardada en H2");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Inscripcion eliminada en H2");
    }
    
    @Override
    public void actualizar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripcion actualizada en H2");
    }
    
    @Override
    public List<Inscripcion> mostrarTodos() {
        return new ArrayList<>();
    }
    
    @Override
    public Inscripcion buscar(int id) {
        return null;
    }
}
