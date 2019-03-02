package controller;

import model.Exceptions.SkillNotFound;
import model.Repo.GetRepo;
import model.Repo.UsersRepo;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSkill")
public class DeleteSkillCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetRepo.print("deleteSkill");
        User user = UsersRepo.getInstance().getLoginUser();
        String skillName = request.getParameter("userSkill");
        try {
            user.deleteSkill(skillName);
        } catch (SkillNotFound skillNotFound) {
            skillNotFound.printStackTrace();
        }
        request.setAttribute("deleteMsg","skill " + "\"" + skillName + "\"" + " was removed from user " + user.getFirstName()
        + " " + user.getLastName() + " skills list");
        request.getRequestDispatcher("userOwnPage.jsp").forward(request, response);

    }
}
