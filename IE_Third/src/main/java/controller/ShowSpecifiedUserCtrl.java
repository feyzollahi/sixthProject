package controller;

import model.Exceptions.UserNotFound;
import model.Repo.GetRepo;
import model.Repo.UsersRepo;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showSpecifiedUserCtrl")
public class ShowSpecifiedUserCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!GetRepo.isSetRepo){
            GetRepo.print("not setRepo ShowSpecifiedUserCtrl");
            request.getRequestDispatcher("home").forward(request, response);
        }
        GetRepo.print("showSpecifiedUserCtrl");
        String userId = request.getParameter("userId");
        try {
            User user = UsersRepo.getInstance().getUserById(userId);
            GetRepo.print(UsersRepo.getInstance().getLoginUser().getFirstName() + UsersRepo.getInstance().getLoginUser().getLastName() + "is login");
            GetRepo.print("user " + user.getFirstName() + " " + user.getLastName() + " is login" + user.isLogin());
            if(user.isLogin()){
                request.getRequestDispatcher("jsp/userOwnPage.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("jsp/userGuestPage.jsp").forward(request, response);
            }
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
    }
}
