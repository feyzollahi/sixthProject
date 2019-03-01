package Bid;

import model.Exceptions.ProjectNotFound;
import model.Exceptions.UserNotFound;
import model.Project.Project;
import model.Repo.ProjectsRepo;
import model.Repo.UsersRepo;
import model.User.User;
import org.json.simple.JSONObject;

public class Bid {
    public Bid(JSONObject jsonObject) throws Exception {
        String userId = (String) jsonObject.get("userId");
        UsersRepo usersRepo = UsersRepo.getInstance();
        User user = usersRepo.getUserById(userId);
        if(user == null){
            throw new UserNotFound();
        }
        ProjectsRepo projectsRepo = ProjectsRepo.getInstance();
        String projectId = (String) jsonObject.get("projectId");
        Project project = projectsRepo.getProjectById(projectId);
        if(project == null){
            throw new ProjectNotFound();
        }
        this.bidAmount = (Integer) jsonObject.get("bidAmount");
        this.biddingUser = user;
        this.project = project;
    }
    private User biddingUser;
    private int bidAmount;
    private Project project;

    public User getBiddingUser() {
        return biddingUser;
    }

    public void setBiddingUser(User biddingUser) {
        this.biddingUser = biddingUser;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
