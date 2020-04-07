<%@page import="bibliotecaWebApp.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Il tuo Profilo!</title>
</head>
<body>
	<%@include file="navbarCliente.jsp"%>
	<%
		String nome = (String) request.getAttribute("username");
	%>
	<%
		Utente utente = (Utente) request.getAttribute("utente");
	%>

	<p class="text-xl-center">
		La tua mail è:
		<%=utente.getUsername()%></p>
	<br>
	<p class="text-xl-center">La tua immagine è:</p>
	<div class="text-center">
		<img alt="immagine" width="300" height="300"
			src="data:image/jpg;base64,<%=utente.getImmagine()%> " 
			class="img-thumbnail"> <input type="hidden" id="username"
			name="username" value=<%=nome%>>
		<form action="modifica" method = "post" enctype="multipart/form-data">
			<h3><p class="text-xl-center">Clicca per modificare l'immagine</p></h3>
			<input type="file" style="width: 150px; height: 50px; margin: auto"
				id="image" name="image"> <input type="submit" 
				class="btn btn-outline-secondary btn-block" 
				style="width: 150px; height: 50px; margin: auto" value="Modifica">
				<input type="hidden" id="username"
			name="username" value=<%=nome%>>
		</form>
	</div>



</body>
</html>