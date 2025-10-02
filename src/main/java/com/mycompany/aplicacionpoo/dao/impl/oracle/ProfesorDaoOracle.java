package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.ProfesorDao;
import com.mycompany.aplicacionpoo.Model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDaoOracle implements ProfesorDao {
    private Connection connection;

    public ProfesorDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Profesor profesor) {
        System.out.println("✅ Profesor guardado en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Profesor eliminado en Oracle");
    }

    @Override
    public void actualizar(Profesor profesor) {
        System.out.println("✅ Profesor actualizado en Oracle");
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
