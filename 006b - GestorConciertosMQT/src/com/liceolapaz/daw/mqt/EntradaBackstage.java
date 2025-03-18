package com.liceolapaz.daw.mqt;

import java.util.Scanner;

public class EntradaBackstage extends Entrada {
    private static int auxCodigo = 1;
    public EntradaBackstage() {
        super(37.50, "P-" + auxCodigo);
        auxCodigo++;
    }

    @Override
    public Entrada addEntrada() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("""
            El precio de entrada es de 100€. ¿Deseas continuar?
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
