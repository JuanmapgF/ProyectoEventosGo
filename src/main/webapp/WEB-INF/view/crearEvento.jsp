<%--
  Created by IntelliJ IDEA.
  User: Kiko BM
  Date: 25/06/2021
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
%>

<body>

<%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

<section class="container rounded shadow w3-padding">
    <header class="container">
        <h1>Datos del evento</h1>
    </header>

    <form action="/evento/guardarEvento" method="POST">
        <div class="container">
            <div class="row">
                <div class="col-8">
                    <input type="text" name="idCreador" value="<%= creador.getId() %>" hidden/>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    T&iacute;tulo:
                </div>
                <div class="col-8">
                    <input type="text" name="titulo" />
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Descripci&oacute;n:
                </div>
                <div class="col-8">
                    <textarea name="descripcion" rows="4" cols="20"></textarea>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Fecha del evento:
                </div>
                <div class="col-8">
                    <input type="date" name="fechaEvento" />
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Fecha de máxima para comprar entradas:
                </div>
                <div class="col-8">
                    <input type="date" name="fechaEntradas" />
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Coste de la entrada:
                </div>
                <div class="col-8">
                    <input id="coste" type="number" name="coste" step="1.00" />  &euro;
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Aforo del evento:
                </div>
                <div class="col-8">
                    <input type="number" name="aforo" />
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Nº m&aacute;ximo de entradas por usuario:
                </div>
                <div class="col-8">
                    <input type="number" name="entradas" />
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    Etiquetas:
                </div>
                <div class="col-8">
                    <textarea name="etiquetas"  ></textarea>
                </div>
            </div>
            <div class="row"><br/></div>
            <div class="row">
                <div class="col">
                    <input type="submit" class="btn btn-success btn-lg" value="Crear evento">
                </div>
                <div class="row"><br/></div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                    <a class="btn btn-danger" href="/evento/listarEventosCreados" role="button">Volver</a>
            </div>
        </div>

    </form>


</section>



</body>
</html>