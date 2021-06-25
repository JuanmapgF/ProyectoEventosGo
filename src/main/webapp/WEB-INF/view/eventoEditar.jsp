<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.taw.eventosgospring.dto.EventoDTO" %>
<%@ page import="java.util.Date" %>
<%@ page import="es.taw.eventosgospring.entity.EventoEtiqueta" %>
<%@ page import="es.taw.eventosgospring.dto.EventoEtiquetaDTO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: Kiko BM
  Date: 25/06/2021
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nuevo Evento</title>

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" >

</head>
<%

    String strError = (String) request.getAttribute("error");
    if (strError == null) {
        strError = "";
    }

    UsuarioDTO creador = (UsuarioDTO) request.getSession().getAttribute("usuario");

    EventoDTO evento = (EventoDTO) request.getAttribute("evento");

    String titulo = evento.getTitulo();
    String descripccion = evento.getDescripcion();
    Date fechaEvento = evento.getFechaEvento();
    Date fechaFin = evento.getFechaFinReservas();
    Double coste = evento.getCoste();
    Integer aforo = evento.getAforo();
    Integer entradas = evento.getMaximoEntradasUsuario();

    String etiquetas = request.getParameter("etiquetas");

    String strTo = "";

    if(creador.getRol() == 0){  // si somos admin
        strTo = "/EventosCargarAdmin";
    }else{
        strTo = "/evento/listarEventosCreados";
    }

%>

<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<section class="container rounded shadow-sm w3-padding">
    <header class="container">
        <h1>Datos del evento</h1>
    </header>

    <form method='POST' action="ServletEventoGuardar">
        <div class="container">
            <div class="row">
                <div class="col-8">
                    <input type="text" name="id" value="<%= evento.getId()%>" hidden/>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    T&iacute;tulo:
                </div>
                <div class="col-8">
                    <input type="text" name="titulo" value="<%= titulo%>"/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Descripci&oacute;n:
                </div>
                <div class="col-8">
                    <textarea name="" rows="4" cols="50"><%= evento.getDescripcion()%></textarea>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Fecha del evento:
                </div>
                <div class="col-8">
                    <input type="date" name="fechaEvento" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(evento.getFechaEvento())%>"/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Fecha de máxima para comprar entradas:
                </div>
                <div class="col-8">
                    <input type="date" name="fechaEntradas" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(evento.getFechaFinReservas())%>"/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Coste de la entrada:
                </div>
                <div class="col-8">
                    <input id="coste" type="number" name="coste" step="1.00" value="<%= coste%>"/>  &euro;
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Aforo del evento:
                </div>
                <div class="col-8">
                    <input type="number" name="aforo" value="<%= aforo%>"/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Nº m&aacute;ximo de entradas por usuario:
                </div>
                <div class="col-8">
                    <input type="number" name="entradas" value="<%= entradas%>"/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Etiquetas:
                </div>
                <div class="col-8">
                    <textarea name="etiquetas" rows="4" cols="50"><%= etiquetas%></textarea>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    <input type="submit" class="btn btn-success btn-lg" value="Editar evento">
                </div>
                <div class="row"><br/></div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <a class="btn btn-primary" href="<%=strTo%>" role="button">Volver</a>
            </div>
        </div>
        </div>

    </form>


</section>



</body>
</html>