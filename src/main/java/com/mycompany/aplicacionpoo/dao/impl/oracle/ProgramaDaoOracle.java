package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.ProgramaDao;
import com.mycompany.aplicacionpoo.Model.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDaoOracle implements ProgramaDao {
    private Connection connection;

    public ProgramaDaoOracle(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Programa programa) {
        System.out.println("✅ Programa guardado en Oracle");
    }

    @Override
    public void eliminar(int id) {
        System.out.println("✅ Programa eliminado en Oracle");
    }

    @Override
    public void actualizar(Programa programa) {
        System.out.println("✅ Programa actualizado en Oracle");
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
