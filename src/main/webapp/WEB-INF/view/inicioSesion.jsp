<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: x Cristhian x
  Date: 18/06/2021
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <title>Pagina inicio sesion</title>
</head>
<%
    String error = (String) request.getAttribute("error");
%>
<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<section class="container rounded shadow-lg w3-padding">
    <h1>Inicio de Sesion</h1>

    <div class="container"><form action="/autenticar" method="POST">
        <div class="form-group">
            <div class="error"><% if (error != null) {%> <%= error%>  <% }%>  </div> <br/>
            <label for="exampleInputEmail1">Correo</label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="correo" aria-describedby="emailHelp">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Contrase&ntilde;a</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>
        <div class="row"><br/></div>
        <button type="submit" class="btn btn-primary">Enviar</button>
    </form> </div>
</section>

</body>
</html>
