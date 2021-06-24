<%--
    Document   : estudios
    Created on : 18-abr-2021, 23:06:32
    Author     : juanm
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgo.dto.EstudioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estudios Estad&iacute;sticos</title>
        <%
            List<EstudioDTO> estudios = (List) request.getAttribute("listaEstudios");
            int pagina = (Integer) request.getAttribute("pagina");
            List<Integer> resultados = (List) request.getAttribute("resultadoEstudios");
        %>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Botones redondos -->
        <style>
            .btn-circle {
                width: 45px;
                height: 45px;
                line-height: 45px;
                text-align: center;
                padding: 0;
                border-radius: 50%;
            }

            .btn-circle i {
                position: relative;
                top: -1px;
            }

            .btn-circle-sm {
                width: 35px;
                height: 35px;
                line-height: 35px;
                font-size: 1.5rem;
            }
        </style>
        <!-- END Botones redondos -->

        <!-- Sección con la tabla de los estudios -->
        <div class="container">
            <div class="d-flex">
                <div class="me-auto">
                    <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
                </div>
                <div class="mt-auto align-middle">
                    <a class="btn btn-outline-dark btn-circle btn-circle-sm m-1" href="crearEstudio.jsp" role="button">
                        <i class="bi bi-plus"></i>
                    </a>
                </div>
            </div>
        </div>
        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center align-middle" id="tabla">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Resultado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            int i;
                            for (i = (pagina - 1) * 10; i < estudios.size() && i < (pagina * 10); i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
                            <td><%= estudios.get(i).getTitulo()%></td>
                            <td><%= resultados.get(i)%></td>
                            <td>
                                <a class="btn btn-outline-info" href="ServletEstudioVer?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <i class="bi bi-eye"></i>
                                </a>
                                <a class="btn btn-outline-success" href="ServletEstudioEditar?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <a class="btn btn-outline-danger" href="ServletEstudioEliminar?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <i class="bi bi-trash"></i>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                            for (i = i; i < 10 * pagina; i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
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
            <nav aria-label="Paginación de estudios">
                <ul class="pagination justify-content-center">
                    <%
                        int totalPaginas = (Integer) (estudios.size() / 10) + 1;
                    %>
                    <li class="page-item <%= (pagina > 1) ? "" : "disabled"%>">

                        <a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <%
                        if (pagina == totalPaginas && totalPaginas > 2) {
                    %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 2%>"><%= pagina - 2%></a></li>
                        <%
                            }
                        %>
                        <%
                            if (pagina > 1) {
                        %>

                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>"><%= pagina - 1%></a></li>
                        <%
                            }
                        %>


                    <li class="page-item active"><a class="page-link active" href="ServletEstudioCargar?paginaActual=<%= pagina%>"><%= pagina%></a></li>
                        <%
                            if (pagina < totalPaginas) {
                        %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 1%>"><%= pagina + 1%></a></li>
                        <%
                            if (pagina == 1 && totalPaginas > 2) {
                        %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 2%>"><%= pagina + 2%></a></li>
                        <%
                                }
                            }
                        %>
                    <li class="page-item <%= (pagina < totalPaginas) ? "" : "disabled"%>">
                        <a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 1%>">
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
