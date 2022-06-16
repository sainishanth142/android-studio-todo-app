package com.to_do_app.to_do;

public class welcomeintromodel {
    String heading,description;
    int imagelink;

    public welcomeintromodel(int imagelink, String heading, String description) {
        this.imagelink = imagelink;
        this.heading = heading;
        this.description = description;
    }

    public int getImagelink() {
        return imagelink;
    }

    public void setImagelink(int imagelink) {
        this.imagelink = imagelink;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
