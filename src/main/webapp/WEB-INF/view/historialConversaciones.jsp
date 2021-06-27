<%-- 
    Document   : todasConversacion
    Created on : 16-may-2021, 11:24:25
    Author     : juanm
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.Conversacion" %>
<%@ page import="es.taw.eventosgospring.dto.ConversacionDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial Conversaciones</title>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->


        <%
            List<ConversacionDTO> lista = (List) request.getAttribute("listaConversaciones");
            int id = Integer.parseInt(request.getAttribute("idUsuario").toString());
            List<UsuarioDTO> usuarios = (List) request.getAttribute("usuarios");
        %>

        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <header class="container">
                <h1 class="display-1">Historial de Conversaciones</h1>
                <hr class="my-3">
            </header>
            <article class="container">
                <div class="list-group">
                    <%                        for (int i = 0; i < lista.size(); i++) {
                    %>
                    <a href="/conversacion/chat/<%= lista.get(i).getId() %>" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h3 class="mb-1"><%= lista.get(i).getAsunto()%></h3>
                        </div>
                        <p><%= (lista.get(i).getMensajesById().size() > 0) ? lista.get(i).getMensajesById().get(lista.get(i).getMensajesById().size() - 1).getTexto() : ""%></p>
                        <div class="d-flex w-100 justify-content-between">

                            <%
                                    if (id == lista.get(i).getUsuarioByIdTeleoperador()) {
                                        %>
                            <small><%="Tú"%></small>
                            <%
                                    } else {
                                        String txt = "";
                                        for (UsuarioDTO usu : usuarios) {
                                            if (lista.get(i).getUsuarioByIdTeleoperador() == usu.getId()) {
                                                txt = usu.getNombre();
                                            }
                                        }
                            %>
                            <small><%= txt %></small>
                            <%
                                    }
                            %>

                            <%
                                for (UsuarioDTO usu : usuarios) {
                                    if (lista.get(i).getUsuarioByIdUsuario() == usu.getId()) {
                                        %>
                            <small><%= usu.getCorreo() %> </small>
                                        <%
                                    }
                                }
                            %>
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
