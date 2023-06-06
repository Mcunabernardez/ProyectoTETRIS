package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaLinversa representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaLinversa extends Pieza {
    Cuadrado centralLinv = new Cuadrado(200, -50, Color.orange);

    /**
     * Crea una instancia de la clase PiezaLinversa con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaLinversa(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(250, -50, Color.orange));
        cuadrados.add(centralLinv);
        cuadrados.add(new Cuadrado(150, -50, Color.orange));
        cuadrados.add(new Cuadrado(150, -100, Color.orange));
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
                    cuadrados.get(0).setLocation(centralLinv.getX(), centralLinv.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralLinv.getX(), centralLinv.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralLinv.getX() + juego.getLADOCUADRADO(), centralLinv.getY() - juego.getLADOCUADRADO());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralLinv.getX() - juego.getLADOCUADRADO(), centralLinv.getY());
                    cuadrados.get(2).setLocation(centralLinv.getX() + juego.getLADOCUADRADO(), centralLinv.getY());
                    cuadrados.get(3).setLocation(centralLinv.getX() + juego.getLADOCUADRADO(), centralLinv.getY() + juego.getLADOCUADRADO());
                    posicion = 2;
                }
                break;
            case 2:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralLinv.getX(), centralLinv.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralLinv.getX(), centralLinv.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralLinv.getX() - juego.getLADOCUADRADO(), centralLinv.getY() + juego.getLADOCUADRADO());
                    posicion = 3;
                }
                break;

            case 3:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralLinv.getX() + juego.getLADOCUADRADO(), centralLinv.getY());
                    cuadrados.get(2).setLocation(centralLinv.getX() - juego.getLADOCUADRADO(), centralLinv.getY());
                    cuadrados.get(3).setLocation(centralLinv.getX() - juego.getLADOCUADRADO(), centralLinv.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
