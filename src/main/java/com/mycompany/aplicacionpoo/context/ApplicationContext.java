package com.mycompany.aplicacionpoo.context;

import com.mycompany.aplicacionpoo.annotation.*;
import com.mycompany.aplicacionpoo.factory.factoryExterna.ExternalFactory;
import com.mycompany.aplicacionpoo.factory.factoryInterna.InternalFactory;
import com.mycompany.aplicacionpoo.adapter.DatabaseAdapter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ApplicationContext {
    
    private static ApplicationContext instance;
    private final Map<String, Object> beans;
    private final Map<Class<?>, String> beanNames;
    private final Set<Class<?>> registeredClasses;
    
    private ApplicationContext() {
        this.beans = new HashMap<>();
        this.beanNames = new HashMap<>();
        this.registeredClasses = new HashSet<>();
        initializeContext();
    }
    
    public static synchronized ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }
    
    private void initializeContext() {
        registerBean("externalFactory", ExternalFactory.getInstance());
        registerBean("internalFactory", InternalFactory.getInstance());
        registerBean("databaseAdapter", DatabaseAdapter.getInstance());
        
        System.out.println("✅ ApplicationContext: Contexto inicializado");
    }
    
    public void registerBean(String name, Object bean) {
        beans.put(name, bean);
        beanNames.put(bean.getClass(), name);
        System.out.println("✅ Bean registrado: " + name + " -> " + bean.getClass().getSimpleName());
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        Object bean = beans.get(name);
        if (bean == null) {
            throw new RuntimeException("Bean no encontrado: " + name);
        }
        return (T) bean;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        String beanName = beanNames.get(type);
        if (beanName == null) {
            throw new RuntimeException("Bean no encontrado para tipo: " + type.getSimpleName());
        }
        return (T) beans.get(beanName);
    }
    
    public void registerComponent(Class<?> clazz) {
        if (registeredClasses.contains(clazz)) {
            return;
        }
        
        registeredClasses.add(clazz);
        
        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            String beanName = component.value().isEmpty() ? 
                clazz.getSimpleName().toLowerCase() : component.value();
            
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                registerBean(beanName, instance);
                performDependencyInjection(instance);
            } catch (Exception e) {
                System.err.println("❌ Error creando bean: " + beanName + " - " + e.getMessage());
            }
        }
        
        if (clazz.isAnnotationPresent(Service.class)) {
            Service service = clazz.getAnnotation(Service.class);
            String beanName = service.value().isEmpty() ? 
                clazz.getSimpleName().toLowerCase() : service.value();
            
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                registerBean(beanName, instance);
                performDependencyInjection(instance);
            } catch (Exception e) {
                System.err.println("❌ Error creando servicio: " + beanName + " - " + e.getMessage());
            }
        }
    }
    
    public void performDependencyInjection(Object target) {
        Class<?> clazz = target.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                
                try {
                    field.setAccessible(true);
                    
                    Object beanToInject;
                    if (!autowired.value().isEmpty()) {
                        beanToInject = getBean(autowired.value());
                    } else {
                        beanToInject = getBean(field.getType());
                    }
                    
                    field.set(target, beanToInject);
                    System.out.println("✅ Inyección realizada: " + clazz.getSimpleName() + 
                                     "." + field.getName() + " -> " + beanToInject.getClass().getSimpleName());
                    
                } catch (Exception e) {
                    if (autowired.required()) {
                        throw new RuntimeException("Error en inyección requerida: " + e.getMessage(), e);
                    } else {
                        System.err.println("⚠️ Inyección opcional falló: " + field.getName() + " - " + e.getMessage());
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T createBean(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            performDependencyInjection(instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error creando bean: " + e.getMessage(), e);
        }
    }
    
    public Set<String> getBeanNames() {
        return beans.keySet();
    }
    
    public boolean containsBean(String name) {
        return beans.containsKey(name);
    }
    
    public void close() {
        DatabaseAdapter adapter = getBean("databaseAdapter");
        if (adapter != null) {
            adapter.closeConnection();
        }
        
        beans.clear();
        beanNames.clear();
        registeredClasses.clear();
        System.out.println("✅ ApplicationContext: Contexto cerrado");
    }
}
