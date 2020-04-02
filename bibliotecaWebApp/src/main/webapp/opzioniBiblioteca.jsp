<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione Biblioteca</title>
</head>
<body>

<br>
<h2><p class="text-xl-center">Scegli</p></h2><br><br>
<form action="gestione" method="post">
  <input type="submit" class= "btn btn-outline-primary btn-block" style="width:300px; height:60px;margin:auto" name= "azione" value="Aggiungi un libro"> 
  <br>
  <input type="submit" class= "btn btn-outline-primary btn-block"style="width:300px; height:60px;margin:auto" name= "azione" value="Riordina libro">
   <br>
  <input type="submit" class= "btn btn-outline-primary btn-block"style="width:300px; height:60px;margin:auto" name= "azione" value="Invia mail">
   <br>
   <input type="submit" class= "btn btn-outline-primary btn-block"style="width:300px; height:60px;margin:auto" name= "azione" value="Stampa lista libri">
   <br>
  <input type="submit" class= "btn btn-outline-primary btn-block"style="width:300px; height:60px;margin:auto" name= "azione" value="Stampa lista libri venduti">
  <br>
   <input type="submit" class= "btn btn-outline-primary btn-block"style="width:300px; height:60px;margin:auto" name= "azione" value="Stampa lista libri prestati">
  </form>
  <br>

 
 <br>
 
   <form action="login.jsp">
  <input type="submit"class="btn btn-outline-secondary btn-block"style="width:200px; height:45px;margin:auto" value="Torna Indietro">

</body>s</html>