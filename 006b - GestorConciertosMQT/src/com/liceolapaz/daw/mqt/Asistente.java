package com.liceolapaz.daw.mqt;

import java.util.Scanner;

public class Asistente {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private int anhoNacimiento;
    private Entrada entrada; // 💡 Ahora cada asistente puede tener una entrada

    private static String[] emailsRegistrados = new String[26000];
    private static int contadorEmail = 0;

    public Asistente(String nombre, String apellido, String dni, String email, int anhoNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.anhoNacimiento = anhoNacimiento;
        this.entrada = null; // Inicialmente no tiene entrada

        emailsRegistrados[contadorEmail] = email;
        contadorEmail++;
    }

    public static Asistente addAsistente() {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce tu nombre: ");
        String auxNombre = teclado.nextLine();

        System.out.print("Introduce tu apellido: ");
        String auxApellido = teclado.nextLine();

        System.out.print("Introduce tu DNI: ");
        String auxDni = teclado.nextLine();

        System.out.print("Introduce tu email: ");
        String auxEmail = teclado.nextLine();

        if (comprobarEmail(auxEmail)) {
            System.out.println("Este email ya está registrado.");
            return null;
        }

        System.out.print("Introduce tu año de nacimiento (YYYY): ");
        int auxAnhoNacimiento = teclado.nextInt();
        teclado.nextLine(); // Limpiar buffer

        int edad = 2025 - auxAnhoNacimiento;

        if (edad >= 18) {
            return new Asistente(auxNombre, auxApellido, auxDni, auxEmail, auxAnhoNacimiento);
        } else {
            System.out.println("Debes ser mayor de edad.");
            return null;
        }
    }

    private static boolean comprobarEmail(String email) {
        for (String registrado : emailsRegistrados) {
            if (registrado != null && registrado.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void asignarEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    // Getters opcionales
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public int getAnhoNacimiento() {
        return anhoNacimiento;
    }
}