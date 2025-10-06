/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.aplicacionpoo.observer;

/**
 *
 * @author steve
 */
public interface Subject {
    
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    
}

