package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaZ representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaZ extends Pieza {
    Cuadrado centralZ = new Cuadrado(200, -50, Color.red);

    /**
     * Crea una instancia de la clase PiezaZ con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaZ(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(250, -50, Color.red));
        cuadrados.add(centralZ);
        cuadrados.add(new Cuadrado(200, -100, Color.red));
        cuadrados.add(new Cuadrado(150, -100, Color.red));
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
                    cuadrados.get(0).setLocation(centralZ.getX(), centralZ.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralZ.getX() + juego.getLADOCUADRADO(), centralZ.getY());
                    cuadrados.get(3).setLocation(centralZ.getX() + juego.getLADOCUADRADO(), centralZ.getY() - juego.getLADOCUADRADO());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralZ.getX() + juego.getLADOCUADRADO(), centralZ.getY());
                    cuadrados.get(2).setLocation(centralZ.getX(), centralZ.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralZ.getX() - juego.getLADOCUADRADO(), centralZ.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
