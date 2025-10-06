package com.mycompany.aplicacionpoo;

import com.mycompany.aplicacionpoo.View.VentanaPrincipal;
import com.mycompany.aplicacionpoo.context.ApplicationContext;
import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;
import com.mycompany.aplicacionpoo.example.ExampleWithDependencyInjection;
import java.sql.SQLException;

public class AplicacionPOO {

    public static void main(String[] args) throws SQLException {
        System.out.println("🚀 Inicio de la aplicación principal");
        System.out.println("════════════════════════════════════════════════════════════");

        try {
            ApplicationContext context = ApplicationContext.getInstance();
            context.registerComponent(ExampleWithDependencyInjection.class);
            ExternalFactory externalFactory = context.getBean("externalFactory");

            ExampleWithDependencyInjection example = context.getBean("exampleService");
            example.runAllDemonstrations();

           
            System.out.println("✅ Observer de consola registrado y activo");
            System.out.println("════════════════════════════════════════════════════════════\n");

            // Iniciar GUI
            VentanaPrincipal vp = externalFactory.createVentanaPrincipal();
            vp.setVisible(true);
            
            System.out.println("🖥️  Aplicación iniciada correctamente");
            System.out.println("📌 Pestaña 1: Terminal principal (esta consola)");
            System.out.println("📌 Pestaña 2: Interfaz gráfica (ventana GUI)");
            System.out.println("📌 Pestaña 3: Observer de Cursos (abrir desde menú 🔔)");
            System.out.println("════════════════════════════════════════════════════════════\n");

        } catch (Exception e) {
            System.err.println("❌ Fallo en la app: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
