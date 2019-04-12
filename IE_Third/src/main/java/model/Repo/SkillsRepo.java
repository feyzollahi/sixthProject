package model.Repo;

import model.Skill.Skill;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SkillsRepo {
    private static final String skillRepoUrlText = "http://142.93.134.194:8000/joboonja/skill";

    private static SkillsRepo skillsRepo = null;
    private SkillsRepo(){
        this.skills = new ArrayList<Skill>();
    }
    public static SkillsRepo getInstance(){
        if(skillsRepo == null){
            skillsRepo = new SkillsRepo();
        }
        return skillsRepo;
    }
    private ArrayList<Skill> skills;


    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
    public void setRepo() throws Exception {
        Object obj = GetRepo.getHTML(skillRepoUrlText);
        JSONArray skillJsonArr = (JSONArray) obj;
        for(Object skillObj: skillJsonArr){
            this.addSkill(new Skill((JSONObject)skillObj));
        }
    }
}
