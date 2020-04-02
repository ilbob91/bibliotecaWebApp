<%@page import="bibliotecaWebApp.model.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi un Libro</title>
</head>
<body>
<h1>Aggiungi Libro</h1>
<% List<Libro> listaLibri = (List<Libro>)request.getAttribute("listaLibri"); %>

<table> 
   
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
<form action="Aggiungi" method="post">
<div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Titolo</span>
  </div>
  <input type="text" id="titolo" name="titolo"><br><br>
</div>
  
  <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Autore</span>
  </div>
  <input type="text" id="autore" name="autore"><br><br>
</div>
  <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Prezzo</span>
  </div>
  <input type="text" id="prezzo" name="prezzo"><br><br>
</div>
  <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Disponibilità</span>
  </div>
  <input type="number" id="disponibilita" name="disponibilita"><br><br>
</div>
 <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Quantità</span>
  </div>
  <input type="number" id="quantita" name="quantita"><br><br>
</div>
  <input type="submit" class="btn btn-outline-success" style="width:120px; height:45px;" value="Aggiungi">
  </form><br><br>
  <form action="opzioniBiblioteca.jsp">
  <input type="submit" class="btn btn-outline-secondary" value="Torna Indietro">


  

</form>
</body>
</html>