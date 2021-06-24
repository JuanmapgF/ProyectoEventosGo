<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: x Cristhian x
  Date: 19/06/2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro Usuario</title>

    <!--        Boostrap -->

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <!--        W3 CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


</head>
<%
    String error = (String) request.getAttribute("error");
%>
<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<h1>Datos del usuario</h1>
<form method='POST' action="/usuario/guardar" >
    <table>
        <%
            if(error!= null && !error.isEmpty()){

        %>
        <%= error %> <br/>
        <%
            }
        %>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" required title="Debes rellenar el campo Nombre"/></td>
        </tr>
        <tr>
            <td>Apellidos:</td>
            <td><input type="text" name="apellidos" required title="Debes rellenar el campo Apellidos"/></td>
        </tr>
        <tr>
            <td>Correo:</td>
            <td><input type="email" name="correo" required title="Debes rellenar el campo Correo"/></td>
        </tr>
        <tr>
            <td>Contraseña</td>
            <td><input type="password" name="pass1" required title="Debes rellenar el campo Contraseña"/></td>
        </tr>
        <tr>
            <td>Confirmar contraseña</td>
            <td><input type="password" name="pass2" required title="Debes rellenar el campo Confirmar Contraseña"</td>
        </tr>
        <tr></tr>
        <tr>
            <td>Sexo:</td>
            <td><select name="Sexo" required title="Debes rellenar el campo Sexo">
                <option value="0">Hombre</option>
                <option value="1">Mujer</option>
                <option value="2">Otro</option>
            </select></td>
        </tr>
        <tr>
            <td>Ciudad de residencia:</td>
            <td><input type="text" name="ciudad" required title="Debes rellenar el campo Ciudad"/></td>
        </tr>
        <tr>
            <td>Domicilio:</td>
            <td><input type="text" name="domicilio" required title="Debes rellenar el campo Domicilio"/></td>
        </tr>
        <tr>
            <td>Fecha de nacimiento: </td>
            <td><input type="date" name="fechaNacimiento" required title="Debes rellenar el campo Fecha de Nacimiento"/></td>
        </tr>
        <tr></tr>
        <tr>
            <td colspan="10"><input type="submit" value="Guardar"></td>
        </tr>

    </table>
</form>

</body>
</html>