
<%@page import="bibliotecaWebApp.model.Prestito"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List<Prestito> lista = (List<Prestito>)request.getAttribute("listaLibriTessera"); %>
<%String nomeUtente = (String) request.getAttribute("username"); %>

<table class="table table-striped">
  
    <h2>Dettagli libri presi in prestito</h2>
  <tr>
   <th>idPrestito </th>
   <th>Username </th>
   <th>Titolo</th>
   <th>Quantità </th>
    
  </tr>
  <% for(Prestito l : lista) { %>
  
  <tr>
    <td>
    <%=l.getIdTessera()%>
    </td>
    <td>
    <%=l.getUsername()%>
    </td>  
     <td>
    <%=l.getTitolo()%>
    </td>    
     <td>
    <%=l.getQuantita()%>
       </td> 
    
  </tr>
<% } %> 
	
</table><br><br>
<form action="tornaIndietro" method="post">
  <input type="submit" class="btn btn-outline-secondary" value="Torna Indietro">
  <input type="hidden" id="username" name="username" value=<%=nomeUtente%>>  
  
</form>

</body>
</html>