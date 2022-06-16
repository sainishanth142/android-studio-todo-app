package com.to_do_app.to_do;

import java.util.ArrayList;

public class groupoftasksmodel {
    String title;
    ArrayList<taskmodel> tasks;

    public groupoftasksmodel(String title, ArrayList<taskmodel> tasks) {
        this.title = title;
        this.tasks = tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<taskmodel> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<taskmodel> tasks) {
        this.tasks = tasks;
    }
}
