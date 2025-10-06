package com.mycompany.aplicacionpoo.dao.impl.oracle;

import com.mycompany.aplicacionpoo.dao.EstudianteDao;
import com.mycompany.aplicacionpoo.Model.Estudiante;
import com.mycompany.aplicacionpoo.Model.Programa;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EstudianteDaoOracle implements EstudianteDao {
     
    private static InternalFactory factory;
    private final Connection conn;
    
    public EstudianteDaoOracle(Connection conn) {
        factory = InternalFactory.getInstance();
        this.conn = conn;
    }
    
     @Override
    public void guardar(Estudiante estudiante) {
        String validarSql = "SELECT tipo FROM persona WHERE id = ?";
        String sql = "INSERT INTO estudiante (id, codigo, promedio, activo, programa_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmtValidar = conn.prepareStatement(validarSql)) {
            stmtValidar.setDouble(1, estudiante.getId());
            try (ResultSet rs = stmtValidar.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if (!"estudiante".equalsIgnoreCase(tipo)) {
                        JOptionPane.showMessageDialog(null,
                                "El ID corresponde a una persona tipo " + tipo +
                                        ", no puede agregarse como estudiante.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ninguna persona con ese ID.");
                    return;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar estudiante: " + ex.getMessage());
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, estudiante.getId());
            stmt.setDouble(2, estudiante.getCodigo());
            stmt.setDouble(3, estudiante.getPromedio());
            stmt.setBoolean(4, estudiante.isActivo());
            stmt.setDouble(5, estudiante.getPrograma().getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Estudiante: " + ex.getMessage());
        }
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        String validarSql = "SELECT tipo FROM persona WHERE id = ?";
        String sql = "UPDATE estudiante SET codigo = ?, promedio = ?, activo = ?, programa_id = ? WHERE id = ?";

        try (PreparedStatement stmtValidar = conn.prepareStatement(validarSql)) {
            stmtValidar.setDouble(1, estudiante.getId());
            try (ResultSet rs = stmtValidar.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if (!"estudiante".equalsIgnoreCase(tipo)) {
                        JOptionPane.showMessageDialog(null,
                                "El ID corresponde a una persona tipo " + tipo +
                                        ", no puede actualizarse como estudiante.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ninguna persona con ese ID.");
                    return;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al validar estudiante: " + ex.getMessage());
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, estudiante.getCodigo());
            stmt.setDouble(2, estudiante.getPromedio());
            stmt.setBoolean(3, estudiante.isActivo());
            stmt.setDouble(4, estudiante.getPrograma().getId());
            stmt.setDouble(5, estudiante.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe Estudiante con ese ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Estudiante: " + ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe Estudiante con ese ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Estudiante: " + ex.getMessage());
        }
    }

    @Override
    public List<Estudiante> mostrarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = """
                SELECT persona.id, persona.nombre, persona.apellido, persona.correo,
                       estudiante.codigo, estudiante.promedio, estudiante.activo,
                       programa.id AS id_programa, programa.nombre AS nombre_programa
                FROM estudiante
                INNER JOIN persona ON persona.id = estudiante.id
                INNER JOIN programa ON programa.id = estudiante.programa_id
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = factory.createEstudiante();
                estudiante.setId(rs.getDouble("id"));
                estudiante.setCodigo(rs.getDouble("codigo"));
                estudiante.setNombres(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("correo"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setActivo(rs.getBoolean("activo"));

                Programa programa = factory.createPrograma();
                programa.setId(rs.getDouble("id_programa"));
                programa.setNombre(rs.getString("nombre_programa"));

                estudiante.setPrograma(programa);
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar Estudiantes: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Estudiante buscar(int id) {
        String sql = """
                SELECT persona.id, persona.nombre, persona.apellido, persona.correo,
                       estudiante.codigo, estudiante.promedio, estudiante.activo,
                       programa.id AS id_programa, programa.nombre AS nombre_programa
                FROM estudiante
                INNER JOIN persona ON persona.id = estudiante.id
                INNER JOIN programa ON programa.id = estudiante.programa_id
                WHERE estudiante.id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = factory.createEstudiante();
                estudiante.setId(rs.getDouble("id"));
                estudiante.setCodigo(rs.getDouble("codigo"));
                estudiante.setNombres(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("correo"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setActivo(rs.getBoolean("activo"));

                Programa programa = factory.createPrograma();
                programa.setId(rs.getDouble("id_programa"));
                programa.setNombre(rs.getString("nombre_programa"));

                estudiante.setPrograma(programa);
                return estudiante;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Estudiante: " + ex.getMessage());
        }
        return null;
    }

}