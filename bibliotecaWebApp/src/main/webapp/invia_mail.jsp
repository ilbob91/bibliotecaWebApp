<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<head>
<meta charset="ISO-8859-1">
<title>Invio mail</title>
</head>
<body>
 <form action="invioMailPrestito" method="post">
        <table border="0" width="35%" align="center">
            <h2><p class="text-xl-center">Invio mail per restituzione del libro</p></h2><br><br>
            <tr>
                <td width="50%">Indirizzo</td>
                <td><input type="text" name="indirizzo" size="50"/></td>
            </tr>
            <tr>
                <td>Oggetto mail: </td>
                <td><input type="text" name="oggetto" size="50"/></td>
            </tr>
            <tr>
                <td>Contenuto</td>
                <td><textarea rows="10" cols="39" name="contenuto"></textarea> </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Invia"/></td>
            </tr>
        </table>
         
    </form>
</body>
</html>