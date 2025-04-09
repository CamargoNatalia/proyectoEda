/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandslam4;


public class Partido {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador ganador;
    private int numeroPartido;
    private String descripcion;
    private String resultadoSets;

    public Partido(Jugador jugador1, Jugador jugador2, int numeroPartido) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.numeroPartido = numeroPartido;
        this.resultadoSets = "";
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public int getNumeroPartido() {
        return numeroPartido;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setResultadoSets(String resultadoSets) {
        this.resultadoSets = resultadoSets;
    }

    public String mostrarConGanador() {
        String descripcionPartido = "Partido " + numeroPartido + ": " + jugador1.getNombre() + " (" + jugador1.getRanking() + ") vs " + jugador2.getNombre() + " (" + jugador2.getRanking() + ")";
        if (ganador != null) {
            descripcionPartido += " | Ganador: " + ganador.getNombre() + " (" + ganador.getRanking() + ")";
            if (!resultadoSets.isEmpty()) {
                descripcionPartido += " | Resultado: " + resultadoSets;
            }
        }
        return descripcionPartido;
    }
}
