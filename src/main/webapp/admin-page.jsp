<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
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
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="row">
    <div class="container">
        <h3 class="text-center">All Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>admin/new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Login</th>
                <th>Password</th>
                <th>Country</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.country}"/></td>
                    <td><c:out value="${user.role}"/></td>

                    <td><a href="${pageContext.request.contextPath}admin/edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}admin/delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
