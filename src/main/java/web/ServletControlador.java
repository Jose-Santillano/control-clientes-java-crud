package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    //Metodo para procesar las peticiones get.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar": {
                    this.editarCliente(request, response);
                    break;
                }
                case "eliminar": {
                    this.eliminarCliente(request, response);
                    break;
                }
                default: {
                    //Redirigimos a vista principal. (refactorizado)
                    this.accionDefault(request, response);
                }
            }
        } else {
            //Redirigimos a vista principal. (refactorizado)
            this.accionDefault(request, response);
        }
    }

    //Metodo para controlar las peticiones post.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar": {
                    this.insertarCliente(request, response);
                    break;
                }
                case "modificar": {
                    this.modificarCliente(request, response);
                    break;
                }
                default: {
                    //Redirigimos a vista principal. (refactorizado)
                    this.accionDefault(request, response);
                }
            }
        } else {
            //Redirigimos a vista principal. (refactorizado)
            this.accionDefault(request, response);
        }
    }
    
    //Metodo por default de la aplicacion. (refactorizamos lo que pasaba en doGet)
    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtenemos la lista de todos los clientes.
        List<Cliente> clientes = new ClienteDaoJDBC().listar();

        //USAMOS ALCANCE SESSION PARA ALMACENAR LA INFORMACION.
        HttpSession sesion = request.getSession();

        //Asignamos un atributo al servlet de clientes.
        sesion.setAttribute("clientes", clientes);

        //Asignamos un atributo para saber cantidad de clientes.
        sesion.setAttribute("totalClientes", clientes.size());

        //Asignamos un atributo para saber el total de los saldos.
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));

        //Redirigimos a nueva ruta y mandamos la informacion obtenida. (la url no cambia)
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        //Que cambie el URL para que no se duplique la informacion.
        response.sendRedirect("clientes.jsp");
    }

    //Metodo para calcular el saldo total de todos los clientes.
    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;

        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }

        return saldoTotal;
    }

    //Metodo para editar clientes. (solo muestra los elementos a editar)
    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos el idCliente.
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        //Creamos objeto cliente y buscamos al cliente con su id.
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        
        //Asignamos atributo del cliente buscado.
        request.setAttribute("cliente", cliente);
        
        //Path del jsp editar.
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    //Metodo para insertar un cliente.
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos valores del formulario agregarCliente.
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        //Valiamos el double en caso de posibles errores.
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos objeto cliente.
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

        //Insertamos en la base de datos.
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);

        //Redirigimos a la accion por default. (inicio)
        this.accionDefault(request, response);
    }
    
    //Metodo para modificar un cliente.
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos valores del formulario editarCliente.
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        //Valiamos el double en caso de posibles errores.
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos objeto cliente.
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

        //Modificar en la base de datos.
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);

        //Redirigimos a la accion por default. (inicio)
        this.accionDefault(request, response);
    }
    
    //Metodo para eliminar un cliente.
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos valores del formulario eliminarCliente.
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        //Creamos objeto cliente.
        Cliente cliente = new Cliente(idCliente);
        
        System.out.println("********* cliente = " + cliente);

        //Modificar en la base de datos.
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos a la accion por default. (inicio)
        this.accionDefault(request, response);
    }
}
