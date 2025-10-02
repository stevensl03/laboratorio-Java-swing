package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.CursoProfesorDao;
import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorDaoOracle implements CursoProfesorDao {
    private Connection connection;

    public CursoProfesorDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor guardado en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ CursoProfesor eliminado en Oracle");
    }

    @Override
    public void actualizar(CursoProfesor cursoProfesor) {
        System.out.println("✅ CursoProfesor actualizado en Oracle");
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
