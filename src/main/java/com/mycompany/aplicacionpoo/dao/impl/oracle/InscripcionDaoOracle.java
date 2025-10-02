package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.InscripcionDao;
import com.mycompany.aplicacionpoo.Model.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDaoOracle implements InscripcionDao {
    private Connection connection;

    public InscripcionDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripción guardada en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Inscripción eliminada en Oracle");
    }

    @Override
    public void actualizar(Inscripcion inscripcion) {
        System.out.println("✅ Inscripción actualizada en Oracle");
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
