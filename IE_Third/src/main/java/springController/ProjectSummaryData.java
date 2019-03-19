package springController;

public class ProjectSummaryData {
    String id, title;
    long budget;
    public ProjectSummaryData(String id, String title, long budget){
        this.id = id;
        this.title = title;
        this.budget = budget;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
}
