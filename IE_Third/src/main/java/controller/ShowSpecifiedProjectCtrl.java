package controller;

import model.Repo.ProjectsRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "showSpecifiedProject")
public class ShowSpecifiedProject extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("projectId");
        request.removeAttribute("projects");
        try {
            request.setAttribute("project", ProjectsRepo.getInstance().getProjectById(projectId));
            request.getRequestDispatcher("specifiedProject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
