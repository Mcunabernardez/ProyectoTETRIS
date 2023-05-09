import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class VentanaPrincipal {
    Timer timer;
    JPanel panelJuego;
    Juego juego;


    public VentanaPrincipal() {
        iniciarInterfaz();
        iniciarPartida();
    }
    public static void main(String[] args) {
        new VentanaPrincipal();
    }

    public void pintarPieza(JLabel cuadrado) {
        cuadrado.setSize(juego.getLADOCADRADO(), juego.getLADOCADRADO());
        panelJuego.add(cuadrado);
    }

    private void iniciarInterfaz() {
        panelJuego = new JPanel(null);
        panelJuego.setBackground(Color.gray);

        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case 37, 65 ->  // caracter A || FLECHA IZQUIERDA
                            juego.moverPiezaIzquierda();
                    case 38, 87 -> // caracter W || FLECHA ARRIBA
                            juego.rotarPieza();
                    case 39, 68 -> // caracter D || FLECHA DERECHA
                            juego.moverPiezaDerecha();
                    case 40, 83 -> // caracter S || FLECHA ABAJO
                    {
                        juego.moverPiezaAbajo();
                        timer.restart();
                    }
                }
            }
        });
        panelJuego.setPreferredSize(new Dimension(500, 900));
        JFrame frame = new JFrame("TETRIS");
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelJuego);
        frame.pack();
        frame.setVisible(true);
    }

    private void iniciarPartida() {
        juego = new Juego(this);
    }

    //generamos un método con un timer de swing, el delay mide en ms y llama al método de juego para que la pieza actual baje cada vez que pase un segundo
    private void tiempoPartida() {
        timer = new Timer(1000, (ActionEvent e) -> {
            juego.moverPiezaAbajo();
        });
        timer.start();
    }
}
