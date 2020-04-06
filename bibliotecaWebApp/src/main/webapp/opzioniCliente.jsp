<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Cliente</title>

</head>
<body>
<%@include file="navbarCliente.jsp"%>
<br>
<%String nome = (String) request.getAttribute("username"); %>
<h2><p class="text-xl-center">Fai una scelta, <%=nome%></p></h2><br><br>
<% String mess = (String) request.getAttribute("mess"); 
	if (mess != null ){
		%>
	<h4><p class="text-md-center text-danger"><%=mess%></p></h4>
		
					
	<% }
	
%><br>

<form action="opzioniCliente" method="post">
 <input type="submit"class="btn btn-outline-primary btn-block" style="width: 300px; height: 60px; margin: auto" name= action value="Compra libri">
 <br> <input type="submit"class="btn btn-outline-primary btn-block" style="width: 300px; height: 60px; margin: auto" name= action value="Affitta libri">
<br> <input type="submit"class="btn btn-outline-primary btn-block" style="width: 300px; height: 60px; margin: auto" name= action value="Stampa acquisti">
<br> <input type="submit"class="btn btn-outline-primary btn-block" style="width: 300px; height: 60px; margin: auto" name= action value="Stampa prestiti">

  <input type="hidden" id="username" name="username" value=<%=nome%>>
</form>  
 <br>   
<form action="login.jsp">
  <input type="submit"  class="btn btn-outline-secondary btn-block" style="width:150px; height:50px;margin:auto" value="Logout">
  </form>
</body>
</html>