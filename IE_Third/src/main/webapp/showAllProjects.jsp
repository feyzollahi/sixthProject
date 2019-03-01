<%@ page import="model.Repo.GetRepo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Project.Project" %><%--
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
    <title>مشاهده تمام پروژه ها</title>
</head>
<body>
<%GetRepo.print("showAllProjects.jsp");
    ArrayList<Project> matchingProjects = (ArrayList<Project>) request.getAttribute("projects");
%>

<form action="showAllUsersCtrl" method="GET">
    <button>مشاهده تمام کاربران</button>
</form>
<form action="homeServlet" method="GET">
    <button>مشاهده صفحه اول</button>
</form>
<form action="userOwnPage.jsp" method="GET">
    <button>پروفایل</button>
</form>
<table>
    <tr>
        <th>id </th>
        <th>title</th>
        <th>budget</th>
        <th>مشاهده</th>
    </tr>
    <%for(Project project: matchingProjects){%>
        <tr>
            <th><%=project.getId()%></th>
            <th><%=project.getTitle()%></th>
            <th><%=project.getBudget()%></th>
            <th>
                <form action="showSpecifiedProjectCtrl" method="GET">
                    <input type="hidden" name="projectId" value="<%= project.getId()%>"/>
                    <button>مشاهده</button>
                </form>
            </th>
        </tr>
    <%}%>
</table>
</body>
</html>
