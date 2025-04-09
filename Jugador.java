/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandslam4;

public class Jugador {

    private int ranking;
    private String apellido;
    private String nombre;
    private String nacionalidad;
    private Jugador nodoIzq;
    private Jugador nodoDerecho;

    public Jugador(int ranking, String apellido, String nombre, String nacionalidad) {
        this.ranking = ranking;
        this.apellido = apellido;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.nodoIzq = null;
        this.nodoDerecho = null;
    }

    // Métodos getters y setters
    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Jugador getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(Jugador nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    public Jugador getNodoDerecho() {
        return nodoDerecho;
    }

    public void setNodoDerecho(Jugador nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }

    public void insertar(int ranking, String apellido, String nombre, String nacionalidad) {
        if (ranking < this.ranking) {
            if (this.nodoIzq == null) {
                this.nodoIzq = new Jugador(ranking, apellido, nombre, nacionalidad);
            } else {
                this.nodoIzq.insertar(ranking, apellido, nombre, nacionalidad);
            }
        } else {
            if (this.nodoDerecho == null) {
                this.nodoDerecho = new Jugador(ranking, apellido, nombre, nacionalidad);
            } else {
                this.nodoDerecho.insertar(ranking, apellido, nombre, nacionalidad);
            }
        }
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}

