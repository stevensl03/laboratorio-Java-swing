package com.mycompany.aplicacionpoo.dto;

public class PersonaDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private String tipo;
    
    public PersonaDTO() {}
    
    public PersonaDTO(int id, String nombres, String apellidos, String email, String tipo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "PersonaDTO{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    
    // Método para validar datos
    public boolean isValid() {
        return nombres != null && !nombres.trim().isEmpty() &&
               apellidos != null && !apellidos.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               tipo != null && !tipo.trim().isEmpty();
    }
}
