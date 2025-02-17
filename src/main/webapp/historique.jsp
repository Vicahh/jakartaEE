<%@ page import="models.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%  List<Transaction>  historiques = (List<Transaction>)request.getAttribute("historique");%>
<%  String value = (String)request.getAttribute("value");%>
<html>
<head>
    <title>Historique</title>
    <link rel="stylesheet" href="bootstrap-4.0.0/dist/css/bootstrap.min.css">
    <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #E6F7FF">
    <a class="navbar-brand" href="#">Gestion de stock</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Update">Accueil</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="Historique">Historique<span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="post" action="Historique">
            <input class="form-control mr-sm-2" type="search" value="<%= (value != null) ? value : "" %>" name="nom" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Heure</th>
            <th>Date</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
            <% for(Transaction h:historiques) {  %>
                <tr>
                    <td><%= h.getHeure() %></td>
                    <td><%= h.getDate() %></td>
                    <td><%= h.getTexte() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
