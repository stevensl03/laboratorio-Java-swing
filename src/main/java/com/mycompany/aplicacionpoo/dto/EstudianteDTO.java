package com.mycompany.aplicacionpoo.dto;

public class EstudianteDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private int codigo;
    private String programaNombre;
    private boolean activo;
    private double promedio;
    
    // Constructor por defecto
    public EstudianteDTO() {}
    
    // Constructor completo
    public EstudianteDTO(int id, String nombres, String apellidos, String email, 
                        int codigo, String programaNombre, boolean activo, double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.codigo = codigo;
        this.programaNombre = programaNombre;
        this.activo = activo;
        this.promedio = promedio;
    }
    
    // Getters y Setters
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
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getProgramaNombre() {
        return programaNombre;
    }
    
    public void setProgramaNombre(String programaNombre) {
        this.programaNombre = programaNombre;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public double getPromedio() {
        return promedio;
    }
    
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", codigo=" + codigo +
                ", programaNombre='" + programaNombre + '\'' +
                ", activo=" + activo +
                ", promedio=" + promedio +
                '}';
    }
    
    // Método para validar datos
    public boolean isValid() {
        return nombres != null && !nombres.trim().isEmpty() &&
               apellidos != null && !apellidos.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               codigo > 0 &&
               programaNombre != null && !programaNombre.trim().isEmpty();
    }
}
