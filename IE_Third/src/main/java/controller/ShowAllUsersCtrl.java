package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Repo.UsersRepo;
import model.User.User;
import springController.UserSummaryData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showAllUsersCtrl")
public class ShowAllUsersCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = UsersRepo.getInstance().getAllUsers();
        ArrayList<UserSummaryData> allUSD = new ArrayList<>();
        for(User user: users){
            allUSD.add(new UserSummaryData(user.getId(), user.getFirstName(), user.getLastName(), user.getJobTitle()));
        }
        response.setHeader("Content-Type", "application/json; charset=UTF-8");

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(allUSD);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        response.setStatus(200);
        writer.flush();
        request.setAttribute("users", users);
        request.setAttribute("loginUser", UsersRepo.getInstance().getLoginUser());
        request.getRequestDispatcher("jsp/showAllUsers.jsp").forward(request, response);
    }
}
