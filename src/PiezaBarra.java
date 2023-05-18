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
    public boolean rotar() {

        switch (posicion) {
            case 0:
                cuadrados.get(0).setLocation(central.getX(), central.getY() - juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(central.getX(), central.getY() + juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(central.getX(), central.getY() + juego.getLADOCADRADO() * 2);
                posicion = 1;
            case 1:
                cuadrados.get(0).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY());
                cuadrados.get(2).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY());
                cuadrados.get(3).setLocation(central.getX() + juego.getLADOCADRADO() * 2, central.getY());
                posicion = 0;
        }

        return true;
    }

}
