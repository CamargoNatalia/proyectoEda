/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandslam4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class GrandSlam4 {

        public static void main(String[] Args) {
            Torneo torneo = new Torneo();
            Scanner scanner = new Scanner(System.in);
            int cantidadJugadores = 0;

            while (true) {
                System.out.println("\n=== TORNEO DE TENIS ===");
                System.out.println("1. Ingresar cantidad de competidores");
                System.out.println("2. Agregar jugador/a");
                System.out.println("3. Generar partidos");
                System.out.println("4. Cargar resultado de un partido");
                System.out.println("5. Mostrar resultados actuales");
                System.out.println("6. Avanzar a la siguiente ronda");
                System.out.println("7. Salir");
                System.out.print("Elige una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la cantidad de competidores (debe ser potencia de 2; por ejemplo, 2, 4, 8, 16): ");
                        cantidadJugadores = scanner.nextInt();
                        if (torneo.esPotenciaDeDos(cantidadJugadores) && cantidadJugadores <= 16) {
                            System.out.println("El número de jugadores es potencia de dos.");
                            System.out.println("Seleccione la opción 2 para cargar los jugadores.");
                        } else {
                            System.out.println("Error: ingrese otro número menor o igual a 16 y que sea potencia de 2.");
                        }
                        break;

                    case 2:
                        if (cantidadJugadores > 0) {
                            ArrayList<Jugador> jugadores = new ArrayList<>();
                            int jugadoresInscritos = 0;
                            while (jugadoresInscritos < cantidadJugadores) {
                                System.out.print("Ingrese el ranking del jugador a insertar: ");
                                int ranking = scanner.nextInt();
                                scanner.nextLine();

                                boolean repetido = false;
                                for (Jugador jugador : jugadores) {
                                    if (jugador != null && jugador.getRanking() == ranking) {
                                        repetido = true;
                                        break;
                                    }
                                }

                                if (!repetido) {
                                    System.out.print("Ingrese el nombre del jugador: ");
                                    String nombre = scanner.nextLine();
                                    System.out.print("Ingrese el apellido del jugador: ");
                                    String apellido = scanner.nextLine();
                                    System.out.print("Ingrese la nacionalidad del jugador: ");
                                    String nacionalidad = scanner.nextLine();

                                    jugadores.add(new Jugador(ranking, apellido, nombre, nacionalidad));
                                    jugadoresInscritos++;
                                } else {
                                    System.out.println("El ranking ya fue ingresado. Intente con otro.");
                                }
                            }
                            torneo.setJugadores(jugadores);
                            System.out.println("Ya se han inscrito todos los jugadores.");
                        } else {
                            System.out.println("Primero, seleccione la opción 1 para establecer la cantidad de jugadores.");
                        }
                        break;

                    case 3:
                        torneo.generarFixture();
                        break;

                    case 4:
                        if (torneo.getRondas().isEmpty()) {
                            System.out.println("No hay partidos generados aún. Seleccione la opción 3 para generar los partidos.");
                        } else {
                            System.out.print("Ingrese el número del partido: ");
                            int numeroPartido = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer del scanner

                            Partido partido = torneo.buscarPartido(numeroPartido);
                            if (partido == null) {
                                System.out.println("No se encontró el partido. Intente con otro número.");
                            } else {
                                System.out.print("Ingrese el nombre del jugador ganador: ");
                                String nombreGanador = scanner.nextLine();

                                Jugador ganador = torneo.buscarJugadorPorNombre(nombreGanador);
                                if (ganador == null) {
                                    System.out.println("No se encontró el jugador. Intente con otro nombre.");
                                } else if (!ganador.equals(partido.getJugador1()) && !ganador.equals(partido.getJugador2())) {
                                    System.out.println("El jugador ingresado no participó en este partido. Intente con otro nombre.");
                                } else {
                                    partido.setGanador(ganador);
                                    System.out.print("Ingrese el resultado del partido (ejemplo: 6-3, 5-4): ");
                                    String resultadoSets = scanner.nextLine();
                                    partido.setResultadoSets(resultadoSets);
                                    System.out.println("Resultado actualizado.");
                                }
                            }
                        }
                        break;

                    case 5:
                        // Mostrar resultados actuales incluyendo los sets
                        int rondaActual = torneo.getRondaActual();  // Obtener el índice de la ronda actual
                        torneo.mostrarRonda(rondaActual, "Ronda " + (rondaActual + 1));
                        break;

                    case 6:
                        if (torneo.todosResultadosIngresados()) {
                            torneo.avanzarSiguienteRonda();
                        } else {
                            System.out.println("Aún hay partidos sin resultado en la ronda actual.");
                        }
                        break;

                    case 7:
                        System.exit(0);

                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }
    }

