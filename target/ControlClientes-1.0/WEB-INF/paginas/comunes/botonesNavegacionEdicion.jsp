<section id="actions" class="py-4 mb-4 bg-dark">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <a href="index.jsp" class="btn btn-light btn-block">
                        <i class="fas fa-arrow-left"></i>
                        Regresar al inicio
                    </a>
                </div>
                <div class="col-md-2">
                    <button type="subgmit" class="btn btn-success btn-block">
                        <i class="fas fa-check"></i>
                        Guardar cliente
                    </button>
                </div>
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}" class="btn btn-danger btn-block">
                        <i class="fas fa-trash"></i>
                        Eiminar cliente
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
