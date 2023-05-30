import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Juego {
    /*

     */

    private final int MAX_X = 500;
    private final int MAX_Y = 900;
    private final int LADOCUADRADO = 50;
    private int puntos;
    private String nombre;
    private Pieza piezaActual;
    private ControlBBDD controlBBDD = new ControlBBDD();
    private boolean gameOver;
    private ArrayList<Cuadrado> cuadradosSuelo = new ArrayList<>();
    private ArrayList<Cuadrado> linea = new ArrayList();


    private VentanaPrincipal ventanaPrincipal;

    //Constructor de juego que necesita de ventanaPrincipal y se igualan.
    public Juego(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        gameOver = false;
    }

    /*
    Método donde se instanciará la pieza piezaActual de forma aleatoria.
     */
    public void instanciarPiezaNueva() {

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

    //Método que recorre los cuadrados de piezaActual y suma LADOCADRADO en el eje Y.
    public void moverPiezaAbajo() {
            if (!choquePiezaSuelo()) {
                piezaActual.moverAbajo();
            } else {
                anadePiezaBorraLineasCompletasGeneraPiezaNueva();
            }
    }

    public void rotarPieza() {
        boolean movValido = true;
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            if (!posicionValida(cuadrado.getX(), cuadrado.getY())) {
                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.rotar();
        }
    }

    //Método que recorre los cuadrados de piezaActual y suma LADOCADRADO en el eje X.
    public void moverPiezaDerecha() {
        boolean movValido = true;
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            if (!posicionValida(cuadrado.getX() + LADOCUADRADO, cuadrado.getY())) {
                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.moverDerecha();
        }
    }
    //Método que recorre los cuadrados de piezaActual y resta LADOCADRADO en el eje X.

    public void moverPiezaIzquierda() {
         boolean movValido = true;
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            if (!posicionValida(cuadrado.getX() - LADOCUADRADO, cuadrado.getY())) {

                movValido = false;
            }
        }
        if (movValido) {
            piezaActual.moverIzquierda();
        }
    }

    /*
    Método que llama al método que instancia una pieza y luego utiliza el método pintarPieza de la clase VentanaPrincipal
    con la pieza instanciada para darle los valores a la JLabel de los cuadrados del ArrayList
    */
    public void crearPieza() {
        instanciarPiezaNueva();
        ventanaPrincipal.pintarPieza(piezaActual);
    }

    public void anadirAlSuelo() {
        Iterator<Cuadrado> actual = piezaActual.cuadrados.iterator();
        while (actual.hasNext()) {
            Cuadrado c = actual.next();
            cuadradosSuelo.add(c);
        }
    }

    public boolean choquePiezaSuelo() {
        boolean choca = false;
        Iterator<Cuadrado> actual = piezaActual.cuadrados.iterator();
        while (actual.hasNext()) {
            Cuadrado cactual = actual.next();
            if (!posicionValida(cactual.getX(), cactual.getY() + LADOCUADRADO)) {
                choca = true;
            }
        }
        return choca;
    }

    public void borrarLineasCompletas() {
        int lineaCompleta = 10;
        Iterator<Cuadrado> actual = piezaActual.cuadrados.iterator();
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

            }
            linea.removeAll(linea);
        }
    }

    private void actualizarBloques() {
        int y= linea.get(0).getY();
        Iterator<Cuadrado> suelo = cuadradosSuelo.iterator();
        while (suelo.hasNext()) {
            Cuadrado csuelo = suelo.next();
            if (csuelo.getY()<y) {
                csuelo.setLocation(csuelo.getX(),csuelo.getY()+LADOCUADRADO);
            }

        }
    }

    public void borrarLinea() {
        Iterator<Cuadrado> blinea = linea.iterator();
        while (blinea.hasNext()) {
            Cuadrado este = blinea.next();
            ventanaPrincipal.borrarCuadrado(este.getLabelCuadrado());
            cuadradosSuelo.removeAll(linea);
            puntos = +500;
        }
    }

    private void anadePiezaBorraLineasCompletasGeneraPiezaNueva() {
        if (!comprobarPerder()) {
            anadirAlSuelo();
            borrarLineasCompletas();
            crearPieza();
            ventanaPrincipal.pintarPieza(piezaActual);
        } else {
            gameOver = true;
            controlBBDD.guardarPuntuacion(nombre, puntos);
        }
    }

    public void enviarPuntuacion() {
    }

    private boolean comprobarPerder() {
        Iterator<Cuadrado> actual = piezaActual.cuadrados.iterator();
        while (actual.hasNext()) {
            Cuadrado cactual = actual.next();
            if (cactual.getY() < 0) {
                return true;
            }
        }
        return false;
    }

    //getter de MAX_X
    public int getMAX_X() {
        return MAX_X;
    }

    //getter de MAX_Y
    public int getMAX_Y() {
        return MAX_Y;
    }

    //getter de LADOCADRADO
    public int getLADOCUADRADO() {
        return LADOCUADRADO;
    }

    public Pieza getPiezaActual() {
        return piezaActual;
    }
}
