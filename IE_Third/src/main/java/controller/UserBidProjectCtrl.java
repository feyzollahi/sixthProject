package controller;

import model.Bid.Bid;
import model.Exceptions.ProjectNotFound;
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
        } catch (ProjectNotFound projectNotFound) {
            projectNotFound.printStackTrace();
            response.setStatus(404, "project not found");
            return;
        }
        String bidAmount = request.getParameter("bidAmount");
        User user = UsersRepo.getInstance().getLoginUser();
        GetRepo.print("bidAmount = " + bidAmount);
        if(bidAmount == null || Integer.valueOf(bidAmount) < 0){
            request.setAttribute("BidErrorMsg", "Bid amount is not set since invalid value of bid amount.");
            response.setStatus(400);//bad request
            return;
        }

            Bid bid = new Bid(UsersRepo.getInstance().getLoginUser(), project, Integer.valueOf(bidAmount));
            if(!bid.isValid()){
                response.setStatus(400, "more than project budget");
            }
            else {
                GetRepo.print("bid user = " + bid.getBiddingUser().getLastName());
                project.addBid(bid);
                UsersRepo.getInstance().getLoginUser().addBid(bid);
                response.setStatus(200);

            }
    }
}
