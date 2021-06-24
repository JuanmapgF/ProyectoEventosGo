<%-- 
    Document   : verEstudio
    Created on : 12-may-2021, 13:56:39
    Author     : juanm
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="eventosgowebapp.entity.Estudio"%>
<%@page import="eventosgowebapp.entity.UsuarioEvento"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgo.entity.UsuarioEvento" %>
<%@ page import="es.taw.eventosgo.entity.Estudio" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Estudio</title>
    </head>
    <body>
        <%
            List<UsuarioEvento> lista = (List) request.getAttribute("resultado");
            Estudio e = (Estudio) request.getAttribute("estudio");
        %>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Sección con la tabla de usuarios que cumplen el filtro para estudio -->
        <section class="container">
            <header class="container">
                <h1 class="display-1"><%= e.getTitulo()%></h1>
                <p class="fs-4">Total de usuarios: <%= lista.size()%></p>
                <a href="ServletEstudioCargar" class="btn btn-primary" role="button">Volver</a>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center" title="Lista de estudios">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Edad</th>
                            <th scope="col">Ciudad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int i = 1;
                            for (UsuarioEvento u : lista) {
                        %>
                        <tr>
                            <th scope="row"><%= i %></th>
                            <td><%= u.getUsuarioById().getNombre()%></td>
                            <%
                                SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
                                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                                int edad = Period.between(LocalDate.of(Integer.parseInt(sdf.format(u.getFechaNacimiento())), Integer.parseInt(sdf1.format(u.getFechaNacimiento())), Integer.parseInt(sdf2.format(u.getFechaNacimiento()))), LocalDate.now()).getYears();
                            %>
                            <td><%= edad%></td>
                            <td><%= u.getCiudad()%></td>
                        </tr>
                        <%
                            i++;
                            }
                        %>

                    </tbody>
                </table>
            </article>
        </section>        
        <!-- END Sección con la tabla de usuarios que cumplen el filtro para estudio -->

    </body>
</html>
