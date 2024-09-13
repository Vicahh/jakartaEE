<%--
  Created by IntelliJ IDEA.
  User: vicah
  Date: 17/08/2024
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String erreur = (String)request.getAttribute("erreur");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%= erreur %>
</body>
</html>
