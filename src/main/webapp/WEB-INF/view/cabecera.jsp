<%@ page import="es.taw.eventosgospring.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: x Cristhian x
  Date: 18/06/2021
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Cabecera</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

  <!-- Iconos de Boostrap -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

</head>
<body>
<%
  int rol;
  UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
  if (usuario != null) {
    rol = (usuario.getRol());
  } else {
    rol = -1;
  }
%>
<!-- Navbar de navegación -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fs-5 text" style="margin-bottom: 20px">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand fs-2" href="/evento/listarEventosDisponibles">EventosGO</a>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="/evento/listarEventosDisponibles">Inicio</a>
        </li>
        <%
          if (rol == 1) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/evento/listarEventosCreados">Mis Eventos</a>
        </li>
        <%
          }
        %>
        <%
          if (rol == 3) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/estudios/">Mis Estudios</a>
        </li>
        <%
          }
        %>
        <%
          if (rol == 4) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/evento/listarEventosAsistidos">Mis Eventos</a>
        </li>
        <%
          }
        %>
        <%
          if (rol == 2 || rol == 4 || rol == 1) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/conversacion/0">Mis Conversaciones</a>
        </li>
        <%
          }
        %>
        <%
          if (rol == 0) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/UsuariosCargarAdmin">Lista Usuarios</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/EventosCargarAdmin">Lista Eventos</a>
        </li>
        <%
          }
        %>
      </ul>
      <%
        if (rol < 0) {
      %>
      <a class="btn btn-outline-light m-4" href="/registrar" role="button">Registrarse</a>
      <a class="btn btn-outline-light" href="/iniciarSesion" role="button">Iniciar Sesión</a>
      <%
      } else {
      %>
      <a class="btn btn-outline-light m-4" href="#" role="button" hidden>Perfil</a>
      <a class="btn btn-outline-light" href="/cerrarSesion" role="button">Cerrar Sesi&oacute;n</a>
      <%
        }
      %>

    </div>
  </div>
</nav>
<!-- END Navbar -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>