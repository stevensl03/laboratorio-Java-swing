/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aplicacionpoo;

import com.mycompany.aplicacionpoo.View.VentanaPrincipal;
import com.mycompany.aplicacionpoo.context.ApplicationContext;
import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;
import com.mycompany.aplicacionpoo.example.ExampleWithDependencyInjection;
import java.sql.SQLException;





public class AplicacionPOO {

    public static void main(String[] args) throws SQLException {
        System.out.println("inicio de la aplicacion");
        
        try {
            ApplicationContext context = ApplicationContext.getInstance();
            
            context.registerComponent(ExampleWithDependencyInjection.class);
            
            ExternalFactory externalFactory = context.getBean("externalFactory");
            
            ExampleWithDependencyInjection example = context.getBean("exampleService");
            example.runAllDemonstrations();
            
            VentanaPrincipal vp = externalFactory.createVentanaPrincipal();
            vp.setVisible(true);
            
            System.out.println("prueba");
            
        } catch (Exception e) {
            System.err.println("fallo mielda " + e.getMessage());
            e.printStackTrace();
        }
    }
}
