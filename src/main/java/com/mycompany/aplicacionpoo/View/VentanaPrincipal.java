package com.mycompany.aplicacionpoo.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;


public class VentanaPrincipal extends JFrame {
    private static ExternalFactory factory;

    public VentanaPrincipal() {
        factory = ExternalFactory.getInstance();
        initComponents();
        setupLookAndFeel();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión Académica");
        setSize(700, 750);
        setResizable(false);

        JPanel mainPanel = createMainPanel();
        setContentPane(mainPanel);

        createMenuBar();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                Color color1 = new Color(248, 249, 250);
                Color color2 = new Color(233, 236, 239);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel centerPanel = createCenterPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomPanel();
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("Sistema de Gestión Académica");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(52, 58, 64));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel subtitleLabel = new JLabel("Gestiona estudiantes, profesores, cursos y más");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(108, 117, 125));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        JPanel infoPanel = createInfoPanel();

        panel.add(titleLabel);
        panel.add(subtitleLabel);
        panel.add(infoPanel);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(3, 3, 15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(createClickableInfoCard("👥", "Personas", "Gestiona personas del sistema", () -> openWindow(factory.createPersonasView())));
        panel.add(createClickableInfoCard("🎓", "Estudiantes", "Administra estudiantes", () -> openWindow(factory.createEstudianteView())));
        panel.add(createClickableInfoCard("👨‍🏫", "Profesores", "Gestiona profesores", () -> openWindow(factory.createProfesorView())));
        panel.add(createClickableInfoCard("🏛️", "Facultades", "Administra facultades", () -> openWindow(factory.createFacultadView())));
        panel.add(createClickableInfoCard("📋", "Programas", "Gestiona programas académicos", () -> openWindow(factory.createProgramaView())));
        panel.add(createClickableInfoCard("📚", "Cursos", "Administra cursos", () -> openWindow(factory.createCursoView())));
        panel.add(createClickableInfoCard("📝", "Inscripciones", "Gestiona inscripciones", () -> openWindow(factory.createInscripcionView())));
        panel.add(createClickableInfoCard("🔗", "Curso-Profesor", "Asigna profesores a cursos", () -> openWindow(factory.createCursoProfesorView())));

        panel.add(createClickableInfoCard("🔔", "Observer Cursos", "Monitor en tiempo real", () -> openObserverWindow()));

        return panel;
    }

    private JPanel createClickableInfoCard(String icon, String title, String description, Runnable action) {
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

    private JPanel createSystemInfoCard() {
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

        JLabel descLabel = new JLabel("<html><div style='text-align: center;'>Información del sistema y configuración</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(new Color(0, 123, 255));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(iconLabel, BorderLayout.NORTH);
        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton exitButton = createStyledButton("🚪 Salir", new Color(220, 53, 69));
        exitButton.addActionListener(e -> {
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
        });

        panel.add(exitButton);

        return panel;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(248, 249, 250));
        menuBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));

        JMenu menuPrincipal = new JMenu("🏠 Menú Principal");
        menuPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menuPrincipal.setForeground(new Color(52, 58, 64));

        JMenu personasMenu = new JMenu("👥 Gestión de Personas");
        personasMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        addMenuItem(personasMenu, "👤 Personas", () -> openWindow(new PersonasView()));
        addMenuItem(personasMenu, "🎓 Estudiantes", () -> openWindow(new EstudianteView()));
        addMenuItem(personasMenu, "👨‍🏫 Profesores", () -> openWindow(new ProfesorView()));

        JMenu academicoMenu = new JMenu("📚 Gestión Académica");
        academicoMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        addMenuItem(academicoMenu, "🏛️ Facultades", () -> openWindow(new FacultadView()));
        addMenuItem(academicoMenu, "📋 Programas", () -> openWindow(new ProgramaView()));
        addMenuItem(academicoMenu, "📖 Cursos", () -> openWindow(new CursoView()));

        JMenu inscripcionesMenu = new JMenu("📝 Inscripciones");
        inscripcionesMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        addMenuItem(inscripcionesMenu, "📝 Inscripciones", () -> openWindow(new InscripcionView()));
        addMenuItem(inscripcionesMenu, "🔗 Curso-Profesor", () -> openWindow(new CursoProfesorView()));

        JMenu observerMenu = new JMenu("🔔 Monitoreo");
        observerMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        addMenuItem(observerMenu, "🔔 Observer de Cursos", this::openObserverWindow);

        menuPrincipal.add(personasMenu);
        menuPrincipal.add(academicoMenu);
        menuPrincipal.add(inscripcionesMenu);
        menuPrincipal.add(observerMenu);

        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu menu, String text, Runnable action) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.addActionListener(e -> action.run());
        menu.add(item);
    }

    private void openWindow(JFrame window) {
        window.setLocationRelativeTo(this);
        window.setVisible(true);
    }

    private void openObserverWindow() {
        CursoObserverView observerView = factory.createCursoObserverView();
        observerView.setVisible(true);
        System.out.println("🔔 Ventana Observer de Cursos abierta");
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            factory.createVentanaPrincipal().setVisible(true);
        });
    }
}
