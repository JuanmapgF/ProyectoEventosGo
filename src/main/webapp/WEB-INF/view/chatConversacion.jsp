<%-- 
    Document   : chatConversacion
    Created on : 16-may-2021, 12:51:22
    Author     : juanm
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.entity.Mensaje" %>
<%@ page import="es.taw.eventosgospring.entity.Conversacion" %>
<%@ page import="es.taw.eventosgospring.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            List<Mensaje> listaMensajes = (List) request.getAttribute("mensajes");
            Conversacion c = (Conversacion) request.getAttribute("conversacion");
            Usuario u = (Usuario) request.getAttribute("user");
            String destino, origen;
            if (u.getId() == c.getUsuarioByIdUsuario().getId()) {
                origen = c.getUsuarioByIdUsuario().getNombre();
                destino = c.getUsuarioByIdTeleoperador().getNombre();
            } else {
                origen = c.getUsuarioByIdTeleoperador().getNombre();
                destino = c.getUsuarioByIdUsuario().getNombre();
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= c.getAsunto()%></title>        
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Seccion del chat -->
        <section class="container">
            <header class="mb-5">
                <h1 class="display-1 text-center mb-4"><%= c.getAsunto()%></h1>
                <div class="d-flex">
                    <h3 class="me-auto display-5"><%= destino%></h2>
                        <h3 class="ms-auto display-5"><%= origen%></h2>
                            </div>
                            <hr class="my-3">
                            </header>
                            <article class="container shadow p-3 mb-2 bg-body rounded overflow-auto" style="max-height: 500px; max-width: 100%">
                                    <%                    for (Mensaje m : listaMensajes) {

                                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                            if (u.getId() == m.getUsuarioByIdUsuario().getId()) {
                                    %>
                                    <!-- Mensaje de Suyo -->
                                    <div class="row justify-content-end">
                                        <div class="col-auto">
                                            <div class="alert-success text-end px-5 py-2 ms-5 mb-4">
                                                <p class="text-break"><%= m.getTexto()%></p>

                                                <span class=""><%= sdf.format(m.getHora())%></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Mensaje -->
                                    <%
                                    } else {
                                    %>
                                    <!-- Mensaje de Usuario -->
                                    <div class="row ">
                                        <div class="col-auto justify-content-start">
                                            <div class="alert-dark text-start mb-4 px-5 py-2 me-5">
                                                <p class="text-break"><%= m.getTexto()%></p>

                                                <span class=""><%= sdf.format(m.getHora())%></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END Mensaje -->
                                    <%                        }
                                        }
                                    %>
                            </article>
                            <%
                                if (u.getId() == c.getUsuarioByIdTeleoperador().getId() || u.getId() == c.getUsuarioByIdUsuario().getId()) {
                            %>
                            <div class="container">
                                <form action="ServletMensajeEnviar">
                                    <input type="text" hidden="" value="<%= c.getId()%>" name="idConversacion"/>
                                    <input type="text" hidden="" value="<%= u.getId()%>" name="idUsuario"/>
                                    <input type="text" class="form-control" placeholder="Enviar un mensaje" name="mensaje"/>
                                    <button class="btn btn-primary">
                                        <i class="bi bi-arrow-right-square-fill"></i>
                                    </button>
                                </form>
                            </div>
                            <%
                                }
                            %>

                            </section>
                            </div>

                            </body>
                            </html>
