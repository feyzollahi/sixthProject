package Page;

import Exceptions.UserNotFound;
import User.User;
import Repo.UsersRepo;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class SpecifiedUserShowPage implements Page {
    String userId = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException, UserNotFound {
        File htmlTemplateFile = new File("src/main/webapp/user_template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        UsersRepo usersRepo = UsersRepo.getInstance();
        User user = usersRepo.getUserById(this.userId);
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String jobTitle = user.getJobTitle();
        String bio = user.getBio();
        String id = user.getId();
        htmlString = htmlString.replace("$id", id);
        htmlString = htmlString.replace("$bio", bio);
        htmlString = htmlString.replace("$firstName", String.valueOf(firstName));
        htmlString = htmlString.replace("$lastName", lastName);
        htmlString = htmlString.replace("$jobTitle", jobTitle);

        String response = htmlString;

        httpExchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response.getBytes());
        } catch (Exception e) {
            Main.Main.print(e);
        }
        os.close();
    }
}
