package com.examen.tlist.ui.home.model;

public class TaskEntity {
    private String titleOfTask;
    private String dateOfTask;
    private Boolean verifyOfTask;

    public TaskEntity() {
    }

    public TaskEntity(String titleOfTask, String dateOfTask, Boolean verifyOfTask) {
        this.titleOfTask = titleOfTask;
        this.dateOfTask = dateOfTask;
        this.verifyOfTask = verifyOfTask;
    }

    public String getTitleOfTask() {
        return titleOfTask;
    }

    public void setTitleOfTask(String titleOfTask) {
        this.titleOfTask = titleOfTask;
    }

    public String getDateOfTask() {
        return dateOfTask;
    }

    public void setDateOfTask(String dateOfTask) {
        this.dateOfTask = dateOfTask;
    }

    public Boolean getVerifyOfTask() {
        return verifyOfTask;
    }

    public void setVerifyOfTask(Boolean verifyOfTask) {
        this.verifyOfTask = verifyOfTask;
    }
}
