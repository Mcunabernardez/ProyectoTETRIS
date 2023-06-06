package modelo;

import logica.Juego;

import java.awt.*;

/**
 * La clase modelo.PiezaCuadrada representa una pieza de tipo cuadrado en el juego.
 * Extiende la clase abstracta modelo.Pieza.
 */
public class PiezaCuadrada extends Pieza {

    Cuadrado central = new Cuadrado(250, -100, Color.green);

    /**
     * Crea una nueva instancia de la clase modelo.PiezaCuadrada.
     *
     * @param juego el objeto logica.Juego al que pertenece la pieza.
     */
    public PiezaCuadrada(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(200, -100, Color.green));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(200, -50, Color.green));
        cuadrados.add(new Cuadrado(250, -50, Color.green));
    }

    /**
     * La pieza cuadrada no tiene rotación, por lo que este método no realiza ninguna acción.
     */
    @Override
    public void rotar() {
        // No se realiza ninguna rotación para la pieza cuadrada
    }
}