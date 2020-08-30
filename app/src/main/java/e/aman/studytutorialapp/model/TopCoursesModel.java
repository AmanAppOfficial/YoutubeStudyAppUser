package e.aman.studytutorialapp.model;

public class TopCoursesModel
{
    String imagePath;
    String date;
    String title;
    String description;
    String author;

    public TopCoursesModel() {
    }

    public TopCoursesModel(String imagePath, String date, String title, String description, String author) {
        this.imagePath = imagePath;
        this.date = date;
        this.title = title;
        this.description = description;
        this.author = author;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
