package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaZinversa representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaZinversa extends Pieza {
    Cuadrado centralZinv = new Cuadrado(200, -50, Color.magenta);

    /**
     * Crea una instancia de la clase PiezaZinversa con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaZinversa(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, -50, Color.magenta));
        cuadrados.add(centralZinv);
        cuadrados.add(new Cuadrado(200, -100, Color.magenta));
        cuadrados.add(new Cuadrado(250, -100, Color.magenta));
    }

    /**
     * Realiza la rotación de la pieza en el juego.
     * La rotación se basa en la posición actual de la pieza.
     */
    @Override
    public void rotar() {
        int y = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getY();
        int x = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getX();

        switch (posicion) {
            case 0:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralZinv.getX(), centralZinv.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralZinv.getX() - juego.getLADOCUADRADO(), centralZinv.getY());
                    cuadrados.get(3).setLocation(centralZinv.getX() - juego.getLADOCUADRADO(), centralZinv.getY() - juego.getLADOCUADRADO());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralZinv.getX() - juego.getLADOCUADRADO(), centralZinv.getY());
                    cuadrados.get(2).setLocation(centralZinv.getX(), centralZinv.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralZinv.getX() + juego.getLADOCUADRADO(), centralZinv.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }

    }
}