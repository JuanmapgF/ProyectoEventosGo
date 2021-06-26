<%--
  Created by IntelliJ IDEA.
  User: Kiko BM
  Date: 26/06/2021
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>

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
<%
    UsuarioDTO user = (UsuarioDTO) request.getAttribute("usuario");
    String strNombre = "", strEmail = "", strPassword = "", strId = "", strRol = "";
    int rol2 = -1;

    if (user != null) {
        strNombre = user.getNombre();
        strEmail = user.getCorreo();
        strPassword = user.getContrasena();
        strId = user.getId().toString();
        rol2 = user.getRol();

        if (rol2 > 0) {
            strRol = Integer.toString(rol2);
        }
    }

%>
<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<div>

    <section class="container rounded shadow w3-padding">

        <header class="container">
            <h1>Datos del Usuario</h1>
        </header>

    <form action = "/usuario/guardarUsuarioAdmin">
        <table>
            <input type="hidden" name="id" value="<%= strId%>" />
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" maxlength="30" size="30" value="<%= strNombre%>" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"  maxlength="40" size="40" value="<%= strEmail%>" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="text" name="password" maxlength="30" size="30"  value="<%= strPassword%>"/>
            </tr>
            <tr>
                <td>Rol</td>
                <td><select name="rol">
                    <option value="0" <%= rol2 == 0 ? "selected" : ""  %>>Administrador</option>
                    <option value="1" <%= rol2 == 1 ? "selected" : ""  %>>Creador</option>
                    <option value="2" <%= rol2 == 2 ? "selected" : ""  %>>Teleoperador</option>
                    <option value="3" <%= rol2 == 3 ? "selected" : ""  %>>Analista</option>
                    <option value="4" <%= rol2 == 4 ? "selected" : ""  %>>Usuario</option>
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
</div>
</body>
</html>