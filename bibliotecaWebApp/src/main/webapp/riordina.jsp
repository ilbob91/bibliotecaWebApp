<%@page import="bibliotecaWebApp.model.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Riordina Libri</title>
</head>
<body>
<div class= "container">
 <div class="row">
    <div class="col-xl align-self-center ">
<%List<Libro> listaLibri = (List<Libro>) request.getAttribute("listaLibri");%>
<table class="table table-striped"> 
   
  <tr>
   <th>Titolo </th>
   <th>Autore </th>
   <th>Prezzo </th>
   <th>Disponibilità </th>
   <th>Quantità </th>
  </tr>
  <% for(Libro l : listaLibri) { %>
  
  <tr>
    <td>
    <%=l.getTitolo()%>
    </td>
    <td>
    <%=l.getAutore()%>
    </td>  
     <td>
    <%=l.getPrezzo()%>
    </td>    
     <td>
    <%=l.getDisponibilita()%>
    </td>  
     <td>
    <%=l.getQuantita()%>
    </td>       
  </tr>
  <% } %> 
	
</table> <br>
</div>

    <div class="col-xl align-self-center">
    <% String messaggio = (String) request.getAttribute("messaggio"); 
	if (messaggio != null ){
		%>
		
		<p class="text-md-center text-danger"><%=messaggio%></p>
					
	<% }
	


%>
<h2>Riordina Libri</h2><br>
<form action="riordina" method = "post">
<div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm"><label for="nomeProdotto">Titolo</label></span>
     
	<select name = "titolo">
	
	<% for (Libro l : listaLibri){%>
	  <option value="<%=l.getTitolo()%>"><%=l.getTitolo() %></option>
	  
	  <% } %>
	</select>
	<br>
  </div>
 <br><br></div>
   <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Inserisci quantità </span>
  </div>
  <input type="number" id="quantita" min = 1 name="quantita"><br><br></div><br>
  
  <input type="submit" class="btn btn-outline-success" style="height:45px;" name= "azione" value="Riordina">
     
   <br><br>
   </form>
   <form action="opzioniBiblioteca.jsp">
  <input type="submit" class="btn btn-outline-secondary" style="width:120px; height:45px;"  value="Torna Indietro">
  
</form>
</div>


</div>
</div>




</body>
</html>