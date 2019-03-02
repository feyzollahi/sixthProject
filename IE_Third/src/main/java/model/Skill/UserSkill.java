package model.Skill;

import model.User.User;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSkill extends Skill {
    public UserSkill(JSONObject data) {
        this.setName(data.get("name").toString());
        this.initialEndorsedCount = (Long) data.get("endorsedCount");

        this.endorsers = new HashMap<String, User>();
    }
    public UserSkill(String name){
        this.setName(name);
        this.endorsers = new HashMap<String, User>();
    }
    private HashMap<String,User> endorsers;
    private long initialEndorsedCount;
    public long getEndorsedCount() {
        return this.endorsers.size() + initialEndorsedCount;
    }

    public boolean isEndorser(String userId){
        return this.endorsers.get(userId) != null;
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
