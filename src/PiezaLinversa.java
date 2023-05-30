import java.awt.*;

public class PiezaLinversa extends Pieza {
    Cuadrado central = new Cuadrado(200, 50, Color.orange);

    public PiezaLinversa(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(250, 50, Color.orange));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(150, 50, Color.orange));
        cuadrados.add(new Cuadrado(150, 0, Color.orange));
    }


    @Override
    public void rotar() {
        int y = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getY();
        int x = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getX();

        switch (posicion) {
            case 0:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(central.getX(), central.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY() - juego.getLADOCUADRADO());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(2).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(3).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY() + juego.getLADOCUADRADO());
                    posicion = 2;
                }
                break;
            case 2:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(central.getX(), central.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY() + juego.getLADOCUADRADO());
                    posicion = 3;
                }
                break;

            case 3:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(2).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(3).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
