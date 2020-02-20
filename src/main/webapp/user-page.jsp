<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>

    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #b3159b">
        <div>
            <a href="http://localhost:8080/logout" class="navbar-brand"> Logout </a>
        </div>
    </nav>

</header>

<h3 class="text-left">Hello ${sessionScope.login}!</h3>

</body>
</html>
