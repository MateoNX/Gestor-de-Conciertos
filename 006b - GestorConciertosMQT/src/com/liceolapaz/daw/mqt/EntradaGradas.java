package com.liceolapaz.daw.mqt;

import java.util.Scanner;

public class EntradaGradas extends Entrada {
    private boolean[][] gradasOcupadas = new boolean[25][1000];
    private static int auxCodigo = 1;
    private int fila;
    private int asiento;

    public EntradaGradas() {
        super(25.50, "G-" + auxCodigo);
        auxCodigo++;

        int[] ubicacion = asignarAsiento();
            this.fila = ubicacion[0];
            this.asiento = ubicacion[1];
    }

    @Override
    public Entrada addEntrada() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("""
            El precio de entrada es de 25.50€. ¿Deseas continuar?
            1. Sí
            2. No
            """);

        int opcion = teclado.nextInt();

        if (opcion == 1) {
            return new EntradaGradas();
        } else {
            return null;
        }
    }

    @Override
    public void mostrarEntrada(Entrada entrada) {

    }

    public int[] asignarAsiento() { // Este método recorre las gradas y convierte el primer elemento false (libre) en true (ocupado).
        for (int i = 0; i <25; i++) { // Este bucle recorre todas las filas.
            for (int j = 0; j < 1000; j++) { // Este bucle recorre todos los asientos de las filas.
                if (gradasOcupadas[i][j] == false) {
                    gradasOcupadas[i][j] = true;
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public boolean[][] getGradasOcupadas() {
        return gradasOcupadas;
    }

    public static int getAuxCodigo() {
        return auxCodigo;
    }

    public int getFila() {
        return fila;
    }

    public int getAsiento() {
        return asiento;
    }
}
