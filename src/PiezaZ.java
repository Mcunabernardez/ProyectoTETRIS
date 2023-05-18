import java.awt.*;

public class PiezaZ extends Pieza {

    Cuadrado central = new Cuadrado(200, 50, Color.red);

    public PiezaZ(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(250, 50, Color.red));
        cuadrados.add(central);
        cuadrados.add(new Cuadrado(200, 0, Color.red));
        cuadrados.add(new Cuadrado(150, 0, Color.red));
    }


    @Override
    public boolean rotar() {

        switch (posicion) {
            case 0:
                cuadrados.get(0).setLocation(central.getX(), central.getY() + juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY());
                cuadrados.get(3).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY() - juego.getLADOCADRADO());
                posicion = 1;
            case 1:
                cuadrados.get(0).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY());
                cuadrados.get(2).setLocation(central.getX(), central.getY() - juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY() - juego.getLADOCADRADO());
                posicion = 0;
        }

        return true;
    }
}
