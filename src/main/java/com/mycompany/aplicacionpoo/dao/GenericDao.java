/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.aplicacionpoo.dao;
import java.util.List;

/**
 *
 * @author steve
 * @param <T>
 */

public interface GenericDao<T> {
    void guardar(T objeto);
    void actualizar(T objeto);
    void eliminar(int id);
    List<T> mostrarTodos();
    T buscar(int id);
}
