package com.mycompany.aplicacionpoo.observer;

import com.mycompany.aplicacionpoo.service.CursoService;
import com.mycompany.aplicacionpoo.Model.Curso;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Observer que muestra los cursos en consola cuando hay cambios
 * Implementación estricta del patrón Observer
 */
public class CursoConsoleObserver implements Observer {

    private final CursoService cursoService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public CursoConsoleObserver(CursoService cursoService) {
        this.cursoService = cursoService;
        cursoService.addObserver(this);
        System.out.println("✅ CursoConsoleObserver registrado correctamente");
    }

    @Override
    public void update() {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  🔔 NOTIFICACIÓN DE CAMBIO - " + timestamp + "                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        List<Curso> cursos = cursoService.mostrarCurso();
        
        if (cursos.isEmpty()) {
            System.out.println("📭 No hay cursos registrados en el sistema.");
        } else {
            System.out.println("📚 Total de cursos: " + cursos.size());
            System.out.println("─────────────────────────────────────────────────────────────");
            for (Curso curso : cursos) {
                String estado = curso.isActivo() ? "✅ ACTIVO" : "❌ INACTIVO";
                System.out.printf("  ID: %d | %s | Programa: %s | %s%n", 
                    curso.getId(), 
                    curso.getNombre(), 
                    curso.getPrograma() != null ? curso.getPrograma().getNombre() : "N/A",
                    estado
                );
            }
            System.out.println("─────────────────────────────────────────────────────────────");
        }
        System.out.println();
    }
}