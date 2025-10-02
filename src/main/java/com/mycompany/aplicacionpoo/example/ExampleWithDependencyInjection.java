package com.mycompany.aplicacionpoo.example;

import com.mycompany.aplicacionpoo.annotation.*;
import com.mycompany.aplicacionpoo.context.ApplicationContext;
import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.adapter.DatabaseAdapter;
import com.mycompany.aplicacionpoo.dto.PersonaDTO;
import com.mycompany.aplicacionpoo.Model.Persona;

@Component("exampleService")
public class ExampleWithDependencyInjection {
    
    @Autowired
    private ExternalFactory externalFactory;
    
    @Autowired
    private InternalFactory internalFactory;
    
    @Autowired
    private DatabaseAdapter databaseAdapter;
    
    public void demonstrateExternalFactory() {
        
        PersonaDTO personaDTO = externalFactory.createPersonaDTO(
            1, "Juan", "Pérez", "juan@email.com", "Estudiante"
        );
        
        System.out.println("DTO creado: " + personaDTO);
        System.out.println("DTO válido: " + externalFactory.isValidDTO(personaDTO));
        
        Persona persona = internalFactory.createPersona(
            1, "Juan", "Pérez", "juan@email.com", "Estudiante"
        );
        
        PersonaDTO convertedDTO = externalFactory.convertToDTO(persona);
        System.out.println("DTO convertido: " + convertedDTO);
    }
    
    public void demonstrateDatabaseAdapter() {
        
        String dbType = databaseAdapter.getDatabaseType();
        System.out.println("Tipo de base de datos: " + dbType);
        System.out.println("Función fecha actual: " + databaseAdapter.getCurrentDateFunction());
        System.out.println("Función tiempo actual: " + databaseAdapter.getCurrentTimeFunction());
        
        // Verificar conexión
        try {
            var connection = databaseAdapter.getConnection();
            System.out.println("Conexión activa: " + !connection.isClosed());
        } catch (Exception e) {
            System.err.println("Error verificando conexión: " + e.getMessage());
        }
    }
    
    public void demonstrateApplicationContext() {
        
        ApplicationContext context = ApplicationContext.getInstance();
        
        ExternalFactory extFactory = context.getBean("externalFactory");
        InternalFactory intFactory = context.getBean("internalFactory");
        
        System.out.println("Factory externa obtenida: " + (extFactory != null));
        System.out.println("Factory interna obtenida: " + (intFactory != null));
        
        System.out.println("Beans registrados: " + context.getBeanNames());
    }
    
    public void runAllDemonstrations() {
        
        demonstrateExternalFactory();
        demonstrateDatabaseAdapter();
        demonstrateApplicationContext();
        
    }
}
