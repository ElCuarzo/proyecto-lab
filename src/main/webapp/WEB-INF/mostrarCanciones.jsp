<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Mostrar Canciones</title>
</head>
<body>
	<h1><c:out value="${cancionActual.nombre}"></c:out></h1>
	<h3>(Creada por <c:out value="${cancionActual.creador}"></c:out>)</h3>
	<h3>Genero: <c:out value="${cancionActual.genero}"></c:out></h3>
	<br>
	<h3>Letra:</h3>
	<p><c:out value="${cancionActual.letra}"></c:out></p>
	<a href="/canciones/${cancionActual.id}/editar">Contribuir</a>
	<h1>Collaborators</h1>
		<c:forEach items="${cancionActual.usuarios}" var="usuarios">
			<li><c:out value="${usuarios.nombre}"></c:out></li>
		</c:forEach>
</body>
</html>