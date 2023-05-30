import java.awt.Color;

public class PiezaBarra extends Pieza {

    Cuadrado central = new Cuadrado(200, 50, Color.yellow);

    public PiezaBarra(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, 50, Color.yellow));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(250, 50, Color.yellow));
        cuadrados.add(new Cuadrado(300, 50, Color.yellow));
    }

    @Override
    public void rotar() {
        int y = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getY();
        int x = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getX();

        switch (posicion) {
            case 0:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO()) && juego.posicionValida(x, y + juego.getLADOCUADRADO() * 2)) {                    cuadrados.get(0).setLocation(central.getX(), central.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(central.getX(), central.getY() + juego.getLADOCUADRADO() * 2);
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO(), y) && juego.posicionValida(x + juego.getLADOCUADRADO() * 2, y)) {                    cuadrados.get(0).setLocation(central.getX() - juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(2).setLocation(central.getX() + juego.getLADOCUADRADO(), central.getY());
                    cuadrados.get(3).setLocation(central.getX() + juego.getLADOCUADRADO() * 2, central.getY());
                    posicion = 0;
                }
                break;
        }
    }

}
