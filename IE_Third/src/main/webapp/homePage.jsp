<%@ page import="model.Repo.UsersRepo" %>
<%@ page import="model.User.User" %>
<%@ page import="model.Repo.GetRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
        }
        td {
            min-width: 300px;
            margin: 5px 5px 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<%GetRepo.print("homePage.jsp");%>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>jobTitle</th>
        <th>login</th>
    </tr>
    <%for(User user: UsersRepo.getInstance().getAllUsers()){%>
        <tr>
            <th><%=user.getId()%></th>
            <th><%=user.getFirstName() + " " + user.getLastName()%></th>
            <th><%=user.getJobTitle()%></th>
            <th>
                <form action="homePageCtrl" method="GET">
                    <input type="hidden" name="userId" value="<%= user.getId()%>"/>
                    <button>login</button>
                </form>
            </th>
        </tr>
    <%}%>
</table>
</body>
</html>