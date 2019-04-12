package model.User;


import model.Bid.Bid;
import model.Exceptions.DupEndorse;
import model.Exceptions.SkillNotFound;
import model.Exceptions.UserSkillNotFound;
import model.Project.Project;
import model.Skill.ProjectSkill;
import model.Skill.UserSkill;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class User {
    public User(JSONObject jsonObject) {
        this.isLogin = false;
        this.bio = (String) jsonObject.get("bio");
        this.firstName = (String) jsonObject.get("firstName");
        this.lastName = (String) jsonObject.get("lastName");
        this.id = (String) jsonObject.get("id");
        this.jobTitle = (String) jsonObject.get("jobTitle");
        this.bio = (String) jsonObject.get("bio");
        this.skills = new HashMap<String, UserSkill>();
        this.bids = new HashMap<String, Bid>();
        JSONArray skills;
        skills = (JSONArray) jsonObject.get("skills");
        for (Object skill1 : skills) {
            UserSkill skill = new UserSkill((JSONObject) skill1);
            this.skills.put(skill.getName(), skill);
        }
    }
    private boolean isLogin;
    private String bio;
    private String firstName;
    private String lastName;
    private String id;
    private String jobTitle;
    private String profilePictureURLText;
    private HashMap<String, UserSkill> skills;
    private HashMap<String, Bid> bids;

    public boolean isUserApproprateForProject(Project project){
        for(ProjectSkill projectSkill:project.getSkills()){
            if(this.skills.get(projectSkill.getName()) == null
            || this.skills.get(projectSkill.getName()).getEndorsedCount() < projectSkill.getPoint()){
                return false;
            }
        }
        return true;
    }
    public boolean isLogin() {
        return isLogin;
    }
    public boolean hasSkill(String skillName){
        return this.skills.get(skillName) != null;
    }
    public void removeSkill(String skillName) throws SkillNotFound {
        if(this.skills.get(skillName) == null)
            throw new SkillNotFound();
        this.skills.remove(skillName);
    }
    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void login(){
        isLogin = true;
    }
    public void logout(){isLogin = false;}
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfilePictureURLText() {
        return profilePictureURLText;
    }

    public void setProfilePictureURLText(String profilePictureURLText) {
        this.profilePictureURLText = profilePictureURLText;
    }

    public HashMap<String, UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<String, UserSkill> skills) {
        this.skills = skills;
    }
    public void addBid(Bid bid){
        this.bids.put(bid.getProject().getId(), bid);
    }
    public void addSkill(UserSkill skill){
        this.skills.put(skill.getName(), skill);
    }
    public void addEndorserToSkills(String skillName, User endorser) throws UserSkillNotFound, DupEndorse {
        UserSkill skill = this.skills.get(skillName);
        if(skill == null)
            throw new UserSkillNotFound();
        skill.addEndorser(endorser);
    }
    public void deleteSkill(String skillName) throws SkillNotFound {
        if(this.skills.get(skillName) == null)
            throw new SkillNotFound();
        this.skills.remove(skillName);
    }
}
