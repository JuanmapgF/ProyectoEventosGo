<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.taw.eventosgospring.dto.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrador Eventos</title>

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!-- Iconos de Boostrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">


    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <%
        List<EventoDTO> listaEventos = (List) request.getAttribute("listaEventos");
    %>
</head>
<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<!-- Sección con la tabla de los estudios -->
<section class="container rounded shadow-sm w3-padding">
    <header class="container">

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <a class="btn btn-primary bi bi-hammer" href="/evento/crearEvento" role="button"> Crear evento</a>
        </div>

        <h1 class="display-1">Lista de eventos</h1>
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
            <button class="btn btn-sm btn-outline-secondary" type="submit">Buscar</button>
        </form>
    </header>

    <article>
        <table class="table table-responsive-md table-hover table-sm fs-6 text-center" title="Lista de eventos">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Titulo</th>
                <th scope="col">Fecha</th>
                <th scope="col">Fecha Fin Resrervas</th>
                <th scope="col">Aforo</th>
                <th scope="col">Coste</th>
                <th scope="col">Maximo entradas/usuario</th>
                <th scope="col">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%                            int i;
                for (i = 0; i < listaEventos.size(); i++) {
            %>

            <tr>

                <th scope="row"><%= (i + 1)%></th>
                <th> <%= listaEventos.get(i).getTitulo()%> </th>
                <td> <%= new SimpleDateFormat("dd/MM/yyyy").format(listaEventos.get(i).getFechaEvento())%> </a></td>
                <td> <%= new SimpleDateFormat("dd/MM/yyyy").format(listaEventos.get(i).getFechaFinReservas())%> </a></td>
                <td> <%= listaEventos.get(i).getAforo()%> </a></td>
                <td> <%= listaEventos.get(i).getCoste()%> </a></td>
                <td> <%= listaEventos.get(i).getMaximoEntradasUsuario()%> </a></td>
                <td>
                    <a class="btn btn-outline-info" href="/evento/verEvento/<%= listaEventos.get(i).getId()%>" role="button">
                        <i class="bi bi-eye"></i>
                    </a>
                    <a class="btn btn-outline-success" href="/evento/editarEvento/<%= listaEventos.get(i).getId()%>" role="button">
                        <i class="bi bi-pencil-square"></i>
                    </a>
                    <a class="btn btn-outline-danger" href="/evento/eliminarEvento/<%= listaEventos.get(i).getId()%>" role="button">
                        <i class="bi bi-trash"></i>
                    </a>
                </td>
            </tr>

            <%
                }
                for (int j = 10; j > i; j--) {
            %>
            <tr>
                <th scope="row"><%= (12 - j)%></th>
                <td>*</td>
                <td>*</td>
                <td>*</td>
                <td>*</td>
                <td>*</td>
                <td>*</td>
                <td>*</td>

            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </article>
    <!-- Navbar de paginación de los estudios-->
    <nav aria-label="Paginación de eventos">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
    <!-- END Navbar de paginación de los estudios-->
</section>
<!-- END Sección con la tabla de los estudios -->


</body>
</html>
