package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Exceptions.ProjectNotFound;
import model.Project.Project;
import model.Repo.GetRepo;
import model.Repo.ProjectsRepo;
import model.Repo.UsersRepo;
import model.User.User;
import springController.ProjectCompleteData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showSpecifiedProjectCtrl")
public class ShowSpecifiedProjectCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetRepo.print("showSpecifiedProjectCtrl");
        String projectId = request.getParameter("projectId");
        boolean isLegal = false;
        boolean exist = true;
        try {
            exist = ProjectsRepo.getInstance().getProjectById(projectId) != null;
            isLegal = ProjectsRepo.getInstance().getProjectById(projectId).isUserAppropriateForProject(UsersRepo.getInstance().getLoginUser());
        } catch (ProjectNotFound projectNotFound) {
            projectNotFound.printStackTrace();
            exist = false;
        }
        request.removeAttribute("projects");
        if(!exist){
            response.setStatus(404);
        }
        else {
            if (isLegal) {
                try {
                    request.setAttribute("project", ProjectsRepo.getInstance().getProjectById(projectId));
                    request.setAttribute("userId", UsersRepo.getInstance().getLoginUser().getId());
                    GetRepo.print("specifiedPro send");
                    response.setHeader("Content-Type", "application/json; charset=UTF-8");
                    Project project = ProjectsRepo.getInstance().getProjectById(projectId);
                    ProjectCompleteData projectCompleteData = new ProjectCompleteData(project.getId(), project.getTitle()
                            , project.getDescription(), project.getImageUrlText(), project.getBudget());
                    response.setStatus(200);

                    ObjectMapper om = new ObjectMapper();
                    String json = om.writeValueAsString(projectCompleteData);
                    PrintWriter writer = response.getWriter();
                    writer.print(json);
                    writer.flush();
//                    request.getRequestDispatcher("specifiedProject/specifiedProject.jsp").forward(request, response);

                } catch (ProjectNotFound projectNotFound) {
                    projectNotFound.printStackTrace();
                    response.setStatus(404);
                }
            } else {
                response.setStatus(403);
                User user = UsersRepo.getInstance().getLoginUser();
                request.setAttribute("forbiddenMsg", "Sorry!\n " + user.getFirstName() + " " + user.getLastName()
                        + " can not see the project! because he/she does not have enough skills for it.");
//            request.getRequestDispatcher("specifiedProject/specifiedProject.jsp").forward(request, response);

            }

        }
    }
}
