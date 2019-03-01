<%@ page import="model.Repo.UsersRepo" %>
<%@ page import="model.Repo.GetRepo" %>
<%@ page import="model.Skill.UserSkill" %>
<%@ page import="model.Skill.Skill" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Repo.SkillsRepo" %><%--

  Created by IntelliJ IDEA.
  User: mahdi
  Date: 3/1/19
  Time: 8:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(!GetRepo.isSetRepo){
        GetRepo.print("not setRepo userOwnPage.jsp");
        request.getRequestDispatcher("").forward(request, response);
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
</head>
<body>
<%System.out.println("userPage#######################3");%>
<%if(request.getAttribute("deleteMsg") != null){%>
    <h3 style="color: blue"><%=request.getAttribute("deleteMsg")%></h3>
<%}%>
<%if(request.getAttribute("addMsg") != null){%>
    <h3 style="color: blue"><%=request.getAttribute("addMsg")%></h3>
<%}%>
    <form action="showAllUsers.jsp" method="GET">
        <button>مشاهده تمام کاربران</button>
    </form>
    <ul>

        <li>id: <%=UsersRepo.getInstance().getLoginUser().getId()%></li>
        <li>first name: <%=UsersRepo.getInstance().getLoginUser().getFirstName()%></li>
        <li>last name: <%=UsersRepo.getInstance().getLoginUser().getLastName()%></li>
        <li>jobTitle: <%=UsersRepo.getInstance().getLoginUser().getJobTitle()%></li>
        <li>bio: <%=UsersRepo.getInstance().getLoginUser().getBio()%></li>
        <li>
            skills:
            <ul>
                <%for(UserSkill userSkill:UsersRepo.getInstance().getLoginUser().getSkills().values()){%>
                    <li>
                        <%=userSkill.getName()%>: <%=userSkill.getEndorsedCount()%>
                        <form action="deleteSkill" method="GET">
                            <input type="hidden" name="userSkill" value="<%=userSkill.getName()%>">
                            <button>Delete</button>
                        </form>
                    </li>
                <%}%>
            </ul>
        </li>
    </ul>
    Add Skill:
    <%ArrayList<Skill> skills = SkillsRepo.getInstance().getSkills();%>
    <form action="addSkillCtrl" method="GET">
        <select name="skillName">
            <%for(Skill skill: skills){
                if(UsersRepo.getInstance().getLoginUser().getSkills().get(skill.getName()) == null){
            %>
                <option value="<%=skill.getName()%>"><%=skill.getName()%></option>
                <%}
            }%>
        </select>
        <button>Add</button>
    </form>
<% GetRepo.print("userOwnPage Finished");
%>
</body>
</html>
