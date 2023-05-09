import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Juego {

    private final int MAX_X = 500;
    private final int MAX_Y = 900;

    private final int LADOCADRADO = 50;

    public Juego(VentanaPrincipal ventanaPrincipal) {

    }

    //PDTE generar un método que recorra un array con cada tipo de pieza y seleccione una de ellas de forma random
    public void piezaActual() {
        ArrayList<Object> piezas = new ArrayList<>();
    }

    public void moverPiezaAbajo() {
        //PDTE encapsular en un if si es posible que la pieza se mueva hacia abajo

    }

    public void rotarPieza() {
    }

    public boolean moverPiezaDerecha() {
        return true;

    }

    public boolean moverPiezaIzquierda() {
        return true;

    }

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }

    public int getLADOCADRADO() {
        return LADOCADRADO;
    }
}
