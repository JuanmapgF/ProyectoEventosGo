<%-- 
    Document   : verEvento
    Created on : 12-may-2021, 20:09:38
    Author     : Kiko BM
--%>
 

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.taw.eventosgospring.dto.EventoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

        <title>JSP Page</title>
    </head>
    <%
        EventoDTO evento = (EventoDTO) request.getAttribute("evento");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        Integer numeroEntradas = (Integer)request.getAttribute("numeroEntradas");
        UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
        
        String volver = "ServletCreadorPrincipal";
        
        if(usuario.getRol() == 0){
            volver = "ServletAdminEventoCargar";
        }else if (usuario.getRol() == 1 ){
            volver = "/evento/listar";
        }

    %>
    
    <% 
        if(usuario == null){
    %>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->  
        <p> Inicie Sesion o Registrese si aun no lo esta</p></br>    
     <% 
        }else{
    %>


    <body>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->  

        <section class="container rounded shadow-sm w3-padding">
            <header class="container">
                <h1><%= evento.getTitulo()%></h1>
            </header>

            <div class="container">


                <div class="row">
                    <div class="col">
                        Descripci&oacute;n:
                    </div>
                    <div class="col-8">
                        <div class="container-md-start">
                            <%= evento.getDescripcion()%><br/>
                        </div>

                    </div> 
                </div>
                <div class="row"><br/></div>
                <div class="row">
                    <div class="col">
                        Fecha del evento:
                    </div>
                    <div class="col-8">
                        <%= formato.format(evento.getFechaEvento())%><br/>
                    </div> 
                </div>
                <div class="row"><br/></div>
                <div class="row">
                    <div class="col">
                        Fecha fin de reservas:
                    </div>
                    <div class="col-8">
                        <%= formato.format(evento.getFechaFinReservas())%><br/>
                    </div> 
                </div>
                <div class="row"><br/></div>
                <div class="row">
                    <div class="col">
                        Coste de la entrada:
                    </div>
                    <div class="col-8">
                        <%= evento.getCoste()%>  &euro;<br/>
                    </div> 
                </div>
                <div class="row"><br/></div>
                <div class="row">
                    <div class="col">
                        Aforo del evento:
                    </div>
                    <div class="col-8">
                        <%= evento.getAforo()%><br/>
                    </div> 
                </div>
                <div class="row"><br/></div>
                <div class="row" he>
                    <div class="col">
                        Nº m&aacute;ximo de entradas por usuario:
                    </div>
                    <div class="col-8">
                        <%= evento.getMaximoEntradasUsuario() %><br/>
                    </div> 
                </div>
                <div class="row"><br/></div>
                <%  // Falta condicion de que las entradas que hay == aforo
                     if((usuario.getRol() == 4)  && (evento.getAforo() > 0) && numeroEntradas != evento.getAforo()){
                  %>
                <div>
                    <a class="btn btn-link" href="ServletParaComprarEntradas?id=<%= evento.getId() %>" role="button"> Comprar entradas</a>
                </div> 
                <%
                      }else if(usuario.getRol() == 4){
                      
                 %>
                    <p> No hay entradas disponibles para este evento</p>
                <%
                      }
                      
                 %>

                <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                    <a class="btn btn-primary" href="<%= volver %>" role="button">Volver</a>
                </div>
            </div>
        </section>
        
                    
    </body>
    <% 
        }
    %>
</html>
