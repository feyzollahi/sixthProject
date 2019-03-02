package model.Repo;

import model.Exceptions.UserNotFound;
import model.User.User;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersRepo {
    private static final String usersRepoUrlText = "https://api.myjson.com/bins/171ppi";
    private static UsersRepo singleUsersRepo = null;
    private UsersRepo(){

        this.users = new HashMap<String, User>();
        loginUser = null;
    }
    public static UsersRepo getInstance(){
        if(singleUsersRepo == null)
            singleUsersRepo = new UsersRepo();
        return singleUsersRepo;
    }
    private HashMap<String, User> users;
    private User loginUser;
    public boolean isUserLogin(String userId){
        if(users.get(userId) == null){
            return false;
        }
        else
            return users.get(userId).isLogin();
    }
    public ArrayList<User> getAllUsers(){
        return new ArrayList<>(this.users.values());
    }
    public void setLoginUser(User user){
        this.loginUser = user;
    }
    public void setLoginUser(String userId) throws UserNotFound {
        User user = this.getUserById(userId);
        user.login();
        this.loginUser = user;
    }
    public void logOutAlUsers(){
        if(loginUser != null) {
            loginUser.logout();
            loginUser = null;
        }
    }
    public User getLoginUser(){
        return loginUser;
    }
    public User tempGetLoginUser() throws UserNotFound {
        return getUserById("1");
    }
    public void addUser(User user){
        users.put(user.getId(), user);
    }
    public User getUserById(String id) throws UserNotFound {
        User user = this.users.get(id);
        if(user == null)
            throw new UserNotFound();
        return users.get(id);
    }
    public void setRepo() throws Exception {
        UsersRepo usersRepo = UsersRepo.getInstance();

        JSONArray arr = (JSONArray) GetRepo.getHTML(usersRepoUrlText);
        for(Object obj: arr){
            this.addUser(new User((JSONObject) obj));
        }
    }
}
