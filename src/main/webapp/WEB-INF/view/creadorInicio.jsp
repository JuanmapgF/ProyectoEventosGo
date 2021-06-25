<%-- 
    Document   : CreadorInicio
    Created on : 10-may-2021, 17:41:50
    Author     : Kiko BM
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.taw.eventosgospring.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page import="es.taw.eventosgospring.dto.EventoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina principal del creador de eventos</title>

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!-- Iconos de Boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

        
        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <%
        List<EventoDTO> eventos = (List) request.getAttribute("eventos");
    %>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->



        <!-- Sección con la tabla de los eventos -->
        <section class="container rounded shadow-sm w3-padding">
            <header class="container">

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <a class="btn btn-primary bi bi-hammer" href="crearEvento.jsp" role="button"> Crear evento</a>
                </div>

                <h1 class="display-1">Mis eventos</h1>

                <form class="d-flex" action="/evento/listarEventosCreados">
                    <input class="form-control me-2" type="search" name="filtro" placeholder="Buscar" aria-label="Search">
                    <button class="btn btn-sm btn-outline-secondary" type="submit">Buscar</button>
                </form>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center align-middle" title="Lista de eventos">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            
                            int i;
                            for (i = 0; i < eventos.size(); i++) {
                        %>

                        <tr>

                            <th scope="row"><%= (i + 1)%></th>
                            <td><%= eventos.get(i).getTitulo()%></td>
                            <td><%= new SimpleDateFormat("dd/MM/yyyy").format(eventos.get(i).getFechaEvento())%></td>
                            <td>
                                <a class="btn btn-outline-info" href="/evento/verEvento/<%= eventos.get(i).getId()%>" role="button">
                                    <i class="bi bi-eye"></i>
                                </a>
                                <a class="btn btn-outline-success" href="ServletEventoVer?eventoid=<%= eventos.get(i).getId()%>&accion=editar" role="button">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <a class="btn btn-outline-danger" href="/evento/eliminarEvento/<%= eventos.get(i).getId()%>" role="button">
                                    <i class="bi bi-trash"></i>
                                </a>
                            </td>
                        </tr>

                        <%
                            }
                            for (int j = i +1; j <= 10; j++) {
                        %>
                        <tr>
                            <th scope="row"><%= j %></th>
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
            <!-- Navbar de paginación de los eventos-->
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
            <!-- END Navbar de paginación de los eventos-->
        </section>
        <!-- END Sección con la tabla de los eventos -->


    </body>
</html>
