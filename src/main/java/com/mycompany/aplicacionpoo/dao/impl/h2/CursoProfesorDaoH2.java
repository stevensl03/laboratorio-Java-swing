/*
 * Implementación H2 del DAO para CursoProfesor
 */
package com.mycompany.aplicacionpoo.dao.impl.h2;

import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.dao.CursoProfesorDao;
import com.mycompany.aplicacionpoo.Model.CursoProfesor;
import com.mycompany.aplicacionpoo.Model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorDaoH2 implements CursoProfesorDao {
    
    private static InternalFactory factory;
    private final Connection conn;
    
    public CursoProfesorDaoH2(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
    
    @Override
    public void guardar(CursoProfesor inscripcion) {
        String sql = "INSERT INTO cursoprofesor (anio, semestre, profesor_id, curso_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getAño());
            stmt.setInt(2, inscripcion.getSemestre());
            stmt.setDouble(3, inscripcion.getProfesor().getId());
            stmt.setInt(4, inscripcion.getCurso().getId());
            stmt.executeUpdate();
            System.out.println("✅ Inscripción guardada");
        } catch (SQLException e) {
            System.out.println("❌ Error (guardarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(CursoProfesor inscripcion) {
        String sql = "UPDATE cursoprofesor SET anio=?, semestre=? WHERE profesor_id=? AND curso_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getAño());
            stmt.setInt(2, inscripcion.getSemestre());
            stmt.setDouble(3, inscripcion.getProfesor().getId());
            stmt.setInt(4, inscripcion.getCurso().getId());
            stmt.executeUpdate();
            System.out.println("✅ Inscripción actualizada");
        } catch (SQLException e) {
            System.out.println("❌ Error (actualizarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int idCurso) {
        String sql = "DELETE FROM cursoprofesor WHERE curso_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            stmt.executeUpdate();
            System.out.println("✅ Inscripción eliminada");
        } catch (SQLException e) {
            System.out.println("❌ Error (eliminarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public List<CursoProfesor> mostrarTodos() {
        List<CursoProfesor> inscripciones = new ArrayList<>();
        String sql = """
                     SELECT cp.anio, cp.semestre,
                            pe.id AS id_profesor, pe.nombre AS nombre_profesor,
                            c.id AS id_curso, c.nombre AS nombre_curso
                     FROM cursoprofesor cp
                     INNER JOIN profesor p ON cp.profesor_id = p.id
                     INNER JOIN persona pe ON p.id = pe.id
                     INNER JOIN curso c ON cp.curso_id = c.id
                     """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CursoProfesor inscripcion = factory.createCursoProfesor();
                inscripcion.setAño(rs.getInt("anio"));
                inscripcion.setSemestre(rs.getInt("semestre"));

                Profesor profesor = factory.createProfesor();
                profesor.setId(rs.getDouble("id_profesor"));
                profesor.setNombres(rs.getString("nombre_profesor"));

                Curso curso = factory.createCurso();
                curso.setId(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre_curso"));

                inscripcion.setProfesor(profesor);
                inscripcion.setCurso(curso);

                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (mostrarInscripciones): " + e.getMessage());
        }
        return inscripciones;
    }

    @Override
    public CursoProfesor buscar(int idCurso) {
        String sql = """
                     SELECT cp.anio, cp.semestre,
                            pe.id AS id_profesor, pe.nombre AS nombre_profesor,
                            c.id AS id_curso, c.nombre AS nombre_curso
                     FROM cursoprofesor cp
                     INNER JOIN profesor p ON cp.profesor_id = p.id
                     INNER JOIN persona pe ON p.id = pe.id
                     INNER JOIN curso c ON cp.curso_id = c.id
                     WHERE c.id = ?
                     """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CursoProfesor inscripcion = factory.createCursoProfesor();
                    inscripcion.setAño(rs.getInt("anio"));
                    inscripcion.setSemestre(rs.getInt("semestre"));

                    Profesor profesor = factory.createProfesor();
                    profesor.setId(rs.getDouble("id_profesor"));
                    profesor.setNombres(rs.getString("nombre_profesor"));

                    Curso curso = factory.createCurso();
                    curso.setId(rs.getInt("id_curso"));
                    curso.setNombre(rs.getString("nombre_curso"));

                    inscripcion.setProfesor(profesor);
                    inscripcion.setCurso(curso);

                    return inscripcion;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (buscarInscripcion): " + e.getMessage());
        }
        return null;
    }
}
