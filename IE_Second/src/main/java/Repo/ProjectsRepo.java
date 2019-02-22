package Repo;

import Exceptions.ProjectNotFound;
import Project.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectsRepo {
    private static ProjectsRepo singleProjectsRepo = null;
    private ProjectsRepo(){
        this.projects = new HashMap<String, Project>();
    }
    public static ProjectsRepo getInstance(){
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
    public ArrayList<Project> getAllProjects(){
        return new ArrayList<>(this.projects.values());
    }
    public void addProject(Project project){
        this.projects.put(project.getId(), project);
    }
}
