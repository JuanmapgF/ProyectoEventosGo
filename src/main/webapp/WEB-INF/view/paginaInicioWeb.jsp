<%--
  Created by IntelliJ IDEA.
  User: x Cristhian x
  Date: 18/06/2021
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.eventosgospring.dto.EventoDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pagina Inicio</title>

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jjdbc:derby://localhost:1527/sampleTAW [sampleTAW on SAMPLETAW]sdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="CSS/paginaInicioWeb.css" media="screen" />

</head>
<body>
<%
    List<EventoDTO> eventosDisponibles = (List)request.getAttribute("eventosDisponibles");

%>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->


<!-- SLIDES -->
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="Imagenes/Ejemplo1.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="Imagenes/Ejemplo2.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="Imagenes/Ejemplo3.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<!-- END SLIDES -->
<%
    for(EventoDTO evento : eventosDisponibles){
%>

<div class="card" style="width: 18rem;">
    <img src="Imagenes/eventoEjemplo1.jpg" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title"><%= evento.getTitulo() %></h5>
        <p class="card-text"><%= evento.getDescripcion()%></p>
        <a class="btn btn-primary bi bi-hammer" href="/evento/verEvento/<%= evento.getId() %>" role="button"> Ir al evento</a>
    </div>
</div>
<%
    }
%>
</body>