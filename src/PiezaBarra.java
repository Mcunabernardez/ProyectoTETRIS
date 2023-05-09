import java.awt.*;
public class PiezaBarra extends Pieza{


    public PiezaBarra(Juego juego) {
        super(juego);
        cuadrados.add(new Cuadrado(150,50, Color.yellow));
        cuadrados.add(new Cuadrado(200,50, Color.yellow));
        cuadrados.add(new Cuadrado(250,50, Color.yellow));
        cuadrados.add(new Cuadrado(300,50, Color.yellow));
    }

    @Override
    public boolean rotar() {
        switch (posicion) {
            case 0:
                cuadrados.get(0).setLocation(cuadrados.get(0).getX()-juego.getLADOCADRADO(),cuadrados.get(0).getY()+juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(cuadrados.get(2).getX()+juego.getLADOCADRADO(),cuadrados.get(2).getY()-juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(cuadrados.get(3).getX()+juego.getLADOCADRADO()*2,cuadrados.get(3).getY()-juego.getLADOCADRADO()*2);
                posicion=1;
            case 1:
                cuadrados.get(0).setLocation(cuadrados.get(0).getX()+juego.getLADOCADRADO(),cuadrados.get(0).getY()-juego.getLADOCADRADO());
                cuadrados.get(2).setLocation(cuadrados.get(2).getX()-juego.getLADOCADRADO(),cuadrados.get(2).getY()+juego.getLADOCADRADO());
                cuadrados.get(3).setLocation(cuadrados.get(3).getX()-juego.getLADOCADRADO()*2,cuadrados.get(3).getY()+juego.getLADOCADRADO()*2);
                posicion=0;
        }

        return true;
    }

}
