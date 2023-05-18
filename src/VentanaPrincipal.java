import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class VentanaPrincipal {

    /*
    Se declara un timer que usaremos para la lógica del juego.
    Se declara un JPanel que usaremos para la interfaz del juego.
    Se declara un objeto Juego juego que será necesario para la lógica del juego, interpretamos "juego" como partida y es necesaria una partida para tener un juego.
     */
    Timer timer;
    JPanel panelJuego;
    Juego juego;


    //Método principal donde juntamos la partida y la interfraz para que exista el Tetris.
    public VentanaPrincipal() {
        iniciarInterfaz();
        iniciarPartida();
    }

    /*
    Método que instancia el juego y llamamos la método de juego crearPieza para generar la primera pieza
    Usamos el método requestFocus() que se encarga de que el panel reciba eventos de teclado (necesario para poder mover las piezas)
    Llamamos al método timer para que inicie la partida.
     */
    private void iniciarPartida() {
        juego = new Juego(this);
        juego.crearPieza();

        panelJuego.requestFocus();
        tiempoPartida();
    }

    //Método main que instancia el objeto que engloba el juego
    public static void main(String[] args) {
        new VentanaPrincipal();
    }

    //Método que da un tamaño a la JLabel de un cuadrado y lo añade al panel.
    public void pintarCuadrado(JLabel cuadrado) {
        cuadrado.setSize(juego.getLADOCADRADO(), juego.getLADOCADRADO());
        panelJuego.add(cuadrado);
    }

    //Método que utiliza el método anterior y recorre el ArrayList de cuadrados para montar la pieza con las JLabel
    public void pintarPieza(Pieza pieza) {
        for (int i = 0; i < pieza.cuadrados.size(); i++)
            pintarCuadrado(pieza.cuadrados.get(i).getLabelCuadrado());

    }

    //Método que engloba la configuración del panel de juego, PDTE ampliar y mejorar
    private void iniciarInterfaz() {
        panelJuego = new JPanel(null);
        panelJuego.setBackground(Color.gray);


        //Función necesaria para poder hacer los movimientos necesarios en el tetris
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


    //Método con un timer de swing, el delay mide en ms y llama al método de juego para que la pieza actual baje cada vez que pase un segundo
    private void tiempoPartida() {
        timer = new Timer(1000, (ActionEvent e) -> {
            juego.moverPiezaAbajo();
            panelJuego.updateUI();
        });
        timer.start();
    }
}
