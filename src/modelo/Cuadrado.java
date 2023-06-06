package modelo;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;

/**
 * La clase Cuadrado representa un cuadrado en el juego Tetris.
 * Cada cuadrado tiene una ubicación en el tablero definida por las coordenadas X e Y,
 * así como un color que determina su apariencia visual.
 * Los cuadrados se utilizan para formar las diferentes piezas del juego Tetris.
 */
public class Cuadrado {
    private int x;
    private int y;
    private JLabel labelCuadrado;

    /**
     * Crea una instancia de la clase Cuadrado con las coordenadas dadas y el color especificado.
     * Configura la ubicación y apariencia del JLabel del cuadrado.
     *
     * @param x     La coordenada X del cuadrado.
     * @param y     La coordenada Y del cuadrado.
     * @param color El color del cuadrado.
     */
    public Cuadrado(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        labelCuadrado = new JLabel();
        labelCuadrado.setLocation(x, y);
        labelCuadrado.setBackground(color);
        labelCuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelCuadrado.setBorder(BorderFactory.createCompoundBorder(labelCuadrado.getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        labelCuadrado.setOpaque(true);
    }

    /**
     * Establece la ubicación del cuadrado en las coordenadas especificadas.
     *
     * @param x La nueva coordenada X del cuadrado.
     * @param y La nueva coordenada Y del cuadrado.
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        labelCuadrado.setLocation(x, y);
    }

    /**
     * Obtiene la coordenada X del cuadrado.
     *
     * @return La coordenada X del cuadrado.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y del cuadrado.
     *
     * @return La coordenada Y del cuadrado.
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el JLabel asociado al cuadrado.
     *
     * @return El JLabel del cuadrado.
     */
    public JLabel getLabelCuadrado() {
        return labelCuadrado;
    }
}

