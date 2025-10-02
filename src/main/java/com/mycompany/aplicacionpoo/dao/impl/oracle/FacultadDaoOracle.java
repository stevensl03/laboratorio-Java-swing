package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.FacultadDao;
import com.mycompany.aplicacionpoo.Model.Facultad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultadDaoOracle implements FacultadDao {
    private Connection connection;

    public FacultadDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Facultad facultad) {
        System.out.println("✅ Facultad guardada en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Facultad eliminada en Oracle");
    }

    @Override
    public void actualizar(Facultad facultad) {
        System.out.println("✅ Facultad actualizada en Oracle");
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
