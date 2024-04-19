<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>home</title>
</head>
<body>
	<h3>Bienvenid@ <c:out value="${nombre}"></c:out></h3>
	<br>
	<a href="/logout">Logout</a>
	<h1>Todas las canciones!</h1>
	<table>
		<thead>
			<tr>
				<th>Cancion</th>
				<th>#numero de colaboraciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cancion}" var="canciones">
				<tr>
					<td><a href="/cancion/${canciones.id}"><c:out value="${canciones.nombre}"></c:out></a> <br> Genero: <c:out value="${canciones.genero}"></c:out></td>
					<td><c:out value="${colaboradoresPorCancion[canciones.id]}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/nueva/cancion">Nueva Cancion</a>
</body>
</html>