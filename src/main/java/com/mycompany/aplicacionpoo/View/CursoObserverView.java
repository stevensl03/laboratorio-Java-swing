package com.mycompany.aplicacionpoo.View;

import com.mycompany.aplicacionpoo.Model.Curso;
import com.mycompany.aplicacionpoo.observer.Observer;
import com.mycompany.aplicacionpoo.service.CursoService;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Tercera ventana - Terminal Observer de Cursos
 * Muestra información de cursos y se actualiza automáticamente usando el patrón Observer
 */
public class CursoObserverView extends JFrame implements Observer {
    
    private final CursoService cursoService;
    private JTextArea consoleArea;
    private JTable cursosTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JLabel lastUpdateLabel;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public CursoObserverView(CursoService cursoService) {
        this.cursoService = cursoService;
        cursoService.addObserver(this);
        
        initComponents();
        setLocationRelativeTo(null);
        
        // Mostrar datos iniciales
        update();
    }
    
    private void initComponents() {
        setTitle("🔔 Observer de Cursos - Terminal de Monitoreo");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // Pestaña 1: Vista de Tabla
        JPanel tablePanel = createTablePanel();
        tabbedPane.addTab("📊 Vista de Tabla", tablePanel);
        
        // Pestaña 2: Vista de Consola
        JPanel consolePanel = createConsolePanel();
        tabbedPane.addTab("💻 Vista de Consola", consolePanel);
        
        // Panel de estado en la parte inferior
        JPanel statusPanel = createStatusPanel();
        
        // Layout principal
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        
        // Cerrar y desregistrar observer
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cursoService.removeObserver(CursoObserverView.this);
                System.out.println("🔴 CursoObserverView desregistrado del Subject");
            }
        });
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        // Título
        JLabel titleLabel = new JLabel("📚 Lista de Cursos en Tiempo Real");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        // Tabla
        String[] columnNames = {"ID", "Nombre del Curso", "Programa", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        cursosTable = new JTable(tableModel);
        cursosTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cursosTable.setRowHeight(30);
        cursosTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        cursosTable.getTableHeader().setBackground(new Color(0, 123, 255));
        cursosTable.getTableHeader().setForeground(Color.WHITE);
        cursosTable.setSelectionBackground(new Color(173, 216, 230));
        
        JScrollPane scrollPane = new JScrollPane(cursosTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230), 1));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createConsolePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(30, 30, 30));
        
        // Título
        JLabel titleLabel = new JLabel("💻 Consola de Eventos del Observer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        // Área de texto estilo consola
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        consoleArea.setBackground(new Color(30, 30, 30));
        consoleArea.setForeground(new Color(0, 255, 0));
        consoleArea.setCaretColor(Color.WHITE);
        consoleArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(consoleArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2));
        
        // Botón para limpiar consola
        JButton clearButton = new JButton("🗑️ Limpiar Consola");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> consoleArea.setText(""));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(clearButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 249, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(222, 226, 230)),
            new EmptyBorder(10, 15, 10, 15)
        ));
        
        statusLabel = new JLabel("🟢 Observer Activo - Esperando cambios...");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(new Color(40, 167, 69));
        
        lastUpdateLabel = new JLabel("Última actualización: --");
        lastUpdateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lastUpdateLabel.setForeground(new Color(108, 117, 125));
        
        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(lastUpdateLabel, BorderLayout.EAST);
        
        return panel;
    }
    
    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            String timestamp = LocalDateTime.now().format(formatter);
            
            // Actualizar tabla
            updateTable();
            
            // Actualizar consola
            updateConsole(timestamp);
            
            // Actualizar estado
            lastUpdateLabel.setText("Última actualización: " + timestamp);
            statusLabel.setText("🔔 Cambio detectado - Datos actualizados");
            
            // Volver al estado normal después de 2 segundos
            Timer timer = new Timer(2000, e -> {
                statusLabel.setText("🟢 Observer Activo - Esperando cambios...");
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        java.util.List<Curso> cursos = cursoService.mostrarCurso();
        for (Curso curso : cursos) {
            String estado = curso.isActivo() ? "✅ ACTIVO" : "❌ INACTIVO";
            String programa = curso.getPrograma() != null ? curso.getPrograma().getNombre() : "N/A";
            
            tableModel.addRow(new Object[]{
                curso.getId(),
                curso.getNombre(),
                programa,
                estado
            });
        }
    }
    
    private void updateConsole(String timestamp) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════════\n");
        sb.append("🔔 NOTIFICACIÓN DE CAMBIO - ").append(timestamp).append("\n");
        sb.append("═══════════════════════════════════════════════════════════\n");
        
        java.util.List<Curso> cursos = cursoService.mostrarCurso();
        
        if (cursos.isEmpty()) {
            sb.append("📭 No hay cursos registrados en el sistema.\n");
        } else {
            sb.append("📚 Total de cursos: ").append(cursos.size()).append("\n");
            sb.append("───────────────────────────────────────────────────────────\n");
            for (Curso curso : cursos) {
                String estado = curso.isActivo() ? "✅ ACTIVO" : "❌ INACTIVO";
                String programa = curso.getPrograma() != null ? curso.getPrograma().getNombre() : "N/A";
                sb.append(String.format("  ID: %d | %s | Programa: %s | %s%n", 
                    curso.getId(), 
                    curso.getNombre(), 
                    programa,
                    estado
                ));
            }
            sb.append("───────────────────────────────────────────────────────────\n");
        }
        sb.append("\n");
        
        consoleArea.append(sb.toString());
        consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
    }
}
