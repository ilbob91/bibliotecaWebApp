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

</head>
<body>
<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
  <span class="navbar-brand mb-0 h1"><img src="download.png" width="30" height="30" class="d-inline-block align-top" alt="">
  Libreria Gruppo 3</span>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
       <% String path = request.getContextPath(); %>
     <form action="indietro" method="post" class="form-inline my-2 my-lg-0">
      <button class="btn btn-outline-link my-2 my-sm-0" type="submit" value="Home">Opzioni</button>
      </form>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:8080/bibliotecaWebApp/about.jsp">About</a>
      </li>
    </ul>
    <form action="<%=path%>/cliente/opzioniCliente" method="post"class="form-inline my-2 my-lg-0">
      
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit"name="action" value="Logout">LogOut</button>
    </form>
  </div>
</nav>
</body>
</html>