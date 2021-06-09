<%@page import="com.company.entity.User" %>
<%@page import="com.company.main.Context" %>
<%@page import="com.company.dao.inter.UserDaoInter" %>
<%@page import="java.util.List" %>
<%@ page import="com.company.resume.controller.UserController" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>JSP Page</title>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-4">
            <form action="users.jsp" method="GET">
                <div class="form-group">
                    <label for="name"> Name: </label>
                    <input placeholder="Enter name" class="form-control" type="text" name="name" value=""/>
                </div>
                <div class="form-group">
                    <label for="surname"> Surname:</label>
                    <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
                </div>
                <input class="btn btn-primary" type="submit" name="search" value="Search"/>

            </form>
        </div>
        <hr>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Address</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
<%--                (List<User>) request.getAttribute("list")--%>
                <%for (User u :(List<User>)request.getAttribute("list")) {%>
                <tr>
                    <td><%=u.getName()%>
                    </td>
                    <td><%=u.getSurname()%>
                    </td>
                    <td><%=u.getAddress()%>
                    </td>
                    <td style: width="3px">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <input type="hidden" name="action" value="delete"/>
                            <button value="delete" type="submit" class="btn-danger">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                        </form>
                    </td>
                    <td style: width="3px">
                        <form action="userdetail" method="GET">
                            <input type="hidden" name="id" value="<%=u.getId()%>"/>
                            <input type="hidden" name="action" value="update"/>
                            <button type="submit" value="update" class="btn-success">
                                <i class="fas fa-pen"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
