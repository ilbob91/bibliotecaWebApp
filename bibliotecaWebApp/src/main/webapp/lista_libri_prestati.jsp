<%@page import="bibliotecaWebApp.model.Prestito"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Stampa Libri Prestati</title>
</head>
<body>
<% List<Prestito> listaPrestiti = (List<Prestito>)request.getAttribute("listaLibriPrestati"); %>





	<table class="table table-striped">

		<tr>
			<th>Id Libro</th>
			<th>Titolo</th>
			<th>Data Affitto</th>
			<th>Username</th>

		</tr>
		<% for(Prestito p : listaPrestiti) { %>

		<tr>
			<td><%=p.getIdLibro()%></td>
			<td><%=p.getTitolo() %></td>
			<td><%=p.getDataAffitto() %></td>
			<td><%=p.getUsername()%></td>
		</tr>
		<% } %>

	</table>
	<br>
<form action="opzioniBiblioteca.jsp">
  <input type="submit" class="btn btn-outline-secondary btn-block" style="width:150px; height:50px;margin:auto"  value="Torna Indietro">

</form>
</body>
</html>