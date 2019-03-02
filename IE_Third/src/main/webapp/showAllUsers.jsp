<%@ page import="model.Repo.GetRepo" %>
<%@ page import="model.User.User" %>
<%@ page import="model.Repo.UsersRepo" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mahdi
  Date: 3/1/19
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(!GetRepo.isSetRepo){
        GetRepo.print("not setRepo showAllUsers.jsp");
        request.getRequestDispatcher("").forward(request, response);
    }
%>
<html>
<head>
    <title>مشاهده تمام کاربران</title>
</head>
<body>
<%GetRepo.print("showAllUsers.jsp");
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
    User loginUser = (User) request.getAttribute("loginUser");
%>


<form action="showAllProjects" method="GET">
    <button>مشاهده تمام پروژه ها</button>
</form>
<form action="homeServlet" method="GET">
    <button>مشاهده صفحه اول</button>
</form>
<form action="userOwnPage.jsp" method="GET">
    <button>پروفایل</button>
</form>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>jobTitle</th>
        <th>مشاهده</th>
    </tr>
    <%for(User user: users){
        if(user.getId() != loginUser.getId()){%>
            <tr>
                <th><%=user.getId()%></th>
                <th><%=user.getFirstName() + " " + user.getLastName()%></th>
                <th><%=user.getJobTitle()%></th>
                <th>
                    <form action="showSpecifiedUserCtrl" method="GET">
                        <input type="hidden" name="userId" value="<%= user.getId()%>"/>
                        <button>مشاهده</button>
                    </form>
                </th>
            </tr>
        <%}%>
    <%}%>
</table>
</body>
</html>
