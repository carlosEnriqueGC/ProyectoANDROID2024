package com.umg.clarorecargasapp;

public class Tienda {
    private int id;
    private String nombre;
    private int pin;
    private String estado;

    // Constructor
    public Tienda(int id, String nombre, int pin, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.pin = pin;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}