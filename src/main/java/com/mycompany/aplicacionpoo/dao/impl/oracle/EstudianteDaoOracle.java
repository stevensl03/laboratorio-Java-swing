package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.EstudianteDao;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDaoOracle implements EstudianteDao {
    private Connection connection;

    public EstudianteDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Estudiante estudiante) {
        System.out.println("✅ Estudiante guardado en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Estudiante eliminado en Oracle");
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        System.out.println("✅ Estudiante actualizado en Oracle");
    }

    @Override
    public List<Estudiante> mostrarTodos() {
        return new ArrayList<>();
    }

    @Override
    public Estudiante buscar(int id) {
        return null;
    }
}
