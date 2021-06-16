<%-- 
    Document   : user
    Created on : May 28, 2021, 6:25:06 PM
    Author     : dell
--%>

<%@page import="com.company.entity.User" %>
<%@page import="com.company.main.Context" %>
<%@page import="com.company.dao.inter.UserDaoInter" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/users.css">
</head>
<body>

<%
    User u = (User) request.getAttribute("user");
%>
<div class="container ">

    <div class="row">
        <div class="col-4">
            <form action="userdetail" method="POST">
                <div class="form-group">
                    <input class="form-control " type="hidden" name="id" value="<%=u.getId()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="name"> Name: </label>
                    <input class="form-control" type="text" name="name" value="<%=u.getName()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="surname"> Surname:</label>
                    <input class="form-control" type="text" name="surname" value="<%=u.getSurname()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="address"> Address: </label>
                    <input class="form-control" type="text" name="address" value="<%=u.getAddress()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="phone"> Phone: </label>
                    <input class="form-control" type="text" name="phone" value="<%=u.getPhone()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="email"> Email: </label>
                    <input class="form-control" type="text" name="email" value="<%=u.getEmail()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <label for="birthdate"> Birth Date: </label>
                    <input class="form-control" type="date" name="birthdate" value="<%=u.getBirthDate()%>"/>
                </div>
                <div class="form-group control_panel_group">
                    <input class="form-control btn-success" type="submit" name="save" value="Save"/>
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="action" value="info"/>


                    <br/>
                </div>

            </form>
        </div>
    </div>

</body>
</html>
