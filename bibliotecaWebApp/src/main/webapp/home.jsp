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
<title>Home</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <span class="navbar-brand mb-0 h1"> <img src="download.png" width="30" height="30" class="d-inline-block align-top" alt="">
  Libreria Gruppo 3</span>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/registrazione.jsp">Registrati! <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/about.jsp">About</a>
      </li>
    </ul>
  </div>
</nav>

	<%
		String messaggio = (String) request.getAttribute("messaggio");
		if (messaggio != null) {
	%>

	<p class="text-md-center text-danger"><%=messaggio%></p>

	<%
		}
	%>
	<br>
	<h2>
		<p class="text-xl-center">Fai il login o accedi alla registrazione!</p>
	</h2>
	<br>
	<br>
	<form action="login.jsp" method="post">
		
		<br> <input type="submit"
			class="btn btn-outline-primary btn-block"
			style="width: 150px; height: 45px; margin: auto" name="azione"
			value="Login"> <br> 
	</form>
	<form action="registrazione.jsp" method="post">
	<input type="submit"
			class="btn btn-outline-primary btn-block"
			style="width: 150px; height: 45px; margin: auto" name="azione"
			value="Registrati">
			</form>
	<br>
	<br>

</body>
</html>