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
<title>About us</title>
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
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/">Registrati! </a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/about.jsp">About<span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</nav>
<p class="text-lg-center">Libreria di Fabio e Bob since 2020. Dal 2020 inoltre si sono aggiunte Marta e Betta, contribuendo all'affito del server gratuito.</p>
<form action="login.jsp">
					<input type="submit" class="btn btn-outline-secondary btn-block"
						style="width: 200px; height: 45px; margin: auto"
						value="Torna Indietro">  
				</form>
</body>
</html>