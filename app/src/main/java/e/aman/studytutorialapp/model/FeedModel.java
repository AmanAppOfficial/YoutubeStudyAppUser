package e.aman.studytutorialapp.model;

public class FeedModel {
    String imagePath;
    String title;

    public FeedModel(String imagePath, String title) {
        this.imagePath = imagePath;
        this.title = title;
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
}
