<%-- 
    Document   : comprarEntradas
    Created on : 15-may-2021, 14:11:44
    Author     : pacoa
--%>

<%@page import="es.taw.eventosgospring.dto.UsuarioEventoDTO"%>
<%@page import="es.taw.eventosgospring.dto.UsuarioDTO"%>
<%@page import="es.taw.eventosgospring.dto.EventoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprar Entradas</title>
    </head>
    <%
        EventoDTO evento = (EventoDTO)request.getAttribute("evento");
        Integer entradasDisponibles = (Integer)request.getAttribute("entradasDisponibles");
        Integer entradasCompradasUsuario = (Integer)request.getAttribute("entradasCompradas");
        UsuarioEventoDTO usuarioEvento = (UsuarioEventoDTO)request.getSession().getAttribute("usuarioEvento");
        String error = (String) request.getAttribute("error");
        
    %>
  
    <body>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->
         
        <%
            if(error == null || error.isEmpty()){
        %>  
        <form action="/usuario/comprarEntradas">
            
            <input type="hidden" name="idEvento" value="<%= evento.getId() %>" />
            <input type="hidden" name="idUsuario" value="<%= usuarioEvento.getId() %>" />
            <input type="hidden" name="entradasCompradasUsuario" value="<%= entradasCompradasUsuario %>" />
            <input type="hidden" name="entradasDisponibles" value="<%= entradasDisponibles %>" />
            
        <h1>Compra de entradas para <%= evento.getTitulo() %> </h1>
        <p>Entradas disponibles: <%= entradasDisponibles %></p>
        <p>Numero maximo de entradas que puede comprar: <%= evento.getMaximoEntradasUsuario() %></p>
        <p>Entradas que ya ha comprado: <%= entradasCompradasUsuario %></p>
            <div class="row">
                <div class="col">
                    Nº de entradas a comprar:
                </div>
                <div class="col-12">
                    <input type="number" name="entradas" />
                </div> 
            </div></br>
        <input type="submit" value="Comprar Entradas" />
        </form>
        <%
            }else{
        %>
        <h1> <%= error %></h1>
        <%
            }
        %>
    </body>
    
</html>
