package Page;
import static j2html.TagCreator.*;
public class HTMLTr {
    public static String generateTr(String projectId, String projectTitle, long budget){
        String tr = tr(
                th(projectId),
                th(projectTitle),
                th(String.valueOf(budget))

        ).render();

        return tr;
    }
}
