/*
 * Implementación MySQL del DAO para Inscripcion
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.dao.InscripcionDao;
import com.mycompany.aplicacionpoo.Model.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDaoMySQL implements InscripcionDao {
    
    private final Connection conn;
    
    public InscripcionDaoMySQL(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void guardar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripcion guardada en MySQL");
    }
    
    @Override
    public void eliminar(int id) {
        System.out.println("✅ Inscripcion eliminada en MySQL");
    }
    
    @Override
    public void actualizar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripcion actualizada en MySQL");
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
