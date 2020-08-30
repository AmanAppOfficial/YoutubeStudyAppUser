package e.aman.studytutorialapp.model;

import java.util.List;

public class UpcomingModel {

    String icon;
    String title;
    String desc;
    String author;
    List<String> topicsList;


    public UpcomingModel(String icon, String title, String desc, String author, List<String> topicsList) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.topicsList = topicsList;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTopicsList() {
        return topicsList;
    }

    public void setTopicsList(List<String> topicsList) {
        this.topicsList = topicsList;
    }
}
