/*
 * Implementación MySQL del DAO para Inscripcion
 */
package com.mycompany.aplicacionpoo.dao.impl.mysql;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.dao.InscripcionDao;
import com.mycompany.aplicacionpoo.Model.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDaoMySQL implements InscripcionDao {
   
    private static InternalFactory factory;
    private final Connection conn;
    
    public InscripcionDaoMySQL(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
        @Override
    public void guardar(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (anio, semestre, estudiante_id, curso_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getAño());
            stmt.setInt(2, inscripcion.getSemestre());
            stmt.setDouble(3, inscripcion.getEstudiante().getId());
            stmt.setInt(4, inscripcion.getCurso().getId());
            stmt.executeUpdate();
            System.out.println("✅ Inscripción guardada");
        } catch (SQLException e) {
            System.out.println("❌ Error (guardarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Inscripcion inscripcion) {
        String sql = "UPDATE inscripcion SET anio=?, semestre=? WHERE estudiante_id=? AND curso_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getAño());
            stmt.setInt(2, inscripcion.getSemestre());
            stmt.setDouble(3, inscripcion.getEstudiante().getId());
            stmt.setInt(4, inscripcion.getCurso().getId());
            stmt.executeUpdate();
            System.out.println("✅ Inscripción actualizada");
        } catch (SQLException e) {
            System.out.println("❌ Error (actualizarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        // Aquí el `id` no es suficiente, porque inscripcion depende de estudiante_id + curso_id.
        // Puedes adaptar tu GenericDao para aceptar clave compuesta, o usar solo estudiante_id.
        String sql = "DELETE FROM inscripcion WHERE estudiante_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Inscripción eliminada (por estudiante_id)");
        } catch (SQLException e) {
            System.out.println("❌ Error (eliminarInscripcion): " + e.getMessage());
        }
    }

    @Override
    public List<Inscripcion> mostrarTodos() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = """
                     SELECT i.anio, i.semestre, 
                            e.id AS id_estudiante, p.nombre AS nombre_estudiante, 
                            c.id AS id_curso, c.nombre AS nombre_curso
                     FROM inscripcion i
                     INNER JOIN estudiante e ON i.estudiante_id = e.id
                     INNER JOIN persona p ON p.id = e.id
                     INNER JOIN curso c ON i.curso_id = c.id
                     """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inscripcion i = factory.createInscripcion();
                i.setAño(rs.getInt("anio"));
                i.setSemestre(rs.getInt("semestre"));

                Estudiante est = factory.createEstudiante();
                est.setId(rs.getDouble("id_estudiante"));
                est.setNombres(rs.getString("nombre_estudiante"));

                Curso c = factory.createCurso();
                c.setId(rs.getInt("id_curso"));
                c.setNombre(rs.getString("nombre_curso"));

                i.setEstudiante(est);
                i.setCurso(c);
                inscripciones.add(i);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (mostrarInscripciones): " + e.getMessage());
        }
        return inscripciones;
    }

    @Override
    public Inscripcion buscar(int id) {
        // Igual que en eliminar: aquí solo usaremos estudiante_id como referencia.
        String sql = """
                     SELECT i.anio, i.semestre, 
                            p.id AS id_estudiante, p.nombre AS nombre_estudiante, 
                            c.id AS id_curso, c.nombre AS nombre_curso
                     FROM inscripcion i
                     INNER JOIN persona p ON i.estudiante_id = p.id
                     INNER JOIN curso c ON i.curso_id = c.id
                     WHERE i.estudiante_id = ?
                     """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Inscripcion i = factory.createInscripcion();
                    i.setAño(rs.getInt("anio"));
                    i.setSemestre(rs.getInt("semestre"));

                    Estudiante e = factory.createEstudiante();
                    e.setId(rs.getDouble("id_estudiante"));
                    e.setNombres(rs.getString("nombre_estudiante"));

                    Curso c = factory.createCurso();
                    c.setId(rs.getInt("id_curso"));
                    c.setNombre(rs.getString("nombre_curso"));

                    i.setEstudiante(e);
                    i.setCurso(c);
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error (buscarInscripcion): " + e.getMessage());
        }
        return null;
    }
}