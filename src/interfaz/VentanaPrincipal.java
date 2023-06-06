package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import logica.ControlBBDD;
import logica.Juego;
import modelo.*;

/**
 * @author Maikel Cuña
 * @version v1.0
 * La clase VentanaPrincipal representa la interfaz principal del juego Tetris.
 * Contiene paneles para el juego, menú, pantalla de pausa, pantalla de perder y puntuaciones.
 * Permite interactuar con el juego y mostrar información al usuario.
 */
public class VentanaPrincipal {

    private Timer timer; // Temporizador para el movimiento automático de la pieza
    private Timer timerFPS; // Temporizador para actualizar la interfaz y la puntuación
    private JPanel panelJuego; // Panel para el juego principal
    private JPanel panelMenu; // Panel para el menú principal
    private JPanel panelPerder; // Panel para la pantalla de perder
    private JPanel panelPause; // Panel para la pantalla de pausa
    private JPanel paneltetris; // Panel principal del Tetris
    private JPanel pnlpuntos; // Panel para mostrar la puntuación
    private JPanel panelPuntuacion; // Panel para mostrar las puntuaciones
    private JTextField nombreTextField; // Campo de texto para ingresar el nombre del jugador
    private JLabel lblpuntos; // Etiqueta para mostrar la puntuación actual
    private JLabel lblpuntuacion; // Etiqueta para mostrar el título "Puntuación"
    private JLabel lblnombre; // Etiqueta para mostrar el título "Nombre"
    private JTable table; // Tabla para mostrar las puntuaciones
    private JScrollPane scrollPane; // Panel de desplazamiento para la tabla de puntuaciones
    private DefaultTableModel model; // Modelo de tabla para las puntuaciones
    private ControlBBDD controlBBDD = new ControlBBDD(); // Controlador para la base de datos
    private String nombre; // Nombre del jugador actual
    private Juego juego; // Objeto Juego para controlar la lógica del Tetris

    /**
     * Constructor de la clase VentanaPrincipal.
     * Inicia la interfaz del juego Tetris.
     */
    public VentanaPrincipal() {
        iniciarInterfaz();
    }

    /**
     * Inicia una nueva partida del juego Tetris.
     * Crea un nuevo objeto Juego y muestra la puntuación.
     */
    private void iniciarPartida() {
        juego = new Juego(this);
        juego.crearPieza();
        tiempoPartida();
        pnlpuntos.setVisible(true);
    }

    /**
     * Método main para ejecutar la aplicación Tetris.
     * Crea una nueva instancia de VentanaPrincipal.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        new VentanaPrincipal();
    }

    /**
     * Pinta un cuadrado en el panel de juego.
     *
     * @param cuadrado La etiqueta del cuadrado a pintar
     */
    private void pintarCuadrado(JLabel cuadrado) {
        cuadrado.setSize(juego.getLADOCUADRADO(), juego.getLADOCUADRADO());
        panelJuego.add(cuadrado);
    }

    /**
     * Pinta una pieza en el panel de juego.
     *
     * @param pieza La pieza a pintar
     */
    public void pintarPieza(Pieza pieza) {
        for (int i = 0; i < pieza.getCuadrados().size(); i++)
            pintarCuadrado(pieza.getCuadrados().get(i).getLabelCuadrado());
    }

    /**
     * Elimina un cuadrado del panel de juego.
     *
     * @param lblCadrado La etiqueta del cuadrado a eliminar
     */
    public void borrarCuadrado(JLabel lblCadrado) {
        panelJuego.remove(lblCadrado);
    }

