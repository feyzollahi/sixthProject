package Page;
import static j2html.TagCreator.*;
class HTMLTr {
    public static String generateTr(String projectId, String projectTitle, long budget){

        return tr(
                th(projectId),
                th(projectTitle),
                th(String.valueOf(budget))

        ).render();
    }
}
