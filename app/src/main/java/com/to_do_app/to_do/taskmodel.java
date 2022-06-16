package com.to_do_app.to_do;

public class taskmodel {
    String task,date;
    String id;
    Boolean slected;

    public taskmodel(String task, String date, String id, Boolean slected) {
        this.task = task;
        this.date = date;
        this.id = id;
        this.slected = slected;
    }
    public taskmodel(String task, String date, Boolean slected) {
        this.task = task;
        this.date = date;
        this.slected = slected;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSlected() {
        return slected;
    }

    public void setSlected(Boolean slected) {
        this.slected = slected;
    }
}
