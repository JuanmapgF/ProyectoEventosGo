<%-- 
    Document   : misConversaciones
    Created on : 13-may-2021, 18:57:02
    Author     : juanm
--%>

<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatterBuilder"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.Conversacion" %>
<%@ page import="es.taw.eventosgospring.entity.Mensaje" %>
<%@ page import="es.taw.eventosgospring.entity.Usuario" %>
<%@ page import="es.taw.eventosgospring.dto.ConversacionDTO" %>
<%@ page import="es.taw.eventosgospring.dto.MensajeDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Conversaciones</title>
        <%
            List<ConversacionDTO> lista = (List) request.getAttribute("listaConversaciones");
            String error = (request.getAttribute("error") != null) ? request.getAttribute("error").toString() : "";
            UsuarioDTO usuarioDTO = (UsuarioDTO) request.getAttribute("user");
            List<UsuarioDTO> usuarios = (List) request.getAttribute("listaUsuarios");
        %>

    </head>
    <body>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <%                if (!error.isEmpty()) {
            %>
            <div class="alert alert-warning d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:">
                <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
                </svg>
                <div>
                    No hay teleoperadores disponibles. Disculpe las molestias.
                </div>
            </div>
            <%
                }
            %>
            <header class="container">
                <h1 class="display-1">Mis Conversaciones</h1>
                <hr class="my-3">
                <%                    if (rol != 2) {
                %>
                <div class="container">
                    <div class="alert alert-info d-flex align-items-center" role="alert">
                        <i class="bi bi-info-circle-fill" style="font-size: 1.7em"></i>
                        <div class="ms-3">
                            <div>
                                Aqu&iacute; puede solicitar una conversaci&oacute;n con un teleoperador.
                            </div>
                        </div>
                    </div>
                    <form action="ServletAsignarTeleoperador" class="row g-3 justify-content-center">
                        <div class="col-auto">
                            <label class="form-label fs-4 my-1">Asunto</label>
                        </div>
                        <div class="col-auto">
                            <input type="text" class="form-control" name="asunto">
                        </div>
                        <div class="col-auto">
                            <input type="submit" class="btn btn-primary mb-3" value="Solicitar">
                        </div>
                    </form>
                </div>
                <%
                } else {
                %>
                <div class="container row g-3 justify-content-center">
                    <a class="btn btn-outline-dark btn-circle btn-circle-sm ms-3 mb-4" href="ServletConversacionListarTodas" role="button">
                        <i class="bi bi-plus"></i>
                        Consultar chats
                    </a>
                </div>
                <%
                    }
                %>
            </header>

            <article class="container">
                <div class="list-group">
                    <%                        for (int i = 0; i < lista.size(); i++) {
                            int cntSinLeer = 0;
                                for (MensajeDTO m1 : lista.get(i).getMensajesById()) {
                                    if (m1.getVisto() == 0 && !(m1.getUsuarioByIdUsuario() == usuarioDTO.getId())) {
                                        cntSinLeer++;
                                    }
                                }
                    %>
                    <a href="/conversacion/<%= lista.get(i).getId()%>" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h3 class="mb-1"><%= lista.get(i).getAsunto()%></h3>
                            <%
                                if (cntSinLeer > 0) {
                            %>
                            <span class="badge bg-primary fs-5"> <%= cntSinLeer%></span>
                            <%
                                }
                            %>

                        </div>
                        <p><%= (lista.get(i).getMensajesById().size() > 0) ? lista.get(i).getMensajesById().get(lista.get(i).getMensajesById().size() - 1).getTexto() : ""%></p>
                        <small><%= (rol == 2) ? usuarioDTO.getCorreo() : usuarios.get(i).getNombre() %></small>
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
