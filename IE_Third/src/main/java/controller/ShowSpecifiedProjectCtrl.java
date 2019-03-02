package controller;

import model.Repo.GetRepo;
import model.Repo.ProjectsRepo;
import model.Repo.UsersRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showSpecifiedProjectCtrl")
public class ShowSpecifiedProjectCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetRepo.print("showSpecifiedProjectCtrl");
        String projectId = request.getParameter("projectId");
        request.removeAttribute("projects");
        try {
            request.setAttribute("project", ProjectsRepo.getInstance().getProjectById(projectId));
            request.setAttribute("userId", UsersRepo.getInstance().getLoginUser().getId());
            GetRepo.print("specifiedPro send");
            request.getRequestDispatcher("specifiedProject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
