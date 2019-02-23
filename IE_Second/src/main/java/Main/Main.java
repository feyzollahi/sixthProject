package Main;

import HttpHandlers.JobOonjaHttpHandler;
import Project.Project;
import Repo.ProjectsRepo;
import Repo.SkillsRepo;
import Repo.UsersRepo;
import Skill.Skill;
import User.User;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Files;

public class Main {
    private static final String projectRepoUrlText = "http://142.93.134.194:8000/joboonja/project";
    private static final String skillRepoUrlText = "http://142.93.134.194:8000/joboonja/skill";
    public static void print(Object o){
        System.out.println(o);
    }
    private static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    private static void setUsersRepo() throws IOException, ParseException {
        UsersRepo usersRepo = UsersRepo.getInstance();
        File userJsonFile = new File("src/main/resources/UserJson.txt");
        String userJsonStr = new String(Files.readAllBytes(userJsonFile.toPath()));
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        jsonObject = (JSONObject) parser.parse(userJsonStr);
        User user = new User(jsonObject);
        user.login();
        usersRepo.addUser(user);
    }
    private static void setProjectsRepo() throws Exception {
        String projectsStr = getHTML(projectRepoUrlText);
        JSONParser jsonParser = new JSONParser();
        JSONArray projectsJsonArray = (JSONArray) jsonParser.parse(projectsStr);

        ProjectsRepo projectsRepo = ProjectsRepo.getInstance();

        for (Object aProjectsJsonArray : projectsJsonArray) {
            JSONObject projectJsonObj = (JSONObject) aProjectsJsonArray;
            Project project = new Project(projectJsonObj);
            projectsRepo.addProject(project);
        }
    }
    private static void setSkillsRepo() throws Exception {
        SkillsRepo skillsRepo = SkillsRepo.getInstance();
        String skillsStr = getHTML(skillRepoUrlText);
        JSONParser jsonParser = new JSONParser();
        JSONArray skillsJsonArray = (JSONArray) jsonParser.parse(skillsStr);
        for (Object aSkillsJsonArray : skillsJsonArray) {
            JSONObject skillJsonObj = (JSONObject) aSkillsJsonArray;
            skillsRepo.addSkill(new Skill(skillJsonObj));
        }
    }
    private static void setRepos() throws Exception {

        setProjectsRepo();
        setSkillsRepo();
        setUsersRepo();
    }
    private static void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new JobOonjaHttpHandler());
        server.setExecutor(null);
        server.start();
    }
    public static void main(String[] args) throws Exception {
        setRepos();
        startServer();
    }
}
