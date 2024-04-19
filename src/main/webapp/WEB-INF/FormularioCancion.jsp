<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Formulario Cancion</title>
</head>
<body>
	<h1>Agregar una nueva Canción:</h1>
	<br>
	<a href="/logout">Logout</a>
	<form:form modelAttribute="canciones" action="/nueva/cancion" method="POST">
	
		<form:label path="nombre" for="nombre">Nombre: </form:label>
		<form:input path="nombre" id="nombre" name="nombre" type="text"></form:input>
		<form:errors path="nombre"></form:errors>
		<br>
		<form:label path="genero" for="genero">Genero: </form:label>
		<form:input path="genero" id="genero" name="genero" type="text"></form:input>
		<form:errors path="genero"></form:errors>
		<br>
		<form:label path="letra" for="letra">Letra: </form:label>
		<form:input path="letra" id="letra" name="letra" type="text"></form:input>
		<form:errors path="letra"></form:errors>
		<br>
		<button>Agregar canción</button>
		<br>
	</form:form>
	<a href="/home">Cancelar</a>
</body>
</html>