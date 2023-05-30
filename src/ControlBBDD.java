import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlBBDD {
    private Connection conexion;
    String url = "jdbc:mysql://localhost:3306/tetrisbd";
    String usuario = "root";
    String passwd = "root";

    PreparedStatement top10;

    PreparedStatement guardarPuntuacion;
    String queryconsultarpuntuacion = "SELECT nombre, puntuacion FROM Puntuacion ORDER BY Puntuacion DESC LIMIT 10";

    String guardarpuntos = "INSERT INTO tabla_puntuaciones (nombre, puntuacion) VALUES (?, ?)";


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
    private void cargarPreparedStatement() throws SQLException {
        top10 = conexion.prepareStatement(queryconsultarpuntuacion);
        guardarPuntuacion = conexion.prepareStatement(guardarpuntos);
    }

    public void obtenerTop10() {
        try {
            ResultSet resultSet = top10.executeQuery();
            System.out.println("Top 10 de puntuaciones:");
            System.out.println("Nombre\tPuntuación");
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int puntuacion = resultSet.getInt("puntuacion");
                System.out.println(nombre + "\t" + puntuacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el top 10 de puntuaciones");
            System.out.println(e.getLocalizedMessage());
        }
    }

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