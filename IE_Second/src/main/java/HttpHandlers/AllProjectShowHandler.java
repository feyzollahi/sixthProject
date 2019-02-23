package HttpHandlers;

import Exceptions.UserNotFound;
import Page.AllProjectsShowPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

class AllProjectShowHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            AllProjectsShowPage page = new AllProjectsShowPage();

            page.HandleRequest(httpExchange);
        } catch (SecurityException | UserNotFound e) {
            e.printStackTrace();
            String response =
                    "<html>"
                            + "<body>Page \""+ "project" + "\" not found.</body>"
                            + "</html>";
            httpExchange.sendResponseHeaders(404, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
