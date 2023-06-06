package logica;

import interfaz.VentanaPrincipal;
import modelo.Pieza;
import modelo.Cuadrado;
import modelo.PiezaCuadrada;
import modelo.PiezaBarra;
import modelo.PiezaLinversa;
import modelo.PiezaL;
import modelo.PiezaT;
import modelo.PiezaZ;
import modelo.PiezaZinversa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * La clase Juego representa el juego principal del tetris y su lógica.
 */
public class Juego {

    private final int MAX_X = 500;
    private final int MAX_Y = 900;
    private final int LADOCUADRADO = 50;
    private Pieza piezaActual;
    private ControlBBDD controlBBDD = new ControlBBDD();
    private int puntuacion = 0;
    private ArrayList<Cuadrado> cuadradosSuelo = new ArrayList<>();
    private ArrayList<Cuadrado> linea = new ArrayList();
    private VentanaPrincipal ventanaPrincipal;

    /**
     * Crea una nueva instancia de la clase Juego.
     *
     * @param ventanaPrincipal la ventana principal del juego.
     */
    public Juego(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    private void instanciarPiezaNueva() {
        Random random = new Random();
        int aleatorio = random.nextInt(0, 7);
        switch (aleatorio) {
            case 0:
                piezaActual = new PiezaBarra(this);
                break;
            case 1:
                piezaActual = new PiezaCuadrada(this);
                break;
            case 2:
                piezaActual = new PiezaL(this);
                break;
            case 3:
                piezaActual = new PiezaT(this);
                break;
            case 4:
                piezaActual = new PiezaZ(this);
                break;
            case 5:
                piezaActual = new PiezaLinversa(this);
                break;
            case 6:
                piezaActual = new PiezaZinversa(this);
                break;
        }
    }

    /**
     * Verifica si la posición dada es válida dentro del tablero de juego.
     *
     * @param x la coordenada x.
     * @param y la coordenada y.
     * @return true si la posición es válida, false en caso contrario.
     */
    public boolean posicionValida(int x, int y) {
        boolean posicionValida = true;
        if (x > MAX_X - LADOCUADRADO || x < 0 || y > MAX_Y - LADOCUADRADO || y < -LADOCUADRADO) {
            posicionValida = false;
        }
        Iterator<Cuadrado> suelo = cuadradosSuelo.iterator();
        while (suelo.hasNext()) {
            Cuadrado csuelo = suelo.next();
            if ((csuelo.getX() == x && csuelo.getY() == y)) {
                posicionValida = false;
            }
        }
        return posicionValida;
    }

    /**
     * Mueve la pieza actual hacia abajo si es posible. En caso de choque con el suelo, se añade la pieza al suelo y se realizan las acciones correspondientes.
     */
    public void moverPiezaAbajo() {
        if (!choquePiezaSuelo()) {
            piezaActual.moverAbajo();
        } else {
            anadePiezaBorraLineasCompletasGeneraPiezaNueva();
        }
    }

    /**
     * Rota la pieza actual si la posición resultante es válida.
     */
    public void rotarPieza() {
        boolean movValido = true;
        for (int i = 0; i < piezaActual.getCuadrados().size(); i++) {
            Cuadrado cuadrado = piezaActual.getCuadrados().get(i);
            if (!posicionValida(cuadrado.getX(), cuadrado.getY())) {
                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.rotar();
        }
    }

    /**
     * Mueve la pieza actual hacia la derecha si la posición resultante es válida.
     */
    public void moverPiezaDerecha() {
        boolean movValido = true;
        for (int i = 0; i < piezaActual.getCuadrados().size(); i++) {
            Cuadrado cuadrado = piezaActual.getCuadrados().get(i);
            if (!posicionValida(cuadrado.getX() + LADOCUADRADO, cuadrado.getY())) {
                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.moverDerecha();
        }
    }

    /**
     * Mueve la pieza actual hacia la izquierda si la posición resultante es válida.
     */
    public void moverPiezaIzquierda() {
        boolean movValido = true;
        for (int i = 0; i < piezaActual.getCuadrados().size(); i++) {
            Cuadrado cuadrado = piezaActual.getCuadrados().get(i);
            if (!posicionValida(cuadrado.getX() - LADOCUADRADO, cuadrado.getY())) {
                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.moverIzquierda();
        }
    }

    /**
     * Crea una nueva pieza y la muestra en la ventana principal.
     */
    public void crearPieza() {
        instanciarPiezaNueva();
        ventanaPrincipal.pintarPieza(piezaActual);
    }

    private void anadirAlSuelo() {
        Iterator<Cuadrado> actual = piezaActual.getCuadrados().iterator();
        while (actual.hasNext()) {
            Cuadrado c = actual.next();
            cuadradosSuelo.add(c);
        }
    }

    private boolean choquePiezaSuelo() {
        boolean choca = false;
        Iterator<Cuadrado> actual = piezaActual.getCuadrados().iterator();
        while (actual.hasNext()) {
            Cuadrado cactual = actual.next();
            if (!posicionValida(cactual.getX(), cactual.getY() + LADOCUADRADO)) {
                choca = true;
            }
        }
        return choca;
    }

    private void borrarLineasCompletas() {
        int lineaCompleta = 10;
        Iterator<Cuadrado> actual = piezaActual.getCuadrados().iterator();
        while (actual.hasNext()) {
            Cuadrado cactual = actual.next();
            Iterator<Cuadrado> suelo = cuadradosSuelo.iterator();
            while (suelo.hasNext()) {
                Cuadrado csuelo = suelo.next();

                if (csuelo.getY() == cactual.getY()) {
                    linea.add(csuelo);
                }
            }
            if (linea.size() == lineaCompleta) {
                borrarLinea();
                actualizarBloques();
                puntuacion += 500;
            }
            linea.removeAll(linea);
        }
    }

    private void actualizarBloques() {
        int y = linea.get(0).getY();
        Iterator<Cuadrado> suelo = cuadradosSuelo.iterator();
        while (suelo.hasNext()) {
            Cuadrado csuelo = suelo.next();
            if (csuelo.getY() < y) {
                csuelo.setLocation(csuelo.getX(), csuelo.getY() + LADOCUADRADO);
            }
        }
    }

    private void borrarLinea() {
        Iterator<Cuadrado> blinea = linea.iterator();
        while (blinea.hasNext()) {
            Cuadrado este = blinea.next();
            ventanaPrincipal.borrarCuadrado(este.getLabelCuadrado());
            cuadradosSuelo.removeAll(linea);
        }
    }

    private void anadePiezaBorraLineasCompletasGeneraPiezaNueva() {
        if (!comprobarPerder()) {
            anadirAlSuelo();
            borrarLineasCompletas();
            crearPieza();
            ventanaPrincipal.pintarPieza(piezaActual);
        } else {
            ventanaPrincipal.mostrarFinJuego();
        }
    }

    private boolean comprobarPerder() {
        Iterator<Cuadrado> suelo = cuadradosSuelo.iterator();
        while (suelo.hasNext()) {
            Cuadrado cactual = suelo.next();
            if (cactual.getY() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el tamaño de los lados de los cuadrados en el juego.
     *
     * @return el tamaño de los lados de los cuadrados.
     */
    public int getLADOCUADRADO() {
        return LADOCUADRADO;
    }

    /**
     * Devuelve la pieza actual en el juego.
     *
     * @return la pieza actual.
     */
    public Pieza getPiezaActual() {
        return piezaActual;
    }

    /**
     * Devuelve la puntuación actual del juego.
     *
     * @return la puntuación actual.
     */
    public int getPuntuacion() {
        return puntuacion;
    }
}