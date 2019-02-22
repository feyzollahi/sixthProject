package Page;

import Exceptions.ProjectNotFound;
import Project.Project;
import Repo.ProjectsRepo;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class SpecifiedProjectShowPage implements Page {
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException, ProjectNotFound {
        File htmlTemplateFile = new File("src/main/webapp/project_single_template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        ProjectsRepo  projectsRepo = ProjectsRepo.getInstance();
        Project project = projectsRepo.getProjectById(this.projectId);
        String title = project.getTitle();
        String id = project.getId();
        long budget = project.getBudget();
        String description = project.getDescription();
        String imgSrcUrlText = project.getImageUrlText();
        htmlString = htmlString.replace("$title", title);
        htmlString = htmlString.replace("$id", id);
        htmlString = htmlString.replace("$budget", String.valueOf(budget));
        htmlString = htmlString.replace("$description", description);
        htmlString = htmlString.replace("$src", "\"" + imgSrcUrlText + "\"");
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
