package Repo;

import Exceptions.UserNotFound;
import User.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersRepo {
    private static UsersRepo singleUsersRepo = null;
    private UsersRepo(){
        this.users = new HashMap<String, User>();
    }
    public static UsersRepo getInstance(){
        if(singleUsersRepo == null)
            singleUsersRepo = new UsersRepo();
        return singleUsersRepo;
    }
    private HashMap<String, User> users;
    public boolean isUserLogin(String userId){
        if(users.get(userId) == null){
            return false;
        }
        else
            return users.get(userId).isLogin();
    }
    public ArrayList<User> getLoginUsers(){
        ArrayList<User> loginUsers =  new ArrayList<User>();
        for(User user:this.users.values()){
            if(user.isLogin())
                loginUsers.add(user);
        }
        return loginUsers;
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

}
