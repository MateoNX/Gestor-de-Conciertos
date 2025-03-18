package com.liceolapaz.daw.mqt;

public abstract class Entrada {
    protected double precio;
    protected String codigoEntrada;

    public Entrada(double precio, String codigoEntrada) {
        this.precio = precio;
        this.codigoEntrada = codigoEntrada;
    }

    public abstract Entrada addEntrada();
    public abstract void mostrarEntrada(Entrada entrada);

    public double getPrecio() {
        return precio;
    }

    public String getCodigoEntrada() {
        return codigoEntrada;
    }
}
