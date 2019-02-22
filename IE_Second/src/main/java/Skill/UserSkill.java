package Skill;

import User.User;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSkill extends Skill {
    public UserSkill(JSONObject data) {
        this.setName(data.get("name").toString());
        this.tempSetEndorsedCount((Long) data.get("endorsedCount"));

        this.endorsers = new HashMap<String, User>();
    }
    public UserSkill(String name){
        this.setName(name);
        this.endorsers = new HashMap<String, User>();
    }
    HashMap<String,User> endorsers;

    public int getEndorsedCount() {
        return this.endorsers.size();
    }
    long tempEndorsedCount;
    public void tempSetEndorsedCount(long endorsedCount) {
        this.tempEndorsedCount = tempEndorsedCount;
    }
    public long tempGetEndorsedCount(){
        return this.tempEndorsedCount;
    }

    public ArrayList<User> getEndorsers() {
        return new ArrayList<User>(this.endorsers.values());
    }

    public void setEndorsers(ArrayList<User> endorsers) {
        for(User endorser: endorsers){
            this.endorsers.put(endorser.getId(), endorser);
        }
    }

    public void addEndorser(User endorser){
        this.endorsers.put(endorser.getId(), endorser);
    }
}
