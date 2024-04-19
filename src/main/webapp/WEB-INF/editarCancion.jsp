<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar canción</title>
</head>
<body>
    <h1>Agregar a <c:out value="${cancionActual.nombre}"></c:out></h1>
    <form:form modelAttribute="cancionActual" action="/procesar/editar/${cancionActual.id}" method="POST">
		
        <form:label path="nombre" for="nombre">Nombre: </form:label>
        <form:input path="nombre" id="nombre" name="nombre" type="text"></form:input>
        <form:errors path="nombre"></form:errors>
        <br>
        <form:label path="genero" for="genero">Género: </form:label>
        <form:input path="genero" id="genero" name="genero" type="text"></form:input>
        <form:errors path="genero"></form:errors>
        <br>
		<p>Letra actual: <c:out value="${cancionActual.letra}"></c:out></p>
        <p>Agrega tu letra:</p>
        <textarea id="nuevaLetra" name="nuevaLetra" rows="5" cols="50"></textarea>
        <form:errors path="letra"></form:errors>
        <br>
        <button>Agregar canción</button>
        <br>
    </form:form>
    <br>
    <a href="/home">Cancelar</a>
    <c:if test="${cancionActual.creador eq sessionScope.nombre}">
        <form action="/eliminarCancion/${cancionActual.id}" method="POST">
            <input type="hidden" name="_method" value="DELETE">
            <button type="submit">Eliminar canción</button>
        </form>
    </c:if>
</body>
</html>
