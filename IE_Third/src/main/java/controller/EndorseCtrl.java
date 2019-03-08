package controller;

import model.Exceptions.UserNotFound;
import model.Exceptions.UserSkillNotFound;
import model.Repo.GetRepo;
import model.Repo.UsersRepo;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/endorseCtrl")
public class EndorseCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillName = request.getParameter("skillName");
        String userId = request.getParameter("userId");
        User loginUser = UsersRepo.getInstance().getLoginUser();
        User user = null;
        try {
            user = UsersRepo.getInstance().getUserById(userId);
            user.addEndorserToSkills(skillName, loginUser);
        } catch (UserNotFound | UserSkillNotFound userNotFound) {
            userNotFound.printStackTrace();
            GetRepo.print("Exception EndorserCtrl");
        }

        request.setAttribute("endorseMsg","skill " + "\"" + skillName + "\"" + " of user " + user.getFirstName()
                + " " + user.getLastName() + " was endoresed by user " + loginUser.getFirstName()
                + " " + loginUser.getLastName());
        request.getRequestDispatcher("jsp/userGuestPage.jsp").forward(request, response);
    }
}
