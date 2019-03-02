package model.Repo;

import model.Exceptions.ProjectNotFound;
import model.Project.Project;
import model.User.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectsRepo {
    private static final String projectRepoUrlText = "http://142.93.134.194:8000/joboonja/project";
    private static ProjectsRepo singleProjectsRepo = null;
    private ProjectsRepo() throws Exception {
        this.projects = new HashMap<String, Project>();
    }
    public static ProjectsRepo getInstance() throws Exception {
        if(singleProjectsRepo == null)
            singleProjectsRepo = new ProjectsRepo();
        return singleProjectsRepo;
    }
    private HashMap<String, Project> projects;
    public Project getProjectById(String id) throws ProjectNotFound {
        Project project = this.projects.get(id);
        if(project == null){
            throw new ProjectNotFound();
        }
        return project;
    }
    public ArrayList<Project> getProjectFilteredByUserSkills(User user){
        ArrayList<Project> projects = new ArrayList<Project>();
        for(Project project:this.projects.values()){
            if(project.isUserAppropriateForProject(user)){
                projects.add(project);
            }
        }
        return projects;
    }
    public ArrayList<Project> getAllProjects(){
        return new ArrayList<>(this.projects.values());
    }
    public void addProject(Project project){
        this.projects.put(project.getId(), project);
    }
    public void setRepo() throws Exception {
        Object prjObj = GetRepo.getHTML(projectRepoUrlText);
        JSONArray jsonPrjArr = (JSONArray) prjObj;
        for(Object prj: jsonPrjArr){
            JSONObject jsonPrj = (JSONObject) prj;
            Project project = new Project(jsonPrj);
            this.addProject(project);
        }
    }
}
