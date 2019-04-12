package controller;

import model.Repo.GetRepo;
import model.Repo.ProjectsRepo;
import model.Repo.SkillsRepo;
import model.Repo.UsersRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

@WebServlet("/home")
public class homeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GetRepo.print("homeServlet");
            if(!GetRepo.isSetRepo) {
                GetRepo.setRepo();
                UsersRepo.getInstance().setLoginUser("1");//ali sharifzadeh
            }
            request.setAttribute("user", UsersRepo.getInstance().getLoginUser());
            request.getRequestDispatcher("jsp/homePage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
