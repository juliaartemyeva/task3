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
            <a href="http://localhost:8080/admin" class="navbar-brand"> Welcome To Admin Page </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<c:url value='/logout' />" class="nav-link">Logout My Account</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="${pageContext.request.contextPath}admin/update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="${pageContext.request.contextPath}admin/insert" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Login</label> <input type="text"
                                                        value="<c:out value='${user.login}' />" class="form-control"
                                                        name="login" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Password</label> <input type="text"
                                                         value="<c:out value='${user.password}' />" class="form-control"
                                                         name="password" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Country</label> <input type="text"
                                                           value="<c:out value='${user.country}' />" class="form-control"
                                                           name="country">
                    </fieldset>
                        <fieldset class="form-group">
                            <label>User Role</label> <input type="text"
                                                               value="<c:out value='${user.role}' />" class="form-control"
                                                               name="role" required="required">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Save</button>

                </form>
        </div>
    </div>
</div>
</body>
</html>
