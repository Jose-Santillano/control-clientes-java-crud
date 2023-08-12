package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    //Variables para la conexion.
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    //Para optimizar las conexiones si no nos las acabariamos muy rapido.
    private static BasicDataSource dataSource;

    //Metodo para asignar el pool de conexiones a la base de datos.
    public static DataSource getDataSource() {

        //** Se optimizo para mejorar las conexiones a la BD.
        if (dataSource == null) {
            //Ingresamos toda la informacion a la conexion.
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            //Posibles conexiones a la base de datos.
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    //Metodo para generar la conexion ejecutando el metodo anterior.
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    /*
        METODOS GENERICOS
        Metodos para cerrar las diferentes tipos de conexiones de la base de datos.
     */
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
