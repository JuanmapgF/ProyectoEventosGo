<%-- 
    Document   : todasConversacion
    Created on : 16-may-2021, 11:24:25
    Author     : juanm
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.Conversacion" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial Conversaciones</title>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->


        <%            List<Conversacion> lista = (List) request.getAttribute("listaConversaciones");
            int id = Integer.parseInt(request.getAttribute("idUsuario").toString());
        %>

        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <header class="container">
                <h1 class="display-1">Historial de Conversaciones</h1>
                <hr class="my-3">
            </header>
            <article class="container">
                <div class="list-group">
                    <%                        for (Conversacion c : lista) {
                    %>
                    <a href="ServletMensajeCargar?idConversacion=<%= c.getId() %>" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h3 class="mb-1"><%= c.getAsunto()%></h3>
                        </div>
                        <p><%= (c.getMensajesById().size() > 0) ? c.getMensajesById().get(c.getMensajesById().size() - 1).getTexto() : ""%></p>
                        <div class="d-flex w-100 justify-content-between">
                            <small><%= (id == c.getUsuarioByIdTeleoperador().getId()) ? "Tú" : c.getUsuarioByIdTeleoperador().getNombre()%> </small>
                            <small><%= c.getUsuarioByIdUsuario().getCorreo()%> </small>
                        </div>
                    </a>
                    <%
                        }
                    %>
                </div>
            </article>
        </section>

        <!-- END Sección conversacion -->
    </body>
</html>
