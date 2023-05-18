import java.util.ArrayList;

public abstract class Pieza {

    /*
    Declaramos un arraylist de cuadrados que usaremos para instanciar el objeto Pieza (una pieza se compone de 4 cuadrados)
    Se declara como ArrayList ya que se usará en un futuro para más métodos de juego

     */

    protected ArrayList<Cuadrado> cuadrados;
    protected Juego juego;
    protected int posicion;

    /*
    Constructor que iguala el objeto Juego e instancia el arraylist de cuadrados.
    Se le da un valor inicial de posicion a una pieza para cuando se instancie.
     */
    public Pieza(Juego juego) {
        this.juego = juego;
        cuadrados = new ArrayList();
        posicion = 0;
    }
    //Método abstracto que usaremos para guardar la lógica de la rotación de piezas en cada uno de los distintos tipos de pieza, usando la variable posicion
    public abstract boolean rotar();

}
