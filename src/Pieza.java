import java.util.ArrayList;

public abstract class Pieza {

    protected ArrayList<Cuadrado> cuadrados;
    protected Juego juego;
    protected int posicion;

    public Pieza(Juego juego) {
        this.juego = juego;
        cuadrados = new ArrayList();

        posicion = 0;

    }

    public abstract boolean rotar();

    public void moverDerecha() {
    }

    public void moverIzquierda() {
    }

    public void moverAbajo() {
    }

}
