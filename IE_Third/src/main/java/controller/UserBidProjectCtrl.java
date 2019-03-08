package controller;

import model.Bid.Bid;
import model.Project.Project;
import model.Repo.GetRepo;
import model.Repo.ProjectsRepo;
import model.Repo.UsersRepo;
import model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userBidProjectCtrl")
public class UserBidProjectCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("projectId");
        Project project = null;
        try {
            project = ProjectsRepo.getInstance().getProjectById(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String bidAmount = request.getParameter("bidAmount");
        User user = UsersRepo.getInstance().getLoginUser();
        GetRepo.print("bidAmount = " + bidAmount);
        if(bidAmount == null || Integer.valueOf(bidAmount) < 0){
            request.setAttribute("BidErrorMsg", "Bid amount is not set since invalid value of bid amount.");
            request.getRequestDispatcher("specifiedProject/specifiedProject.jsp").forward(request, response);
        }
        try {
            Bid bid = new Bid(UsersRepo.getInstance().getLoginUser(), project, Integer.valueOf(bidAmount));
            if(!bid.isValid()){
                request.setAttribute("BidInvalidMsg", "Bid amount is more than project budget.");
            }
            else {
                GetRepo.print("bid user = " + bid.getBiddingUser().getLastName());
                project.addBid(bid);
                UsersRepo.getInstance().getLoginUser().addBid(bid);
                request.setAttribute("BidMsg", "bid of user " + user.getFirstName() + " " + user.getLastName()
                        + " is set");
            }
            request.setAttribute("project", project);
            request.setAttribute("userId", UsersRepo.getInstance().getLoginUser().getId());

            request.getRequestDispatcher("specified/specifiedProject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
