package HttpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class JobOonjaHttpHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().toString();
        String[] tokens = path.split("/");

        if(tokens.length > 2){
            if(tokens[1].equals("project"))
                new SpecifiedProjectShowHandler().handle(httpExchange);
            else if(tokens[1].equals("user")){
                new SpecifiedUserShowHandler().handle(httpExchange);
            }
        }
        else{
            if(tokens[1].equals("project")){
                new AllProjectShowHandler().handle(httpExchange);
            }
        }
    }
}
