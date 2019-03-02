<%@ page import="model.Repo.UsersRepo" %>
<%@ page import="model.Exceptions.UserNotFound" %>
<%@ page import="model.User.User" %>
<%@ page import="model.Skill.UserSkill" %>
<%@ page import="model.Repo.GetRepo" %><%--
  Created by IntelliJ IDEA.
  User: mahdi
  Date: 3/1/19
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<%  String userId = request.getParameter("userId");
    User user = null;
    try {
        user = UsersRepo.getInstance().getUserById(userId);
    } catch (UserNotFound userNotFound) {
        userNotFound.printStackTrace();
    }
    if(request.getAttribute("endorseMsg") != null){
        GetRepo.print(request.getAttribute("endorseMsg"));
    }
%>
<form action="showAllUsersCtrl" method="GET">
    <button>مشاهده تمام کاربران</button>
</form>
<form action="showAllProjects" method="GET">
    <button>مشاهده تمام پروژه ها</button>
</form>
<form action="homeServlet" method="GET">
    <button>مشاهده صفحه اول</button>
</form>
<form action="userOwnPage.jsp" method="GET">
    <button>پروفایل</button>
</form>
<ul>
    <li>id: <%=user.getId()%></li>
    <li>first name: <%=user.getFirstName()%></li>
    <li>last name: <%=user.getLastName()%></li>
    <li>jobTitle: <%=user.getJobTitle()%></li>
    <li>bio: <%=user.getBio()%></li>
    <li>
        skills:
        <ul>
            <%for(UserSkill userSkill:user.getSkills().values()){%>
                <li>
                    <%=userSkill.getName()%>: <%=userSkill.getEndorsedCount()%>
                    <%if(!userSkill.isEndorser(UsersRepo.getInstance().getLoginUser().getId())){%>
                        <form action="endorseCtrl" method="GET">
                            <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                            <input type="hidden" name="skillName" value="<%=userSkill.getName()%>"/>
                            <button>Endorse</button>
                        </form>
                    <%}%>
                </li>
            <%}%>
            </li>
        </ul>
    </li>
</ul>
</body>
</html>
