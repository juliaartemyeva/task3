<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Web Application</title>
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
            <a href="http://localhost:8080/" class="navbar-brand"> User Management App </a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <form action="/" method="post">

                <caption>
                    <h2>
                        Please sign in to your account
                    </h2>

                </caption>

                <fieldset class="form-group">
                    <label>User Login</label> <input type="text"
                                                     value="<c:out value='${user.login}' />" class="form-control"
                                                     name="login" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Password</label> <input type="password"
                                                   value="<c:out value='${user.password}' />" class="form-control"
                                                   name="password" required="required">
                </fieldset>
                <button type="submit" class="btn btn-success">Login</button>
                    <c:if test="${status != null}">
                <h7>
                   No user with such login and password was found! Please try again!
                </h7>
                    </c:if>
            </form>


        </div>
    </div>
</div>

</body>
</html>
