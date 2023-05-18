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
    public boolean rotar() {

        switch (posicion) {
            case 0:
                cuadrados.get(0).setLocation(central.getX(), central.getY() + juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(central.getX(), central.getY() - juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY() - juego.getLADOCADRADO());
                posicion = 1;
            case 1:
                cuadrados.get(0).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY());
                cuadrados.get(2).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY());
                cuadrados.get(3).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY() + juego.getLADOCADRADO());
                posicion = 2;
            case 2:
                cuadrados.get(0).setLocation(central.getX(), central.getY() - juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(central.getX(), central.getY() + juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY() + juego.getLADOCADRADO());
                posicion = 3;

            case 3:
                cuadrados.get(0).setLocation(central.getX() + juego.getLADOCADRADO(), central.getY());
                cuadrados.get(2).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY());
                cuadrados.get(3).setLocation(central.getX() - juego.getLADOCADRADO(), central.getY() - juego.getLADOCADRADO());
                posicion = 0;
        }

        return true;
    }
}
