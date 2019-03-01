<%@ page import="model.Repo.UsersRepo" %>
<%@ page import="model.User.User" %>

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
<meta charset="UTF-8">
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
                    <input type="hidden" name="id" value="<%= user.getId()%>"/>
                    <button>login</button>
                </form>
            </th>
        </tr>
    <%}%>
</table>
</body>
</html>