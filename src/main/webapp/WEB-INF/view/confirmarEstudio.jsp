<%-- 
    Document   : confirmarEstudio
    Created on : 07-may-2021, 21:58:53
    Author     : juanm
--%>

<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.UsuarioEvento" %>
<%@ page import="es.taw.eventosgospring.dto.UsuarioEventoDTO" %>
<%@ page import="es.taw.eventosgospring.dto.EstudioDTO" %>
<%@ page import="java.util.Scanner" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Estudio</title>
    </head>
    <body>
        <%
            List<UsuarioEventoDTO> lista = (List) request.getAttribute("lista");
            EstudioDTO e = (EstudioDTO) request.getAttribute("estudio");

            int edadMinima;
            int edadMaxima;
            String ciudad;
            int anio;
            int masculino;
            int femenino;
            int otro;


            // Extraer valores de los filtros
            try (Scanner sc = new Scanner(e.getResultado())) {
                sc.useDelimiter(";");
                edadMinima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
                edadMaxima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
                String auxsc = sc.next();
                ciudad = (sc.hasNext() && !auxsc.isEmpty()) ? auxsc : null;
                anio = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
                masculino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
                femenino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
                otro = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            }

        %>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Indicador de progreso -->
        <div class="container py-4">
            <div class="position-relative m-4">
                <div class="progress" style="height: 3px;">
                    <div class="progress-bar" role="progressbar" style="width: 100%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <button type="button" class="position-absolute top-0 start-0 translate-middle btn btn-lg btn-primary rounded-pill" style="width: 3rem; height:3rem;">1</button>
                <button type="button" class="position-absolute top-0 start-100 translate-middle btn btn-lg btn-primary rounded-pill" style="width: 3rem; height:3rem;">2</button>
            </div>
        </div>
        <!-- END Indicador de progreso -->

        <!-- Secci??n con la tabla de usuarios que cumplen el filtro para estudio -->
        <section class="container">
            <header class="container">
                <h1 class="display-1"><%= e.getTitulo()%></h1>
                <p class="fs-4">Total de usuarios: <%= lista.size()%></p>
                
                <%
                    if (e.getId() != null) {
                        %>
                        
                        <a href="/estudios/guardar/<%= e.getTitulo() %>/<%= edadMinima %>/<%= edadMaxima %>/<%= ciudad %>/<%= anio %>/<%= masculino %>/<%= femenino %>/<%= otro %>/<%= e.getId() %>" class="btn btn-primary" role="button">Guardar</a>
                        <a href="/estudios/guardar/<%= e.getTitulo() %>/<%= edadMinima %>/<%= edadMaxima %>/<%= ciudad %>/<%= anio %>/<%= masculino %>/<%= femenino %>/<%= otro %>/<%= -1 %>" class="btn btn-primary" role="button">Guardar como nuevo</a>
                        
                        
                        <%
                    } else {
                        %>
                        
                        <a href="/estudios/guardar/<%= e.getTitulo() %>/<%= edadMinima %>/<%= edadMaxima %>/<%= ciudad %>/<%= anio %>/<%= masculino %>/<%= femenino %>/<%= otro %>/<%= -1 %>" class="btn btn-primary" role="button">Crear Estudio</a>

                        <%

                    }
                %>
                
                <a href="/estudios/" class="btn btn-secondary btn-sm" role="button">Cancelar</a>
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
                            for (UsuarioEventoDTO u : lista) {
                        %>
                        <tr>
                            <th scope="row"><%= i %></th>
                            <td><%= u.getNombre() %></td>
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
        <!-- END Secci??n con la tabla de usuarios que cumplen el filtro para estudio -->
    </body>
</html>
