<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : editarEstudio
    Created on : 12-may-2021, 14:10:21
    Author     : Pablo
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Estudio</title>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
            int idEstudio = (Integer) request.getAttribute("idEstudio");
            String titulo = String.valueOf(request.getAttribute("titulo"));
            int eMin = (Integer) request.getAttribute("eMin");
            int eMax = (Integer) request.getAttribute("eMax");
            int anio = (request.getAttribute("anio") != null) ? (Integer) request.getAttribute("anio") : -1;
            String ciudad = (request.getAttribute("ciudad") != null) ? String.valueOf(request.getAttribute("ciudad")) : "";
            int masculino = ((request.getAttribute("masculino") != null) && ((Integer) request.getAttribute("masculino")) > -1) ? 1 : 0;
            int femenino = ((request.getAttribute("femenino") != null) && ((Integer) request.getAttribute("femenino")) > -1) ? 1 : 0;
            int otro = ((request.getAttribute("otro") != null) && ((Integer) request.getAttribute("otro")) > -1) ? 1 : 0;
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

        <form:form class="container shadow-lg p-3 mb-5 bg-body rounded" method="post" action="/estudios/almacenar" modelAttribute="resultado">
            <form:hidden path="id"/>
            <div class="alert alert-info mb-3">Todos los estudios estadísticos que eres capaz de crear serán vinculados a los usuarios que han asistido a eventos, restringido a los filtros que t&uacute; como analista has considerado de estudio.</div>
            <div class="mb-3">
                <label for="titulo" class="form-label">T&iacute;tulo</label>
                <form:input path="titulo" type="text" class="form-control" id="titulo"/>
            </div>
            <div class="mb-3">
                <label class="form-label">A&ntilde;o evento</label>
                <form:input path="anio" type="number" class="form-control" min="2000" max="<%= sdf.format(new Date())%>"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&iacute;nima</label>
                <form:input path="edad_min" type="range" class="form-range" onchange="updateTextInput(this.value, 'emin');" value="<%= eMin %>"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="<%= eMin %>" id="emin"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&aacute;xima</label>
                <form:input path="edad_max" type="range" class="form-range" onchange="updateTextInput(this.value, 'emax');" value="<%= eMax %>"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="<%= eMax %>" id="emax"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <div class="form-check form-switch">
                    <form:checkbox path="masculino" class="form-check-input" value="0"/>
                    <label class="form-check-label">Masculino</label>
                </div>
                <div class="form-check form-switch">
                    <form:checkbox path="femenino" class="form-check-input" value="1"/>
                    <label class="form-check-label">Femenino</label>
                </div>
                <div class="mb-3 form-check form-switch">
                    <form:checkbox path="otro" class="form-check-input" value="2"/>
                    <label class="form-check-label">Otro</label>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Ciudad</label>
                <form:input path="ciudad" type="text" class="form-control"/>
            </div>
            <div class="pt-3">
                <button type="submit" class="btn btn-primary btn-lg">Siguiente</button>
                <button type="reset" class="btn btn-secondary btn-sm">Limpiar</button>
                <a class="btn btn-danger btn-sm" href="/estudios/">Cancelar</a>
            </div>
        </form:form>

        <!-- Formulario de crear un Estudio -->

        <%--<form class="container shadow-lg p-3 mb-5 bg-body rounded" action="ServletCalcularEstudio">

            <div class="alert alert-info mb-3">Todos los estudios estadísticos que eres capaz de crear serán vinculados a los usuarios que han asistido a eventos, restringido a los filtros que t&uacute; como analista has considerado de estudio.</div>
            <div class="mb-3">
                <label for="title" class="form-label">T&iacute;tulo</label>
                <input type="text" name="titulo" class="form-control" id="titulo" value="<%= titulo %>"/>
            </div>
            <div class="mb-3">
                <label for="year" class="form-label">A&ntilde;o evento</label>
                <input type="number" class="form-control" name="anio" min="2000" max="<%= sdf.format(new Date())%>" value="<%= anio %>"/>

            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&iacute;nima</label>
                <input type="range" class="form-range" name="edad_min" onchange="updateTextInput(this.value, 'emin');" value="<%= eMin%>"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="<%= eMin%>" id="emin"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&aacute;xima</label>
                <input type="range" class="form-range" name="edad_max" onchange="updateTextInput(this.value, 'emax');" value="<%= eMax%>"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="<%= eMax%>" id="emax"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="0" name="masculino" <%= (masculino == 1) ? "checked=\"\"" : ""%>>
                    <label class="form-check-label" for="masculino">Masculino</label>
                </div>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="1" name="femenino" <%= (femenino == 1) ? "checked=\"\"" : ""%>>
                    <label class="form-check-label" for="femenino">Femenino</label>
                </div>
                <div class="mb-3 form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="2" name="otro" <%= (otro == 1) ? "checked=\"\"" : ""%>>
                    <label class="form-check-label" for="otro">Otro</label>
                </div>
            </div>

            <div class="mb-3">
                <label for="city" class="form-label">Ciudad</label>
                <input type="text" class="form-control" name="ciudad" value="<%= ciudad%>"/>
            </div>

            <input type="text" class="form-control" hidden="" name="idEstudio" value="<%= idEstudio%>"/>

            <div class="pt-3">
                <button type="submit" class="btn btn-primary btn-lg">Siguiente</button>
                <button type="reset" class="btn btn-secondary btn-sm">Reset</button>
                <a class="btn btn-danger btn-sm" href="ServletEstudioCargar">Cancelar</a>
            </div>
        </form>--%>
        <script>
            function updateTextInput(val, inp) {
                document.getElementById(inp).value = val;
            }
        </script>

        <!-- END Formulario de crear un Estudio -->

    </body>
</html>
