package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase ControlBBDD funciona como controlador de la BBDD.
 */
public class ControlBBDD {
    private Connection conexion;
    private String url = "jdbc:mysql://localhost:3306/tetrisbd";
    private String usuario = "root";
    private String passwd = "root";
    private PreparedStatement top10;
    private PreparedStatement guardarPuntuacion;
    private String queryconsultarpuntuacion = "SELECT nombre, puntuacion FROM tabla_puntuaciones ORDER BY puntuacion DESC LIMIT 10";
    private String guardarpuntos = "INSERT INTO tabla_puntuaciones (nombre, puntuacion) VALUES (?, ?)";
    private String nombre;
    private int puntuacion;

    /**
     * Crea una instancia de la clase ControlBBDD y establece la conexión con la base de datos.
     * Imprime un mensaje de éxito si la conexión es exitosa, o muestra un mensaje de error si hay algún problema.
     */
    public ControlBBDD() {
        try {
            conexion = DriverManager.getConnection(url, usuario, passwd);
            System.out.println("Conexión correcta");
            cargarPreparedStatement();
        } catch (SQLException e) {
            System.out.println("Error en la conexión con MySQL");
            System.out.println("Revisa que todo esté bien escrito y funcional");
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Carga los PreparedStatements utilizados para consultar el top 10 de puntuaciones y guardar una puntuación en la base de datos.
     * Lanza una excepción SQLException si hay algún error al cargar los PreparedStatements.
     */
    private void cargarPreparedStatement() throws SQLException {
        top10 = conexion.prepareStatement(queryconsultarpuntuacion);
        guardarPuntuacion = conexion.prepareStatement(guardarpuntos);
    }

    /**
     * Obtiene el top 10 de puntuaciones desde la base de datos.
     * Devuelve un objeto ResultSet con los resultados obtenidos.
     * Imprime un mensaje de error si ocurre algún problema al obtener los datos.
     *
     * @return Un objeto ResultSet con el top 10 de puntuaciones, o null si ocurre un error.
     */
    public ResultSet obtenerTop10() {

        try {
            ResultSet resultSet = top10.executeQuery();
            while (resultSet.next()) {
                nombre = resultSet.getString("nombre");
                puntuacion = resultSet.getInt("puntuacion");
                return resultSet;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el top 10 de puntuaciones");
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * Guarda una puntuación en la base de datos.
     * Recibe el nombre y la puntuación a guardar como parámetros.
     * Imprime un mensaje de éxito si la puntuación se guarda correctamente, o muestra un mensaje de error si hay algún problema.
     *
     * @param nombre     El nombre asociado a la puntuación.
     * @param puntuacion La puntuación a guardar.
     */
    public void guardarPuntuacion(String nombre, int puntuacion) {
        try {
            guardarPuntuacion.setString(1, nombre);
            guardarPuntuacion.setInt(2, puntuacion);
            guardarPuntuacion.executeUpdate();
            System.out.println("Puntuación guardada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al guardar la puntuación");
            System.out.println(e.getLocalizedMessage());
        }
    }
}