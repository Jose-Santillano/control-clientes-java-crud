<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- BOOTSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

        <!-- FONT AWESOME -->
        <script src="https://kit.fontawesome.com/db4c6cdbc4.js" crossorigin="anonymous"></script>

        <!-- LOCAL CSS -->
        <link rel="stylesheet" type="text/css" href="src/css/styles.css"/>
        
        <title>Control de Clientes</title>
    </head>
    <body class="bg-secondary">

        <!--  Cabecero -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

        <!-- Botones de navegacion -->
        <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacion.jsp" />

        <!-- Listado de clientes -->
        <jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp" />

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />

        <!-- JS BOOTSTRAP -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
