<%-- 
    Document   : user
    Created on : Oct 28, 2020, 12:33:23 PM
    Author     : 808278
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        
        <h2>Users</h2>
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.email}</td>
                    <td><form action="" method="post"><input type="submit" name="action" value="Delete"><input type="hidden" name="user" value="${user.username}"></form></td>
                    <td><form action="" method="post"><input type="submit" name="action" value="Edit"><input type="hidden" name="user" value="${user.username}"></form></td>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Add User</h2>
        <form action="" method="post">
            Username: <input type="text" name="username" value="${user.username}"> <br>
            First Name: <input type="text" name="firstname" value="${user.firstname}"><br>
            Last Name: <input type="text" name="lastname" value="${user.lastname}"><br>
            Password: <input type="password" name="password" value="${user.password}"><br>
            Email: <input type="text" name="email" value="${user.email}"><br>
            <c:if test="${empty user}"><input type="submit" value="Add"><input type="hidden" name="action" value="Add"><br></c:if>
            <c:if test="${not empty user}"><input type="submit" value="Save"><input type="hidden" name="action" value="Save"><br></c:if>
        </form>
        
    </body>
</html>
