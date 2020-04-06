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
<title>Registrati!</title>
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
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/">Registrati! <span class="sr-only">(current)</span></a>
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
		<p class="text-xl-center">Fai il login o registrati!</p>
	</h2>
	<br>
	<br>
	<form action="login" method="post">
		<div class="cliente">

			<p class="text-xl-center">Mail</p>
			<input type="text" class="form-control" id="username" name="username"
				style="width: 250px; height: 50px; margin: auto" placeholder="Mail">
		</div>
		<br>
		<div class="cliente">
			<p class="text-xl-center">Password</p>
			<input type="password" class="form-control" id="password"
				name="password" style="width: 250px; height: 50px; margin: auto"
				placeholder="Password">
		</div>
		<br> <input type="submit"
			class="btn btn-outline-primary btn-block"
			style="width: 150px; height: 45px; margin: auto" name="azione"
			value="Entra"> <br> <input type="submit"
			class="btn btn-outline-primary btn-block"
			style="width: 150px; height: 45px; margin: auto" name="azione"
			value="Registrati">
	</form>
	<br>
	<br>

</body>
</html>