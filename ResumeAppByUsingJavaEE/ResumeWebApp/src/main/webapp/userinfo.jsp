<%@ page import="com.company.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 6/9/2021
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <title>Information</title>
</head>
<%
    User u = (User) request.getAttribute("user");

%>
<body class="table distance">
<div>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Address</th>
            <th>Profile Description</th>
            <th>Phone</th>
            <th>E-mail</th>
            <th>Birth date</th>
            <th>Nationality</th>
        </tr>
        </thead>
        <tbody>


        <td><%=u.getName()%>
        </td>
        <td><%=u.getSurname()%>
        </td>
        <td><%=u.getAddress()%>
        </td>
        <td><%=u.getProfileDesc()%>
        </td>

        <td><%=u.getPhone()%>
        </td>

        <td><%=u.getEmail()%>
        </td>

        <td><%=u.getBirthDate()%>
        </td>
        <td><%=u.getNationality()%>
        </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
