package com.liceolapaz.daw.mqt;

import java.util.Scanner;

public class EntradaPista extends Entrada {

    private static int auxCodigo = 1;

    public EntradaPista() {
        super(100.00, "B-" + auxCodigo);
        auxCodigo++;
    }

    @Override
    public Entrada addEntrada() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("""
            El precio de entrada es de 37.50€. ¿Deseas continuar?
            1. Sí
            2. No
            """);

        int opcion = teclado.nextInt();

        if (opcion == 1) {
            return new EntradaPista();
        } else {
            return null;
        }
    }

    @Override
    public void mostrarEntrada(Entrada entrada) {

    }
}

