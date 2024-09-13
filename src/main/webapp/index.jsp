<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%  String erreur = (String)request.getAttribute("erreur");%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="bootstrap-4.0.0/dist/css/bootstrap.min.css">
    <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
<body>
<div>
    <div class="cadre">
        <form method="post" action="LoginServlet">
            <div class="container">
                <label><b>Gmail</b></label>
                <input type="text" placeholder="Veuillez saisir votre nom" name="gmail" required>
                <label><b>Mot de passe</b></label>
                <input type="password" placeholder="Veuillez saisir votre mot de passe" name="password" required>
                <button type="submit">Se connecter</button>
                <% if (erreur != null){%>
                    <p class="alert alert-danger"><%= erreur %></>
                <% } %>
            </div>
        </form>
    </div>
</div>
</body>
</html>