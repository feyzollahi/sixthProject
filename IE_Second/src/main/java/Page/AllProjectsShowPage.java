package Page;

import Exceptions.UserNotFound;
import Project.Project;
import Repo.ProjectsRepo;
import Repo.UsersRepo;
import User.User;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class AllProjectsShowPage implements Page {
    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException, UserNotFound {
        File htmlTemplateFile = new File("src/main/webapp/project_all_template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        ProjectsRepo projectsRepo = ProjectsRepo.getInstance();
        ArrayList<Project> allProjects = projectsRepo.getAllProjects();
        UsersRepo usersRepo = UsersRepo.getInstance();
        User user = usersRepo.tempGetLoginUser();

        StringBuilder trs = new StringBuilder();
        for(Project project: allProjects){
            if(project.isUserAppropriateForProject(user)) {
                String tr = HTMLTr.generateTr(project.getId(), project.getTitle(), project.getBudget());
                trs.append(tr).append("\n");
            }
        }

        htmlString = htmlString.replace("$trs", trs.toString());
        String response = htmlString;

        httpExchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response.getBytes());
        }catch (Exception e){
            Main.Main.print(e);
        }
        os.close();
    }
}
