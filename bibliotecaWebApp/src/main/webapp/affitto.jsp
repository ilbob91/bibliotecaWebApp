
<%@page import="bibliotecaWebApp.model.Libro"%>
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
	<div class="container">
		<div class="row">
			<div class="col">
				<br>
				
				<%
					List<Libro> lista = (List<Libro>) request.getAttribute("listaLibri");
				%>



				<%
					String mess = (String) request.getAttribute("mess");
					if (mess != null) {
				%>
				<h4>
					<p class="text-md-center text-danger"><%=mess%></p>
				</h4>


				<%
					}
				%><br>
				<table class="table table-striped">

					<h2>Lista Libri</h2>
					<tr>
						<th>Nome</th>
						<th>Autore</th>
						<th>Quantità Disponibile</th>
						<th>Prezzo</th>

					</tr>
					<%
						for (Libro l : lista) {
					%>

					<tr>
						<td><%=l.getTitolo()%></td>
						<td><%=l.getAutore()%></td>
						<td><%=l.getDisponibilita()%></td>
						<td><%=l.getPrezzo()%></td>
					</tr>
					<%
						}
					%>
				</table>
				<br>
				<br>
			</div>
			<div class="col">
				<h2>
					Quale libro vuoi prendere in prestito,
					<%=session.getAttribute("username")%>
					?
				</h2>
				<form action="affitto" method="post">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm"><label
								for="titolo">Titolo</label></span> <select name="titolo">

								<%
									for (Libro l : lista) {
								%>
								<option value="<%=l.getTitolo()%>">
									<%=l.getTitolo()%></option>

								<%
									}
								%>

							</select> <br>
							<br>
						</div>
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm">Inserisci
								quantità da prendere in prestito</span>
						</div>
						<input type="number" id="quantita" name="quantita"><br>
						<br>
					</div>
					<br> <input type="submit" class="btn btn-outline-success"
						style="height: 45px;" name="azione" value="Aggiungi al carrello">
					<input type="submit" class="btn btn-outline-danger"
						style="width: 120px; height: 45px;" name="azione" value="Affitta">
					
					<%
					if(request.getAttribute("idTessera") != null){
				%>
					<input type="hidden" id="idTessera" name="idTessera"
						value=<%=(int) request.getAttribute("idTessera")%>> <br>
					<br>
				</form><%}%>
				<form action="affitto" method="post">
					<input type="submit" class="btn btn-outline-secondary" name= "azione"
						value="Torna Indietro">  	<%
					if(request.getAttribute("idTessera") != null){
				%>
					<input type="hidden" id="idTessera" name="idTessera"
						value=<%=(int) request.getAttribute("idTessera")%>> <br>
					<br>
				</form><%}%>
			</div>
		</div>
	</div>
</body>
</html>
