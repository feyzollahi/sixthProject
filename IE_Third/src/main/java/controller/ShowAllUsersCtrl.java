package controller;

import model.Repo.UsersRepo;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showAllUsersCtrl")
public class ShowAllUsersCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = UsersRepo.getInstance().getAllUsers();
        request.setAttribute("users", users);
        request.setAttribute("loginUser", UsersRepo.getInstance().getLoginUser());
        request.getRequestDispatcher("jsp/showAllUsers.jsp").forward(request, response);
    }
}
