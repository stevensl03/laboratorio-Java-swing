package com.mycompany.aplicacionpoo.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipalNetBeans extends JFrame {
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton jButton1;
    
    public VentanaPrincipalNetBeans() {
        initComponents();
        setupCustomDesign();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión Académica");
        setResizable(false);

        jPanel1.setBackground(new Color(233, 236, 239));
        jPanel1.setBorder(new EmptyBorder(20, 20, 20, 20));

        jPanel2.setBackground(new Color(233, 236, 239));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setForeground(new Color(52, 58, 64));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Sistema de Gestión Académica");

        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setForeground(new Color(108, 117, 125));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Gestiona estudiantes, profesores, cursos y más");

        jPanel4.setBackground(new Color(233, 236, 239));
        jPanel4.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanel4.setLayout(new GridLayout(3, 3, 15, 15));

        jPanel3.setBackground(new Color(233, 236, 239));

        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.setText("🚪 Salir");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setSize(700, 750);
    }
    
    private void setupCustomDesign() {
        jButton1.setBackground(new Color(220, 53, 69));
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addClickableCards();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void addClickableCards() {
        jPanel4.removeAll();
        
        jPanel4.add(createClickableCard("👥", "Personas", "Gestiona personas del sistema", () -> openWindow(new PersonasView())));
        jPanel4.add(createClickableCard("🎓", "Estudiantes", "Administra estudiantes", () -> openWindow(new EstudianteView())));
        jPanel4.add(createClickableCard("👨‍🏫", "Profesores", "Gestiona profesores", () -> openWindow(new ProfesorView())));
        jPanel4.add(createClickableCard("🏛️", "Facultades", "Administra facultades", () -> openWindow(new FacultadView())));
        jPanel4.add(createClickableCard("📋", "Programas", "Gestiona programas académicos", () -> openWindow(new ProgramaView())));
        jPanel4.add(createClickableCard("📚", "Cursos", "Administra cursos", () -> openWindow(new CursoView())));
        jPanel4.add(createClickableCard("📝", "Inscripciones", "Gestiona inscripciones", () -> openWindow(new InscripcionView())));
        jPanel4.add(createClickableCard("🔗", "Curso-Profesor", "Asigna profesores a cursos", () -> openWindow(new CursoProfesorView())));
        
        jPanel4.add(createSystemCard());
        
        jPanel4.revalidate();
        jPanel4.repaint();
    }
    
    private JPanel createClickableCard(String icon, String title, String description, Runnable action) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 200));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(new Color(108, 117, 125));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel clickLabel = new JLabel("👆 Click para abrir");
        clickLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        clickLabel.setForeground(new Color(0, 123, 255));
        clickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        card.add(clickLabel, BorderLayout.PAGE_END);
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(240, 248, 255));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                    new EmptyBorder(15, 15, 15, 15)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(255, 255, 255, 200));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
                    new EmptyBorder(15, 15, 15, 15)
                ));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.run();
            }
        });
        
        return card;
    }
    
    private JPanel createSystemCard() {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(240, 248, 255));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel iconLabel = new JLabel("ℹ️");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JLabel titleLabel = new JLabel("Sistema");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(0, 123, 255));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>Información del sistema</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(new Color(0, 123, 255));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void openWindow(JFrame window) {
        window.setLocationRelativeTo(this);
        window.setVisible(true);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea salir del sistema?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipalNetBeans().setVisible(true);
        });
    }
}