    /**
     * Inicia la interfaz del juego Tetris.
     * Crea y configura los paneles y componentes necesarios.
     */
    private void iniciarInterfaz() {
        paneltetris = new JPanel(null);
        paneltetris.setPreferredSize(new Dimension(1000, 1000));
        paneltetris.setBackground(new java.awt.Color(27, 27, 27));

        pnlpuntos = new JPanel(null);
        pnlpuntos.setVisible(false);
        pnlpuntos.setBounds(25, 50, 200, 100);
        pnlpuntos.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlpuntos.setBackground(new java.awt.Color(27, 27, 27));
        pnlpuntos.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));


        lblpuntos = new JLabel();
        lblpuntos.setBounds(25, 50, 150, 45);
        lblpuntos.setBackground(new java.awt.Color(231, 231, 231));
        lblpuntos.setHorizontalAlignment(SwingConstants.CENTER);
        lblpuntos.setForeground(Color.white);
        lblpuntos.setFont(new java.awt.Font("Segoe Ui", 1, 32));
        lblpuntos.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlpuntos.add(lblpuntos);

        lblpuntuacion = new JLabel("Puntuacion");
        lblpuntuacion.setBounds(0, 0, 200, 50);
        lblpuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblpuntuacion.setForeground(Color.white);
        lblpuntuacion.setFont(new java.awt.Font("Segoe Ui", 1, 32));
        pnlpuntos.add(lblpuntuacion);

        paneltetris.add(pnlpuntos);

        panelMenu = new JPanel(null);
        panelMenu.setBounds(0, 0, 1000, 1000);
        paneltetris.add(panelMenu);

        JButton btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setBounds(400, 230, 200, 80);
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
        btnPuntuacion.setBounds(400, 460, 200, 80);
        btnPuntuacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarPuntuaciones();
            }
        });
        panelMenu.add(btnPuntuacion);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(400, 690, 200, 80);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelMenu.add(btnSalir);


        panelPause = new JPanel(null);
        panelPause.setBounds(300, 200, 400, 500);
        panelPause.setBackground(new java.awt.Color(27, 27, 27));
        panelPause.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelPause.setVisible(false);

        JButton btnResumen = new JButton("Continuar");
        btnResumen.setBounds(150, 100, 100, 50);
        btnResumen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelPause.setVisible(false);
                resumePause();
                panelJuego.requestFocusInWindow();
            }
        });
        panelPause.add(btnResumen);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(150, 200, 100, 50);
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        panelPause.add(btnReiniciar);

        JButton btnSalir2 = new JButton("Salir");
        btnSalir2.setBounds(150, 400, 100, 50);
        btnSalir2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarInterfaz();
            }
        });
        panelPause.add(btnSalir2);

        paneltetris.add(panelPause);
        panelJuego = new JPanel(null);
        panelJuego.setBackground(Color.gray);
        /**
         * Configura el panel de juego, incluyendo su fondo y la detección de eventos del teclado.
         * Permite el control del juego mediante las teclas de flecha y otras teclas específicas.
         * Las acciones se ejecutan solo si el panel de pausa no es visible.
         * Las teclas y sus acciones asociadas son las siguientes:
         * - Flecha izquierda (caracter A): mueve la pieza hacia la izquierda.
         * - Flecha derecha (caracter D): mueve la pieza hacia la derecha.
         * - Flecha arriba (caracter W): rota la pieza.
         * - Flecha abajo (caracter S): mueve la pieza hacia abajo y reinicia el temporizador.
         * - Caracter P o tecla Escape: muestra el panel de pausa y pausa el juego.
         */

        panelJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!panelPause.isVisible()) {
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
                        case 80, 27 ->// caracter P || ESCAPE
                        {
                            panelPause.setVisible(true);
                            tiempoPause();
                        }
                    }
                }
            }
        });
        panelJuego.setFocusable(true);
        panelJuego.setVisible(false);
        panelJuego.requestFocusInWindow();
        panelJuego.setBounds(250, 50, 500, 900);
        paneltetris.add(panelJuego);

        panelPerder = new JPanel(null);
        panelPerder.setBounds(300, 200, 400, 500);
        panelPerder.setBackground(new java.awt.Color(27, 27, 27));
        panelPerder.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelPerder.setVisible(false);
        lblnombre = new JLabel("Nombre:");
        lblnombre.setBounds(20, 50, 150, 50);
        lblnombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblnombre.setForeground(Color.white);
        lblnombre.setFont(new java.awt.Font("Segoe Ui", 1, 32));

        nombreTextField = new JTextField(3);
        nombreTextField.setBounds(230, 50, 150, 50);
        JButton btnguardar = new JButton("Guardar");
        btnguardar.setBounds(150, 120, 100, 50);
        btnguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre = nombreTextField.getText();
                if (nombre.length() == 3) {
                    controlBBDD.guardarPuntuacion(nombre, juego.getPuntuacion());
                } else {
                    System.out.println("El nombre debe tener 3 caracteres");
                }
            }
        });
        panelPerder.add(lblnombre);
        panelPerder.add(nombreTextField);
        panelPerder.add(btnguardar);
        JButton btnSalirFinal = new JButton("Salir");
        btnSalirFinal.setBounds(150, 300, 100, 50);
        btnSalirFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarInterfaz();
            }
        });
        panelPerder.add(btnSalirFinal);
        paneltetris.add(panelPerder);

        panelPuntuacion = new JPanel(null);
        panelPuntuacion.setBounds(300, 200, 400, 500);
        panelPuntuacion.setBackground(new java.awt.Color(27, 27, 27));
        panelPuntuacion.setBorder(javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelPuntuacion.setVisible(false);
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Puntuación");
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 400, 400);
        panelPuntuacion.add(scrollPane);

        JButton btnSalirPuntos = new JButton("Salir");
        btnSalirPuntos.setBounds(150, 425, 100, 50);
        btnSalirPuntos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarInterfaz();
            }
        });
        panelPuntuacion.add(btnSalirPuntos);
        paneltetris.add(panelPuntuacion);

        JFrame frame = new JFrame("TETRIS");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 1000) / 2;
        int y = (screenSize.height - 1000) / 2;
        frame.setLocation(x, y);
        frame.setUndecorated(true); // Desaparezca el borde superior de una ventana
        frame.add(paneltetris);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Inicia un temporizador para controlar el avance automático de la partida.
     * El temporizador mueve la pieza hacia abajo cada 500 milisegundos.
     * Además, se inicia otro temporizador para actualizar la interfaz de usuario
     * y mostrar la puntuación cada 50 milisegundos.
     */
    private void tiempoPartida() {
        timer = new Timer(500, (ActionEvent e) -> {
            juego.moverPiezaAbajo();
        });
        timer.start();

        timerFPS = new Timer(50, (ActionEvent e) -> {
            panelJuego.updateUI();
            lblpuntos.setText(String.valueOf(juego.getPuntuacion()));
        });
        timerFPS.start();
    }

    /**
     * Detiene los temporizadores de la partida y actualiza la interfaz de usuario.
     * Este método se utiliza al pausar el juego.
     */
    private void tiempoPause() {
        timer.stop();
        timerFPS.stop();
        panelJuego.updateUI();
    }

    /**
     * Reanuda la partida al reiniciar los temporizadores y actualizar la interfaz de usuario.
     * Este método se utiliza al reanudar el juego después de pausarlo.
     */
    private void resumePause() {
        timer.start();
        timerFPS.start();
        panelJuego.updateUI();
    }

    /**
     * Muestra la pantalla de fin de juego al detener los temporizadores,
     * ocultar el panel de juego y mostrar el panel de perder.
     * Además, se actualiza la interfaz de usuario.
     */
    public void mostrarFinJuego() {
        tiempoPause();
        panelJuego.setVisible(false);
        panelPerder.setVisible(true);
        panelJuego.updateUI();
    }

    /**
     * Reinicia el juego al reiniciar los temporizadores, limpiar el panel de juego,
     * crear una nueva instancia de la clase Juego, crear una nueva pieza y actualizar la interfaz de usuario.
     * Además, se oculta el panel de pausa, se muestra el panel de juego y se establece el enfoque en el panel de juego.
     */
    private void reiniciarJuego() {
        timer.restart();
        timerFPS.restart();
        panelJuego.removeAll();
        juego = new Juego(this);
        juego.crearPieza();
        panelJuego.updateUI();
        panelPause.setVisible(false);
        panelJuego.setVisible(true);
        panelJuego.requestFocusInWindow();
    }

    /**
     * Actualiza las puntuaciones mostrando el panel de puntuación y obteniendo los 10 mejores resultados de la base de datos.
     * Los resultados se agregan al modelo de datos de la tabla de puntuaciones.
     */
    private void actualizarPuntuaciones() {
        panelMenu.setVisible(false);
        panelPuntuacion.setVisible(true);
        ResultSet top10 = controlBBDD.obtenerTop10();
        try {
            while (top10.next()) {
                String nombre = top10.getString("Nombre");
                int puntuacion = top10.getInt("Puntuacion");
                model.addRow(new Object[]{nombre, puntuacion});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

