package modelo;

import logica.Juego;

import java.util.ArrayList;

public abstract class Pieza {

    protected ArrayList<Cuadrado> cuadrados;
    protected Juego juego;
    protected int posicion;

    /**
     * Crea una instancia de la clase Pieza con el juego dado.
     * Inicializa la lista de cuadrados y la posición de la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public Pieza(Juego juego) {
        this.juego = juego;
        cuadrados = new ArrayList();
        posicion = 0;
    }

    /**
     * Método abstracto para rotar la pieza.
     * La implementación específica se realiza en las subclases.
     */
    public abstract void rotar();

    /**
     * Mueve la pieza hacia la izquierda desplazando cada cuadrado.
     */
    public void moverIzquierda() {
        for (int i = 0; i < cuadrados.size(); i++)
            cuadrados.get(i).setLocation(cuadrados.get(i).getX() - juego.getLADOCUADRADO(), cuadrados.get(i).getY());
    }

    /**
     * Mueve la pieza hacia la derecha desplazando cada cuadrado.
     */
    public void moverDerecha() {
        for (int i = 0; i < cuadrados.size(); i++)
            cuadrados.get(i).setLocation(cuadrados.get(i).getX() + juego.getLADOCUADRADO(), cuadrados.get(i).getY());
    }

    /**
     * Mueve la pieza hacia abajo desplazando cada cuadrado.
     */
    public void moverAbajo() {
        for (int i = 0; i < cuadrados.size(); i++)
            cuadrados.get(i).setLocation(cuadrados.get(i).getX(), cuadrados.get(i).getY() + juego.getLADOCUADRADO());
    }

    /**
     * Obtiene la lista de cuadrados que forman la pieza.
     *
     * @return La lista de cuadrados de la pieza.
     */
    public ArrayList<Cuadrado> getCuadrados() {
        return cuadrados;
    }
}
