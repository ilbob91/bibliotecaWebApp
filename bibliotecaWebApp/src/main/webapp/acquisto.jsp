
<%@page import="bibliotecaWebApp.model.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<style>
table, th, td {
  border: 1px solid #000000;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String nome = (String) request.getAttribute("username"); %>
<%List <Libro> lista = (List<Libro>) request.getAttribute("listaLibri"); %>

<br>

<% String mess = (String) request.getAttribute("mess"); 
	if (mess != null ){
		%>
	<h4><p class="text-md-center text-danger"><%=mess%></p></h4>
		
					
	<% }
%><br>
<table>
  
    <h2>Lista Libri</h2>
  <tr>
   <th>Nome </th>
   <th>Autore </th>
   <th>Quantità Acquistabile</th>
   <th>Prezzo </th>
   
  </tr>
  <% for(Libro l : lista) { %>
  
  <tr>
    <td>
    <%=l.getTitolo()%>
    </td>
    <td>
    <%=l.getAutore()%>
    </td>
    <td>
    <%=l.getDisponibilita()%>
    </td>     
    <td>
    <%=l.getPrezzo()%>
    </td>  
  </tr>
	<% } %> 
</table> <br><br>


<h2>Cosa vuoi comprare, <%=request.getAttribute("username")%> ?</h2>
<form action="acquisto" method = "post">
  <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm"><label for="titolo">Titolo</label></span>
     
      <select name = "titolo">
  
  <% for (Libro l : lista){%>
    <option value="<%=l.getTitolo() %>"> <%=l.getTitolo() %></option>
  
    <% } %>
    
  </select>
   <br><br></div>
   </div>
  
   <div class="input-group input-group-sm mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-sm">Inserisci quantità da acquistare</span>
  </div>
  <input type="number" id="quantita" name="quantita"><br><br></div><br>
  <input type="submit" class="btn btn-outline-success" style="height:45px;" name= "azione" value="Aggiungi al carrello">
  <input type="submit" class="btn btn-outline-danger" style="width:120px; height:45px;" name= "azione" value="Paga">
  <input type="hidden" id="username" name="username" value=<%=nome%>>
  <input type="hidden" id="idScontrino" name="idScontrino" value=<%=(int)request.getAttribute("idScontrino")%>>

   
   <br><br>
   </form>
   <form action="tornaIndietro" method="post">
  <input type="submit" class="btn btn-outline-secondary" value="Torna Indietro">
  <input type="hidden" id="username" name="username" value=<%=nome%>>  
   <input type="hidden" id="idScontrino" name="idScontrino" value=<%=(int)request.getAttribute("idScontrino")%>>
</form>

</body>
</html>