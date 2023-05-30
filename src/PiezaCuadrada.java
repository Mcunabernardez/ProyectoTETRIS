import java.awt.*;

public class PiezaCuadrada extends Pieza{

    Cuadrado central = new Cuadrado(250, 0, Color.green);

    public PiezaCuadrada(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(200,0, Color.green));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(200,50, Color.green));
        cuadrados.add(new Cuadrado(250,50, Color.green));
    }

    @Override
    public void rotar() {
    }
}
