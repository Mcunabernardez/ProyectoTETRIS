import java.awt.*;

public class PiezaCuadrada extends Pieza{


    public PiezaCuadrada(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(200,0, Color.yellow));
        cuadrados.add(new Cuadrado(250,0, Color.yellow));
        cuadrados.add(new Cuadrado(200,50, Color.yellow));
        cuadrados.add(new Cuadrado(250,50, Color.yellow));
    }


    @Override
    public boolean rotar() {
        return true;
    }
}
