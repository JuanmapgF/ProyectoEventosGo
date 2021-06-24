<%@ page import="es.taw.eventosgospring.entity.Usuario" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrador</title>

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
%>
<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->
<section class="container rounded shadow-sm w3-padding border border-primary">
    <figure class="text-center">
        <blockquote class="blockquote">
            <h1><p>¡Bienvenido administrador!</p></h1>
        </blockquote>
        <%= usuario.getNombre()%>
    </figure>
</section>

</body>
</html>