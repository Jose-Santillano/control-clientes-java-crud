package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDaoJDBC {
    //Simplificamos y no usamos interfaces.

    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo FROM cliente";

    // Busqueda por id.
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo FROM cliente"
            + " WHERE id_cliente=?";

    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo)"
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE cliente"
            + " SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";

    //Obtener lista de todos los clientes.
    public List<Cliente> listar() {
        //Variables para:

        //Obtener conexion.
        Connection conn = null;
        //Preparar la sentencia sql.
        PreparedStatement stmt = null;
        //Recuperar informacion.
        ResultSet rs = null;
        //Almacenar cliente.
        Cliente cliente = null;
        //Almacenar listado de clientes.
        List<Cliente> clientes = new ArrayList<>();

        try {
            //Creamos conexion.
            conn = Conexion.getConnection();

            //Preparamos la sentencia a mandar a la base de datos.
            stmt = conn.prepareStatement(SQL_SELECT);

            //Ejecutamos el query.
            rs = stmt.executeQuery();

            //Obtenemos la respuesta y las guardamos en las variables.
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                //Creamos objeto y mandamos toda la informacion.
                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

                //Añadimos a la lista el objeto creado en este ciclo.
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            //En caso de error mandamos el mensaje a la consola.
            ex.printStackTrace(System.out);
        } finally {
            //Cerramos toda conexion con la base de datos.
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //Retornamos los clientes obtenidos.
        return clientes;
    }

    //Buscar cliente en especifico mediante id.
    public Cliente encontrar(Cliente cliente) {
        //Variables para:

        //Obtener conexion.
        Connection conn = null;
        //Preparar la sentencia sql.
        PreparedStatement stmt = null;
        //Recuperar informacion.
        ResultSet rs = null;

        try {
            //Creamos conexion.
            conn = Conexion.getConnection();

            //Preparamos la sentencia a mandar a la base de datos.
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            

            //Asignamos el parametro.
            stmt.setInt(1, cliente.getIdCliente());
            
            //Ejecutamos query.
            rs = stmt.executeQuery();
            
            //Obtenemos el primer registro de coincidencia.
            rs.next();

            //Obtenemos la informacion del cliente buscado.
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            //Asignamos esa informacion al objeto.
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);
        } catch (SQLException ex) {
            //En caso de error mandamos el mensaje a la consola.
            ex.printStackTrace(System.out);
        } finally {
            //Cerramos toda conexion con la base de datos.
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //Retornamos el objeto cliente buscado.
        return cliente;
    }

    //Insertar un cliente.
    public int insertar(Cliente cliente) {
        //Variables para:

        //Obtener conexion.
        Connection conn = null;
        //Preparar la sentencia sql.
        PreparedStatement stmt = null;
        //Obtener filas modificadas.
        int rows = 0;

        try {
            //Creamos conexion.
            conn = Conexion.getConnection();

            //Preparamos la sentencia a mandar a la base de datos.
            stmt = conn.prepareStatement(SQL_INSERT);

            //Asignamos cada parametro en orden.
            //Nombre, Apellido, Email, Telefon, Saldo.
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            //Ejecutamos y retornara un valor entero de las filas modificadas.
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            //En caso de error mandamos el mensaje a la consola.
            ex.printStackTrace(System.out);
        } finally {
            //Cerramos toda conexion con la base de datos.
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //Retornamos las filas modificadas.
        return rows;
    }

    //Actualizar un cliente.
    public int actualizar(Cliente cliente) {
        //Variables para:

        //Obtener conexion.
        Connection conn = null;
        //Preparar la sentencia sql.
        PreparedStatement stmt = null;
        //Obtener filas modificadas.
        int rows = 0;

        try {
            //Creamos conexion.
            conn = Conexion.getConnection();

            //Preparamos la sentencia a mandar a la base de datos.
            stmt = conn.prepareStatement(SQL_UPDATE);

            //Asignamos cada parametro en orden.
            //Nombre, Apellido, Email, Telefon, Saldo, Id_Cliente (para saber que registro modificar).
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());

            //Ejecutamos y retornara un valor entero de las filas modificadas.
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            //En caso de error mandamos el mensaje a la consola.
            ex.printStackTrace(System.out);
        } finally {
            //Cerramos toda conexion con la base de datos.
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //Retornamos las filas modificadas.
        return rows;
    }
    
    //Eliminar un cliente.
    public int eliminar(Cliente cliente){
        //Variables para:

        //Obtener conexion.
        Connection conn = null;
        //Preparar la sentencia sql.
        PreparedStatement stmt = null;
        //Obtener filas modificadas.
        int rows = 0;

        try {
            //Creamos conexion.
            conn = Conexion.getConnection();

            //Preparamos la sentencia a mandar a la base de datos.
            stmt = conn.prepareStatement(SQL_DELETE);

            //Asignamos cada parametro id_cliente para eliminarlo.
            stmt.setInt(1, cliente.getIdCliente());

            //Ejecutamos y retornara un valor entero de las filas modificadas.
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            //En caso de error mandamos el mensaje a la consola.
            ex.printStackTrace(System.out);
        } finally {
            //Cerramos toda conexion con la base de datos.
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //Retornamos las filas modificadas.
        return rows;
    }
    
}
