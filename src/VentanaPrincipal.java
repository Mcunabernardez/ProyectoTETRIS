import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    Timer timerFPS;
    JPanel panelJuego;
    private ControlBBDD controlBBDD = new ControlBBDD();
    Juego juego;


    //Método principal donde juntamos la partida y la interfraz para que exista el Tetris.
    public VentanaPrincipal() {
        iniciarInterfaz();
    }

    /*
    Método que instancia el juego y llamamos la método de juego crearPieza para generar la primera pieza
    Usamos el método requestFocus() que se encarga de que el panel reciba eventos de teclado (necesario para poder mover las piezas)
    Llamamos al método timer para que inicie la partida.
     */
    private void iniciarPartida() {
        juego = new Juego(this);
        juego.crearPieza();
        tiempoPartida();
    }

    //Método main que instancia el objeto que engloba el juego
    public static void main(String[] args) {
        new VentanaPrincipal();
    }

    //Método que da un tamaño a la JLabel de un cuadrado y lo añade al panel.
    public void pintarCuadrado(JLabel cuadrado) {
        cuadrado.setSize(juego.getLADOCUADRADO(), juego.getLADOCUADRADO());
        panelJuego.add(cuadrado);
    }

    //Método que utiliza el método anterior y recorre el ArrayList de cuadrados para montar la pieza con las JLabel
    public void pintarPieza(Pieza pieza) {
        for (int i = 0; i < pieza.cuadrados.size(); i++)
            pintarCuadrado(pieza.cuadrados.get(i).getLabelCuadrado());
    }

    public void borrarCuadrado(JLabel lblCadrado) {
        panelJuego.remove(lblCadrado);
    }

    //Método que engloba la configuración del panel de juego, PDTE ampliar y mejorar
    private void iniciarInterfaz() {
        JPanel paneltetris = new JPanel();
        paneltetris.setPreferredSize(new Dimension(800, 1000));

        JPanel panelMenu = new JPanel(null);
        panelMenu.setPreferredSize(new Dimension(800, 1000));
        paneltetris.add(panelMenu);

        JButton btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setBounds(300, 230, 200, 80);
        btnNuevaPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarPartida();
                panelJuego.setVisible(true);
                panelMenu.setVisible(false);
                panelJuego.requestFocus();
            }
        });
        panelMenu.add(btnNuevaPartida);

        JButton btnPuntuacion = new JButton("Puntuación");
        btnPuntuacion.setBounds(300, 460, 200, 80);
        btnPuntuacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlBBDD.obtenerTop10();
            }
        });
        panelMenu.add(btnPuntuacion);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(300, 690, 200, 80);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelMenu.add(btnSalir);

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
        panelJuego.setFocusable(true);
        panelJuego.setVisible(false);
        panelJuego.requestFocusInWindow();
        panelJuego.setPreferredSize(new Dimension(500, 900));

        paneltetris.add(Box.createVerticalGlue());
        paneltetris.add(panelJuego);
        JFrame frame = new JFrame("TETRIS");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 800) / 2;
        int y = (screenSize.height - 1000) / 2;
        frame.setLocation(x, y);
        frame.setUndecorated(true); // Desaparezca el borde superior de una ventana
        frame.add(paneltetris);
        frame.pack();
        frame.setVisible(true);
    }


    //Método con un timer de swing, el delay mide en ms y llama al método de juego para que la pieza actual baje cada vez que pase un segundo
    private void tiempoPartida() {
        timer = new Timer(1000, (ActionEvent e) -> {
            juego.moverPiezaAbajo();
        });
        timer.start();

        timerFPS = new Timer(50, (ActionEvent e) -> {
            panelJuego.updateUI();
        });
        timerFPS.start();
    }
}
