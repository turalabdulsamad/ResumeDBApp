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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title>JSP Page</title>
    <script type="text/javascript" src="assets/js/users.js">
    </script>

</head>
<body>
<% String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String address = request.getParameter("address");
UserDaoInter userDao = Context.instanceUserDao();
List<User> list = userDao.getAll(name,surname,address);%>

<div class="container">
    <div class="row">
        <div class="col-4">
            <form action="users.jsp" method="GET">
                <div class="form-group">
                    <label for="name"> Name: </label>

                    <input onkeyup="writeWhatIamTyping()" placeholder="Enter name" class="form-control" type="text"
                           name="name" value=""/>
                </div>
                <div class="form-group">
                    <label for="surname"> Surname:</label>
                    <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
                </div>
                <input visible="true" class="btn btn-primary" type="submit" name="search" value="Search"
                       id="btnsearch"/>
            </form>
        </div>
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
                <th></th>
            </tr>
            </thead>
            <tbody>

            <%
                for (User u :list) {
            %>
            <tr>
                <td><%=u.getName()%>
                </td>
                <td><%=u.getSurname()%>
                </td>
                <td><%=u.getAddress()%>
                </td>
                <td style: width="3px">
                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button value="delete" type="submit" class="btn-danger"
                            data-toggle="modal" data-target="#exampleModal" ONCLICK="deletebyId('<%=u.getId()%>')">
                        <i class="fas fa-trash-alt"></i>
                    </button>
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
                <td style: width="3px">
                    <form action="userinfo" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="info"/>
                        <button type="submit" value="info" class="btn-warning" style="color: white">
                            <i class="fas fa-info-circle"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="POST">
                    <input type="hidden" name="id" value="" id="idfordelete"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<div class="container">
    <form class="form-group" action="logout" method="POST">
        <input type="submit" value="Logout" name="logout" class="btn btn-primary logout"/>
        <input type="hidden" name="action" value="Logout"/>
    </form>
</div>


</body>
</html>
