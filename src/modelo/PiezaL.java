package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaL representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaL extends Pieza {
    Cuadrado centralL = new Cuadrado(200, -50, Color.pink);

    /**
     * Crea una instancia de la clase PiezaLinversa con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaL(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, -50, Color.pink));
        cuadrados.add(centralL);
        cuadrados.add(new Cuadrado(250, -50, Color.pink));
        cuadrados.add(new Cuadrado(250, -100, Color.pink));
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
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO()) && juego.posicionValida(x + juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralL.getX(), centralL.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralL.getX(), centralL.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralL.getX() + juego.getLADOCUADRADO(), centralL.getY() + juego.getLADOCUADRADO());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO(), y - juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralL.getX() + juego.getLADOCUADRADO(), centralL.getY());
                    cuadrados.get(2).setLocation(centralL.getX() - juego.getLADOCUADRADO(), centralL.getY());
                    cuadrados.get(3).setLocation(centralL.getX() - juego.getLADOCUADRADO(), centralL.getY() + juego.getLADOCUADRADO());
                    posicion = 2;
                }
                break;
            case 2:
                if (juego.posicionValida(x - juego.getLADOCUADRADO(), y - juego.getLADOCUADRADO()) && juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralL.getX(), centralL.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralL.getX(), centralL.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralL.getX() - juego.getLADOCUADRADO(), centralL.getY() - juego.getLADOCUADRADO());
                    posicion = 3;
                }
                break;

            case 3:
                if (juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO()) && juego.posicionValida(x + juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y)) {
                    cuadrados.get(0).setLocation(centralL.getX() - juego.getLADOCUADRADO(), centralL.getY());
                    cuadrados.get(2).setLocation(centralL.getX() + juego.getLADOCUADRADO(), centralL.getY());
                    cuadrados.get(3).setLocation(centralL.getX() + juego.getLADOCUADRADO(), centralL.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
