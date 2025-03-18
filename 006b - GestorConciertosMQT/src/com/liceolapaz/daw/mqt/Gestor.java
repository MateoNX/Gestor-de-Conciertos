package com.liceolapaz.daw.mqt;

import java.util.Scanner;

public class Gestor {
    static Scanner teclado = new Scanner(System.in);

    private static int contadorEntradaGradas = 0; // Máx. 25.000
    private static int contadorEntradaPista = 0; // Máx. 900
    private static int contadorEntradaBackstage = 0; // Máx. 100
    private static int contadorEntradaAsistentes = 0; // Máx. 26.000

    // Array para almacenar asistentes
    private static Asistente[] listaAsistentes = new Asistente[26000]; // Tamaño máximo de 26.000 asistentes

    public static void main(String[] args) {
        do {
            escribirMenu();

            int opcionMenu = teclado.nextInt();

            switch (opcionMenu) {
                case 1: // Reservar entrada.
                    if (contadorEntradaAsistentes < 26000) {
                        reservarEntrada();
                    } else {
                        System.out.println("Ya no quedan entradas disponibles. ¡Más suerte en el siguiente concierto!");
                    }
                    break;
                case 2: // Mostrar entrada reservada.
                    mostrarEntradaReservada();
                    break;
                case 3: // Listar entradas vendidas.
                    System.out.println(listarEntradasVendidas());
                    break;
                case 4: // Mostrar número de entradas disponibles.
                    System.out.println("Ahora mismo, quedan disponibles:\nEntradas de gradas: " + (25000 - contadorEntradaGradas) + "\nEntradas de pista: " + (900 - contadorEntradaPista) +"\nEntradas de pista (con backstage): " + (100 - contadorEntradaBackstage));
                    break;
                case 0:
                    System.out.println("¡Hasta la próxima!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (true);
    }

    private static void reservarEntrada() {
        System.out.println("Se va a crear un nuevo asistente: ");
        Asistente asistente = Asistente.addAsistente();

        if (asistente == null) {
            System.out.println("No ha sido posible registrar un nuevo asistente.");
            return;
        } else {
            System.out.print("""
                    Tipos de entrada disponibles:
                    1. Entrada en gradas (25.50€)
                    2. Entrada en pista (37.50€)
                    3. Entrada en pista con acceso a backstage (100.00€)
                    0. Cancelar
                    Elige tu tipo de entrada:
                    """);

            int opcionEntrada = teclado.nextInt();

            Entrada nuevaEntrada = null;

            switch (opcionEntrada) {
                case 1: // Entrada en gradas.
                    if (contadorEntradaGradas < 25000) {
                        nuevaEntrada = new EntradaGradas().addEntrada();
                        if (nuevaEntrada != null) {
                            contadorEntradaGradas++;
                        } else {
                            System.out.println("No quedan entradas de gradas disponibles.");
                        }
                    }
                    break;

                case 2: // Entrada en pista.
                    if (contadorEntradaPista < 900) {
                        nuevaEntrada = new EntradaPista().addEntrada();
                        if (nuevaEntrada != null) {
                            contadorEntradaPista++;
                        }
                    } else {
                        System.out.println("No quedan entradas de pista disponibles.");
                    }
                    break;

                case 3: // Entrada en backstage
                    if (contadorEntradaBackstage < 100) {
                        nuevaEntrada = new EntradaBackstage().addEntrada();
                        if (nuevaEntrada != null) {
                            contadorEntradaBackstage++;
                        } else {
                            System.out.println("No quedan entradas de pista disponibles.");
                        }
                    }
                    break;

                case 0: // Cancelar
                    return;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            if (nuevaEntrada != null) {
                asistente.asignarEntrada(nuevaEntrada);

                // Añadir el asistente al array
                listaAsistentes[contadorEntradaAsistentes] = asistente;
                contadorEntradaAsistentes++;

                System.out.println("Entrada con código " + nuevaEntrada.codigoEntrada + " reservada exitosamente para " + asistente.getNombre() + " con e-mail " + asistente.getEmail());
            }
        }
    }

    private static void mostrarEntradaReservada() {
        System.out.print("Introduce el email del asistente: ");
        String email = teclado.next();

        // Buscar el asistente por su email en el array
        Asistente asistenteEncontrado = null;
        for (int i = 0; i < contadorEntradaAsistentes; i++) {
            if (listaAsistentes[i].getEmail().equals(email)) {
                asistenteEncontrado = listaAsistentes[i];
                break;
            }
        }

        if (asistenteEncontrado != null) {
            Entrada entradaReservada = asistenteEncontrado.getEntrada();
            System.out.println("Entrada reservada para el asistente con email " + email + ":");
            System.out.println("Código de entrada: " + entradaReservada.codigoEntrada);
            System.out.println("Tipo de entrada: " + entradaReservada.getClass().getSimpleName());
            System.out.println("Nombre del asistente: " + asistenteEncontrado.getNombre());
        } else {
            System.out.println("No se ha encontrado ninguna entrada reservada para ese email.");
        }
    }

    private static String listarEntradasVendidas() {
        String listado = "";

        for (int i = 0; i < contadorEntradaAsistentes; i++) {
            Asistente asistente = listaAsistentes[i];
            Entrada entrada = asistente.getEntrada();

            // Si la entrada es de tipo EntradaGradas, mostrar fila y asiento
            String filaAsiento = "";
            if (entrada instanceof EntradaGradas) {
                filaAsiento = " Fila " + ((EntradaGradas) entrada).getFila() + " Asiento " + ((EntradaGradas) entrada).getAsiento();
            }

            // Construir el listado de entradas
            listado += "Entrada " + entrada.codigoEntrada + "; Tipo: " + entrada.getClass().getSimpleName() +
                    filaAsiento + " Precio " + entrada.getPrecio() + "€ " + asistente.getNombre() + " " + asistente.getApellido() +
                    " " + asistente.getEmail() + " DNI " + asistente.getDni() + " " + asistente.getAnhoNacimiento() + "\n";
        }
        return listado;
    }

    private static void escribirMenu() {
        System.out.print("""
                BIENVENIDO AL GESTOR DE ENTRADAS PARA EL CONCIERTO
                1. Reservar entrada
                2. Mostrar entrada reservada
                3. Listar entradas vendidas
                4. Mostrar número de entradas disponibles
                0. Salir
                Escoja una opción:
                """);
    }
}
