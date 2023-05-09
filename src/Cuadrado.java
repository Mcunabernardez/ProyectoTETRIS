import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

public class Cuadrado {
    //Creamos las variables del alto y el ancho que queremos que tenga la label que usaremos como cuadrado
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

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        labelCuadrado.setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public JLabel getLabelCuadrado() {
        return labelCuadrado;
    }

    public int getY() {
        return y;
    }
}
