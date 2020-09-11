package e.aman.studytutorialapp.model;

public class CourseModel {

    String imagePath;
    String title;
    String countVideos;

    public CourseModel(String imagePath, String title, String countVideos) {
        this.imagePath = imagePath;
        this.title = title;
        this.countVideos = countVideos;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountVideos() {
        return countVideos;
    }

    public void setCountVideos(String countVideos) {
        this.countVideos = countVideos;
    }
}
