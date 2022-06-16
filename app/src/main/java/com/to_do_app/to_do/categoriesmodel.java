package com.to_do_app.to_do;

import java.util.ArrayList;

public class categoriesmodel {
    String text,tasks;
    int pos;
    Boolean selected;
    ArrayList<groupoftasksmodel> grouptasks;

    public ArrayList<groupoftasksmodel> getGrouptasks() {
        return grouptasks;
    }
    public void setGrouptasks(ArrayList<groupoftasksmodel> grouptasks) {
        this.grouptasks = grouptasks;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public categoriesmodel(String text, int pos, Boolean selected, ArrayList<groupoftasksmodel> grouptasks) {
        this.text = text;
        this.pos = pos;
        this.selected = selected;
        this.grouptasks = grouptasks;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
