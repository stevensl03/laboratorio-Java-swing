package com.mycompany.aplicacionpoo;

import com.mycompany.aplicacionpoo.View.VentanaPrincipal;
import com.mycompany.aplicacionpoo.context.ApplicationContext;
import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;
import java.sql.SQLException;

public class AplicacionPOOMejorada {

    public static void main(String[] args) throws SQLException {
        System.out.println("inicio de la aplicacion");
        
        try {
            ApplicationContext context = ApplicationContext.getInstance();
            
            ExternalFactory externalFactory = context.getBean("externalFactory");
            
            VentanaPrincipal vp = externalFactory.createVentanaPrincipal();
            vp.setVisible(true);
            
            System.out.println("prueba");
            
        } catch (Exception e) {
            System.err.println("fallo mielda " + e.getMessage());
            e.printStackTrace();
        }
    }
}
