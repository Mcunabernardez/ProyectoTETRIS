# ProyectoTETRIS

[JavaDoc](https://mcunabernardez.github.io/ProyectoTETRIS/JavaDoc/index.html)
-------------------------
DIAGRAMA DE CLASES
-------------------------
```mermaid
classDiagram
    VentanaPrincipal "0..1" -- "1" Juego : +juego / +ventanaPrincipal
    Juego "1" -- "1" Pieza : +piezaActual
    
    VentanaPrincipal <-- ControlBBDD : +conexion

    Cuadrado <-- "*" Juego : +cuadradosSuelo

    Pieza <|-- PiezaCuadrada
    Pieza <|-- PiezaBarra
    Pieza <|-- PiezaT
    Pieza <|-- PiezaL
    Pieza <|-- PiezaLinversa
    Pieza <|-- PiezaZ
    Pieza <|-- PiezaZinversa
    
    Pieza "*" *-- Cuadrado : +cuadrados

    
    class VentanaPrincipal{
    -timer : Timer
    -panelJuego : JPanel
    -panelMenu : JPanel
    -panelPausa : Jpanel
    -panelPuntuacion : JPanel
    
    -iniciarPartida()
    -pintarCuadrado(lblCuadrado: JLabel)
    +pintarPieza(pieza : Pieza)
    +borrarCuadrado(lblCuadrado : JLabel)
    -iniciarInterfaz()
    -tiempoPartida()
    +mostrarFinJuego()
    -reiniciarJuego()
    }
    <<Abstract>> Pieza
    class Pieza{
        -cuadrados : ArrayList 
        #posicion : int
        
        +rotar()
        +moverIzquierda()
        +moverDerecha()
        +moverAbajo()
    }
    class Cuadrado{
        -x : int
        -y : int
        - lblCuadrado : JLabel
        
        +setLocation(int x, int y)
    }
    class Juego{
        -LADOCUADRADO : final int
        -MAX_X : final int
        -MAX_y : final int
        
        -instanciarPiezaNueva()
        -posicionValida(int x, int y) : boolean
        +moverPiezaAbajo()
        +moverPiezaIzquierda()
        +moverPiezaDerecha()
        +rotarPieza()
        +crearPieza()
        -anadirAlSuelo()
        -choquePiezaSuelo()
        -borrarLineasCompletas()
        -actualizarBloques()
        -borrarLinea()
        -anadePiezaBorraLineasCompletasGeneraPiezaNueva()
        -comprobarPerder() : boolean
    }
        
      class ControlBBDD{
        -conexion : Connection
        -top10 : PreparedStatement
        -guardarPuntos : PreparedStatment
        
        -cargarPreparedStatment()
        +obtenerTop10()
        +guardarPuntuacion(String nombre, int puntuacion)
    }
```

---------------
DIAGRAMA DE SECUENCIA
---------------
Secuencia de intentar mover una piezaActual a la izquierda
```mermaid
sequenceDiagram
User-->>VentanaPrincipal: presiona A o flecha izquierda
VentanaPrincipal-->>Juego: el Listener avisa a la lógica que quiere moverse a la izquierda 
Juego-->>Pieza: Se puede mover piezaActual a la izquierda?
Pieza-->>Juego: Mueve piezaActual a la izquierda
Juego-->>VentanaPrincipal: Actualiza posicion de la Pieza
```
Secuencia de iniciarPartida

```mermaid
sequenceDiagram
User-->>VentanaPrincipal: Pulsa en el boton Nueva Partida
VentanaPrincipal-->>Juego : Instancia un juego
Juego-->>Pieza : Solicita una piezaActual
loop
Pieza-->Cuadrado : Recoge cuadrados para formar una pieza
end
VentanaPrincipal-->VentanaPrincipal: activa panelJuego e inicia Timer
Pieza-->>Juego : Genera la pieza actual
Juego-->>VentanaPrincipal: Le envia la pieza y se pinta para poder visualizarla
VentanaPrincipal-->>User: Visualiza el inicio de la partida de Tetris

```
