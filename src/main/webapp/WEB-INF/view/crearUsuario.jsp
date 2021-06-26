<%--
  Created by IntelliJ IDEA.
  User: Kiko BM
  Date: 26/06/2021
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Datos del usuario</title>

  <!--        Boostrap -->

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

  <!--        W3 CSS -->
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


</head>

<body>
<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<section class="container rounded shadow w3-padding">

  <header class="container">
    <h1>Datos del Usuario</h1>
  </header>

  <form action = "/usuario/guardarUsuarioAdmin">
    <table>
      <input type="hidden" name="id" value="" />
      <tr>
        <td>Nombre:</td>
        <td><input type="text" name="nombre" maxlength="30" size="30"  /></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email"  maxlength="40" size="40" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="text" name="password" maxlength="30" size="30"  />
      </tr>
      <tr>
        <td>Rol</td>
        <td><select name="rol">
          <option value="0">Administrador</option>
          <option value="1">Creador</option>
          <option value="2">Teleoperador</option>
          <option value="3">Analista</option>
          <option value="4">Usuario</option>
        </select></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="Guardar"/>
        </td>
      </tr>
    </table>
  </form>

</section>


</body>
</html>

