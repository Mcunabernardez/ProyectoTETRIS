package modelo;

import logica.Juego;

import java.awt.Color;

/**
 * La clase modelo.PiezaBarra representa una pieza de tipo barra en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaBarra extends Pieza {
    Cuadrado central = new Cuadrado(200, -50, Color.yellow);

    /**
     * Crea una instancia de la clase PiezaBarra con el juego dado.
     * Inicializa la posición y los cuadrados que forman la pieza.
     *
     * @param juego El juego al que pertenece la pieza.
     */
    public PiezaBarra(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, -50, Color.yellow));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(250, -50, Color.yellow));
        cuadrados.add(new Cuadrado(300, -50, Color.yellow));
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
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO() * 2)) {
                    cuadrados.get(0).setLocation(central.getX(), central.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO() * 2);
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO() * 2, y)) {
                    cuadrados.get(0).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(2).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(3).setLocation(central.getX() + juego.getLADOCUADRADO() * 2, central.getY());
                    posicion = 0;
                }
                break;
        }
    }
}
