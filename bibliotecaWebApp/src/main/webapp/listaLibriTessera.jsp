
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


<table class="table table-striped">
  
    <h2>Dettagli libri presi in prestito</h2>
  <tr>
   <th>idPrestito </th>
   <th>Username </th>
   <th>Titolo</th>
   <th>Quantità </th>
   <th>Data Affitto</th>
     <th>Data Fine Affitto</th>
     <th>Codice</th>
    
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
                <td>
    <%=l.getDataAffitto()%>
       </td> 
        <td>
    <%=l.getDataDiFine()%>
    </td>    
       <td>
    <%=l.getIdPrestito()%>
    </td>  
      <td>
       
        <form action="opzioniCliente" method="post">
  <input type="submit" name="action" value="Restituisci"> 
  <input type="hidden" id="titolo" name="titolo" value=<%=l.getTitolo()%>>
  <input type="hidden" id="disp" name="disp" value=<%=l.getQuantita()%>>
 <input type="hidden" id="prestito" name="prestito" value=<%=l.getIdPrestito()%>>
	</form>  
	</td> 
    
  </tr>
<% } %> 
	
</table><br><br>
<form action="tornaIndietro" method="post">
  <input type="submit" class="btn btn-outline-secondary" value="Torna Indietro">
    
  
</form>

</body>
</html>