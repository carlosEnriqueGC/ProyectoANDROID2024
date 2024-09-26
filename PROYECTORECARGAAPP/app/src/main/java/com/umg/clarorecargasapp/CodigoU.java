package com.umg.clarorecargasapp;

public class CodigoU {
    private String Tipo_codigo;
    private int Precio_codigo;
    private String Secuencia_codigo;
    private String estado_codigo;

    // Constructor
    public CodigoU(String Tipo_codigo, int Precio_codigo, String Secuencia_codigo, String estado_codigo) {
        this.Tipo_codigo = Tipo_codigo;
        this.Precio_codigo = Precio_codigo;
        this.Secuencia_codigo = Secuencia_codigo;
        this.estado_codigo = estado_codigo;
    }

    // Getters y Setters
    public String getTipo_codigo() { return Tipo_codigo; }
    public void setTipo_codigo(String Tipo_codigo) { this.Tipo_codigo = Tipo_codigo; }
    public int getPrecio_codigo() { return Precio_codigo; }
    public void setPrecio_codigo(int Precio_codigo) { this.Precio_codigo = Precio_codigo; }
    public String getSecuencia_codigo() { return Secuencia_codigo; }
    public void setSecuencia_codigo(String Secuencia_codigo) { this.Secuencia_codigo = Secuencia_codigo; }
    public String getEstado_codigo() { return estado_codigo; }
    public void setEstado_codigo(String estado_codigo) { this.estado_codigo = estado_codigo; }
}
