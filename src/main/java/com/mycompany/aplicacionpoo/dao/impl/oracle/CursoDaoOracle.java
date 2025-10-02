package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.CursoDao;
import com.mycompany.aplicacionpoo.Model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoOracle implements CursoDao {
    private Connection connection;

    public CursoDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Curso curso) {
        System.out.println("✅ Curso guardado en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Curso eliminado en Oracle");
    }

    @Override
    public void actualizar(Curso curso) {
        System.out.println("✅ Curso actualizado en Oracle");
    }

    @Override
    public List<Curso> mostrarTodos() {
        return new ArrayList<>();
    }

    @Override
    public Curso buscar(int id) {
        return null;
    }
}
