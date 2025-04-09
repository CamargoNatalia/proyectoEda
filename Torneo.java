/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandslam4;
import java.util.ArrayList;
import java.util.Collections;

public class Torneo {

    private ArrayList<Integer> idsJugadores = new ArrayList<>();
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<ArrayList<Partido>> rondas = new ArrayList<>();
    private int rondaActual = 0;
    private int numeroPartidoGlobal = 1; // Contador global para números de partidos

    public boolean esPotenciaDeDos(int numero) {
        return (numero > 0) && ((numero & (numero - 1)) == 0);
    }

    public boolean jugadorRepe(int ranking) {
        return idsJugadores.contains(ranking);
    }

    public void jugadorRepetido(int ranking, String apellido, String nombre, String nacionalidad, int jugadoresActuales, int maxJugadores) {
        if (jugadoresActuales >= maxJugadores) {
            System.out.println("No se pueden inscribir más jugadores. El límite es " + maxJugadores + " jugadores.");
            return;
        }

        if (idsJugadores.contains(ranking)) {
            System.out.println("El jugador con ranking número " + ranking + " ya fue ingresado.");
            return;
        }

        idsJugadores.add(ranking);
        insertar(ranking, apellido, nombre, nacionalidad);
    }

    public void insertar(int ranking, String apellido, String nombre, String nacionalidad) {
        Jugador nuevoJugador = new Jugador(ranking, apellido, nombre, nacionalidad);
        jugadores.add(nuevoJugador);
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<ArrayList<Partido>> getRondas() {
        return rondas;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public Partido buscarPartido(int numeroPartido) {
        for (ArrayList<Partido> ronda : rondas) {
            for (Partido partido : ronda) {
                if (partido.getNumeroPartido() == numeroPartido) {
                    return partido;
                }
            }
        }
        return null;
    }

    public Jugador buscarJugador(int ranking) {
        for (Jugador jugador : jugadores) {
            if (jugador.getRanking() == ranking) {
                return jugador;
            }
        }
        return null;
    }

    public void generarFixture() {
        if (!esPotenciaDeDos(jugadores.size()) || jugadores.size() > 16) {
            System.out.println("La cantidad de jugadores debe ser una potencia de dos y no mayor a 16.");
            return;
        }

        // Ordenar jugadores por ranking
        Collections.sort(jugadores, (j1, j2) -> Integer.compare(j1.getRanking(), j2.getRanking()));

        if (jugadores.size() == 2) {
            generarFinalDirecto();
        } else {
            generarRondaInicial();
            mostrarRonda(0, "Ronda Inicial");
        }
    }

    private void generarFinalDirecto() {
        rondas.clear();
        ArrayList<Partido> finalRonda = new ArrayList<>();
        Partido finalPartido = new Partido(jugadores.get(0), jugadores.get(1), numeroPartidoGlobal++);
        finalPartido.setDescripcion("Final: " + jugadores.get(0).getNombre() + " vs " + jugadores.get(1).getNombre());
        finalRonda.add(finalPartido);
        rondas.add(finalRonda);

        mostrarRonda(0, "Final");
    }

    private void generarRondaInicial() {
        rondas.clear();
        ArrayList<Partido> rondaInicial = new ArrayList<>();
        int n = jugadores.size();
        for (int i = 0; i < n / 2; i++) {
            Jugador jugador1 = jugadores.get(i);
            Jugador jugador2 = jugadores.get(n - 1 - i);  // Esto garantiza que se enfrenten según la consigna
            rondaInicial.add(new Partido(jugador1, jugador2, numeroPartidoGlobal++));
        }
        rondas.add(rondaInicial);
    }

    private void generarSiguienteRonda(int rondaActual) {
        ArrayList<Partido> rondaAnterior = rondas.get(rondaActual);
        ArrayList<Partido> nuevaRonda = new ArrayList<>();
        for (int i = 0; i < rondaAnterior.size() / 2; i++) {
            Partido partido1 = rondaAnterior.get(i * 2);
            Partido partido2 = rondaAnterior.get(i * 2 + 1);

            Jugador ganador1 = partido1.getGanador();
            Jugador ganador2 = partido2.getGanador();

            Partido nuevoPartido = new Partido(ganador1, ganador2, numeroPartidoGlobal++);
            nuevaRonda.add(nuevoPartido);
        }
        rondas.add(nuevaRonda);
    }

     void mostrarRonda(int ronda, String nombreRonda) {
        if (ronda == 0) {
            nombreRonda = "Ronda Inicial";
        } else if (rondas.get(ronda).size() == 1 && ronda == rondas.size() - 1) {
            nombreRonda = "Final";
        } else if (rondas.get(ronda).size() == 2) {
            nombreRonda = "Semifinal";
        } else if (rondas.get(ronda).size() == 4) {
            nombreRonda = "Cuartos de Final";
        } else {
            nombreRonda = "Ronda " + (ronda + 1);
        }

        System.out.println("\n=== " + nombreRonda + " ===");
        for (Partido partido : rondas.get(ronda)) {
            System.out.println(partido.mostrarConGanador());
        }
    }

    private void mostrarFinal(int rondaActual) {
        if (rondas.get(rondaActual).size() == 1) {
            System.out.println("\n=== Final ===");
            Partido finalPartido = rondas.get(rondaActual).get(0);
            finalPartido.setDescripcion("Ganador Semifinal 1 vs Ganador Semifinal 2");
            System.out.println(finalPartido);
        }
    }

    public boolean todosResultadosIngresados() {
        if (rondas.isEmpty()) {
            return false;
        }
        for (Partido partido : rondas.get(rondaActual)) {
            if (partido.getGanador() == null) {
                return false;
            }
        }
        return true;
    }

    public void avanzarSiguienteRonda() {
        if (rondas.get(rondaActual).size() == 1) {
            mostrarFinal(rondaActual);
        } else {
            generarSiguienteRonda(rondaActual);
            rondaActual++;
            mostrarRonda(rondaActual, "");
        }
    }

    public Jugador buscarJugadorPorNombre(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
    }
}
