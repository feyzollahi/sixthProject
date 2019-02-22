package HttpHandlers;

import Exceptions.BidNotFound;
import Exceptions.ProjectNotFound;
import Exceptions.UserNotFound;
import Page.SpecifiedUserShowPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class SpecifiedUserShowHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String userId = "";
        try {
            SpecifiedUserShowPage page = new SpecifiedUserShowPage();
            String[] tokens = httpExchange.getRequestURI().toString().split("/");
            userId = tokens[tokens.length - 1];
            page.setUserId(userId);
            page.HandleRequest(httpExchange);
        } catch (SecurityException | UserNotFound e) {
            e.printStackTrace();
            String response =
                    "<html>"
                            + "<body>Page \""+ userId + "\" not found.</body>"
                            + "</html>";
            httpExchange.sendResponseHeaders(404, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
