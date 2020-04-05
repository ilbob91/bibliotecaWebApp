
<%@page import="bibliotecaWebApp.model.Scontrino"%>
<%@page import="java.util.List"%>
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
<title>Insert title here</title>
</head>
<body>
<%@include file="navbarCliente.jsp"%>
	<%
		List<Scontrino> listaScontrini = (List<Scontrino>) request.getAttribute("listaScontrini");
	%>
	<%
		String nomeUtente = (String) request.getAttribute("username");
	%>
	<table class="table table-striped">

		<h2>Lista Scontrini</h2>
		<tr>
			<th>idScontrino</th>
			<th>Username</th>
			<th>Data</th>
			<th>Spesa</th>
			<th>Dettagli</th>

		</tr>
		<%
			for (Scontrino s : listaScontrini) {
		%>

		<tr>
			<td><%=s.getIdScontrino()%></td>
			<td><%=s.getUsername()%></td>
			<td><%=s.getData()%></td>
			<td><%=s.getCostoTotale()%></td>
			<td>
				<form action="opzioniCliente" method="post">
					<input type="submit" class="btn btn-outline-secondary" name="action" value="Dettagli"> <input
						type="hidden" id="username" name="username" value=<%=nomeUtente%>>
					<input type="number" hidden="true" id="id" name="id"
						value=<%=s.getIdScontrino()%>>
				</form>
			</td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<br>
	<form action="tornaIndietro" method="post">
		<input type="submit" class="btn btn-outline-secondary"
			value="Torna Indietro"> <input type="hidden" id="username"
			name="username" value=<%=nomeUtente%>>

	</form>
</body>
</html>