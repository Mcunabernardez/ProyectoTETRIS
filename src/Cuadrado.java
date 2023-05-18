import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;

public class Cuadrado {
    //Creamos las variables de X e Y que usará el objeto cuadrado como atributos logicos de coordenadas de los cuadrados
    private int x;
    private int y;
    private JLabel labelCuadrado;


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

    //setter especial que guarda la posición actual de cada cuadrado y su label
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        labelCuadrado.setLocation(x, y);
    }

    //getters y setter
    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public JLabel getLabelCuadrado() {
        return labelCuadrado;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

