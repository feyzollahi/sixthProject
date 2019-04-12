package controller;

import model.Repo.UsersRepo;
import model.Skill.UserSkill;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addSkill")
public class AddSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("addSkill-doPost");
        System.out.println(request.getParameter("name"));
        User loginUser = UsersRepo.getInstance().getLoginUser();
        String skillName = request.getParameter("name");
        if(loginUser.hasSkill(skillName)){
            response.setStatus(403);
            System.out.println("skillNotAdded");

        }
        else{
            UserSkill userSkill = new UserSkill(skillName);
            loginUser.addSkill(userSkill);
            response.setStatus(200);
            System.out.println("skillAdded");
            response.setHeader("Content-Type", "application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(skillName);
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
