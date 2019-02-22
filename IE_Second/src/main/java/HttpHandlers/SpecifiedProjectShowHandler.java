package HttpHandlers;

import Exceptions.ProjectNotFound;
import Page.SpecifiedProjectShowPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


class SpecifiedProjectShowHandler implements HttpHandler{

    public void handle(HttpExchange httpExchange) throws IOException {
        String projectId = "";
        try {
            SpecifiedProjectShowPage page = new SpecifiedProjectShowPage();
            String[] tokens = httpExchange.getRequestURI().toString().split("/");
            projectId = tokens[tokens.length - 1];
            page.setProjectId(projectId);
            page.HandleRequest(httpExchange);
        } catch (SecurityException | ProjectNotFound e) {
            e.printStackTrace();
            String response =
                    "<html>"
                            + "<body>project with id: \""+ projectId + "\" not found.</body>"
                            + "</html>";
            httpExchange.sendResponseHeaders(404, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
