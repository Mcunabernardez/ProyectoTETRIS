import java.awt.*;

public class PiezaT extends Pieza {
    Cuadrado centralT = new Cuadrado(200, 50, Color.blue);

    public PiezaT(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150, 50, Color.blue));
        cuadrados.add(centralT);
        cuadrados.add(new Cuadrado(250, 50, Color.blue));
        cuadrados.add(new Cuadrado(200, 0, Color.blue));
    }


    @Override
    public void rotar() {
        int y = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getY();
        int x = juego.getPiezaActual().getCuadrados().get(1).getLabelCuadrado().getX();

        switch (posicion) {
            case 0:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    posicion = 1;
                }
                break;
            case 1:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(2).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(3).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    posicion = 2;
                }
                break;
            case 2:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX(), centralT.getY() + juego.getLADOCUADRADO());
                    cuadrados.get(2).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    cuadrados.get(3).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    posicion = 3;
                }
                break;
            case 3:
                if (juego.posicionValida(x, y - juego.getLADOCUADRADO()) && juego.posicionValida(x - juego.getLADOCUADRADO(), y) && juego.posicionValida(x - juego.getLADOCUADRADO(), y + juego.getLADOCUADRADO())) {
                    cuadrados.get(0).setLocation(centralT.getX() - juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(2).setLocation(centralT.getX() + juego.getLADOCUADRADO(), centralT.getY());
                    cuadrados.get(3).setLocation(centralT.getX(), centralT.getY() - juego.getLADOCUADRADO());
                    posicion = 0;
                }
                break;
        }
    }
}
