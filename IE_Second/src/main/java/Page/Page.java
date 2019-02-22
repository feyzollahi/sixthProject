package Page;


import Exceptions.ProjectNotFound;
import Exceptions.UserNotFound;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface Page {
    public void HandleRequest(HttpExchange httpExchange) throws IOException, ProjectNotFound, UserNotFound;
}
