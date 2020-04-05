<%@page import="bibliotecaWebApp.model.Prestito"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Invio mail</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<% List<Prestito> listaPrestiti = (List<Prestito>)request.getAttribute("listaLibriPrestati"); %>
	<div class="container">
		<div class="row">
			<div class="col-xl align-self-center ">
				<table class="table table-striped">

					<tr>
						
						<th>Titolo</th>
						<th>Data Affitto</th>
						<th>Username</th>

					</tr>
					<% for(Prestito p : listaPrestiti) { %>

					<tr>
						
						<td><%=p.getTitolo() %></td>
						<td><%=p.getDataAffitto() %></td>
						<td><%=p.getUsername()%></td>
					</tr>
					<% } %>

				</table>
				<br>
			</div>
			<div class="col-xl align-self-center ">
				 
				<form action="invioMailPrestito" method="post">
					        
					<table border="0" width="35%" align="center">
						            
						<h2>
							<p class="text-xl-center">Invio mail per restituzione del
								libro</p>
						</h2>
						<br>
						<br>             
						<tr>
							                
							<td width="50%">Indirizzo</td>                 
							<td><input type="text" name="indirizzo" size="50" /></td>
							            
						</tr>
						            
						<tr>
							                
							<td>Oggetto mail:</td>                 
							<td><input type="text" name="oggetto" size="50" /></td>
							            
						</tr>
						            
						<tr>
							                
							<td>Contenuto</td>                 
							<td><textarea rows="10" cols="39" name="contenuto"></textarea>
							</td>             
						</tr>
						            
						<tr>
							                
							<td colspan="2" align="center"><input type="submit"
								class="btn btn-outline-success btn-block"
								style="width: 200px; height: 45px; margin: auto" value="Invia"></td>
							            
						</tr>
						        
					</table>
					              
				</form>
				<form action="opzioniBiblioteca.jsp">
					<input type="submit" class="btn btn-outline-secondary btn-block"
						style="width: 200px; height: 45px; margin: auto"
						value="Torna Indietro">  
				</form>
			</div>
		</div>
	</div>
</body>
</html>