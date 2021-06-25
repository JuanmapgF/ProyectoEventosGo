<%-- 
    Document   : crearEstudio
    Created on : 29-abr-2021, 14:11:36
    Author     : juanm
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Estudio</title>
        <%
            SimpleDateFormat anio = new SimpleDateFormat("YYYY");
        %>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Indicador de progreso -->
        <div class="container py-4">
            <div class="position-relative m-4">
                <div class="progress" style="height: 3px;">
                    <div class="progress-bar" role="progressbar" style="width: 50%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <button type="button" class="position-absolute top-0 start-0 translate-middle btn btn-lg btn-primary rounded-pill" style="width: 3rem; height:3rem;">1</button>
                <button type="button" class="position-absolute top-0 start-100 translate-middle btn btn-lg btn-secondary rounded-pill" style="width: 3rem; height:3rem;">2</button>
            </div>
        </div>
        <!-- END Indicador de progreso -->

        <!-- Formulario de crear un Estudio -->

        <form class="container shadow-lg p-3 mb-5 bg-body rounded" action="/estudios/almacenar/">

            <div class="alert alert-info mb-3">Todos los estudios estadísticos que eres capaz de crear serán vinculados a los usuarios que han asistido a eventos, restringido a los filtros que t&uacute; como analista has considerado de estudio.</div>
            <div class="mb-3">
                <label for="titulo" class="form-label">T&iacute;tulo</label>
                <input type="text" name="titulo" class="form-control" id="titulo"/>
            </div>
            <div class="mb-3">
                <label class="form-label">A&ntilde;o evento</label>
                <input type="number" class="form-control" name="anio" min="2000" max="<%= anio.format(new Date())%>"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&iacute;nima</label>
                <input type="range" class="form-range" name="edad_min" onchange="updateTextInput(this.value, 'emin');" value="0"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="0" id="emin"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&aacute;xima</label>
                <input type="range" class="form-range" name="edad_max" onchange="updateTextInput(this.value, 'emax');" value="100"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="100" id="emax"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="0" name="masculino">
                    <label class="form-check-label">Masculino</label>
                </div>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="1" name="femenino">
                    <label class="form-check-label">Femenino</label>
                </div>
                <div class="mb-3 form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="2" name="otro">
                    <label class="form-check-label">Otro</label>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Ciudad</label>
                <input type="text" class="form-control" name="ciudad"/>
            </div>
            <div class="pt-3">
                <button type="submit" class="btn btn-primary btn-lg">Siguiente</button>
                <button type="reset" class="btn btn-secondary btn-sm">Limpiar</button>
                <a class="btn btn-danger btn-sm" href="/estudios/">Cancelar</a>
            </div>
        </form>
        <script>
            function updateTextInput(val, inp) {
                document.getElementById(inp).value = val;
            }
        </script>

        <!-- END Formulario de crear un Estudio -->

    </body>
</html>
