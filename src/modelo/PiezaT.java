package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaT representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaT extends Pieza {
    Cuadrado centralT = new Cuadrado(200, -50, Color.blue);

    /**
     * Crea una instancia de la clase PiezaT con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaT(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, -50, Color.blue));
        cuadrados.add(centralT);
        cuadrados.add(new Cuadrado(250, -50, Color.blue));
        cuadrados.add(new Cuadrado(200, -100, Color.blue));
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
                    cuadrados.get(0).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(2).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(3).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    posicion = 2;
                }
                break;
            case 2:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    posicion = 3;
                }
                break;
            case 3:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(2).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(3).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
