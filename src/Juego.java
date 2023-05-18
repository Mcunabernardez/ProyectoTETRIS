import java.util.Random;

public class Juego {
    /*
    Se declaran unas constantes para delimitar el tamaño máximo que tendrá el panel de juego.
    y la constante que da valor a la longitud del lado de un cuadrado.
    Se declara un objeto pieza y un objeto ventanaPrincipal para trabajar con ellos en la lógica del juego.
     */

    private final int MAX_X = 500;
    private final int MAX_Y = 900;
    private final int LADOCADRADO = 50;
    private Pieza piezaActual;

    private VentanaPrincipal ventanaPrincipal;

    //Constructor de juego que necesita de ventanaPrincipal y se igualan.
    public Juego(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /*
    Método donde se instanciará la pieza piezaActual de forma aleatoria.
     */
    public void instanciarPiezaNueva() {

        Random random = new Random();
        int aleatorio = random.nextInt(0, 6);
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

    //Método que recorre los cuadrados de piezaActual y suma LADOCADRADO en el eje Y.
    public void moverPiezaAbajo() {
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            cuadrado.setLocation(cuadrado.getX(), cuadrado.getY() + LADOCADRADO);
        }
    }

    public void rotarPieza() {
        piezaActual.rotar();
    }

    //Método que recorre los cuadrados de piezaActual y suma LADOCADRADO en el eje X.
    public void moverPiezaDerecha() {
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            cuadrado.setLocation(cuadrado.getX() + LADOCADRADO, cuadrado.getY());
        }


    }
    //Método que recorre los cuadrados de piezaActual y resta LADOCADRADO en el eje X.

    public void moverPiezaIzquierda() {
        for (int i = 0; i < piezaActual.cuadrados.size(); i++) {
            Cuadrado cuadrado = piezaActual.cuadrados.get(i);
            cuadrado.setLocation(cuadrado.getX() - LADOCADRADO, cuadrado.getY());
        }
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
    public int getLADOCADRADO() {
        return LADOCADRADO;
    }

    /*
    Método que llama al método que instancia una pieza y luego utiliza el método pintarPieza de la clase VentanaPrincipal
    con la pieza instanciada para darle los valores a la JLabel de los cuadrados del ArrayList
     */
    public void crearPieza() {
        instanciarPiezaNueva();
        ventanaPrincipal.pintarPieza(piezaActual);
    }
}
