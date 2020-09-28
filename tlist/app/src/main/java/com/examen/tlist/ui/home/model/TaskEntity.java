package com.examen.tlist.ui.home.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_task")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title_task")
    private String titleOfTask;
    @ColumnInfo(name = "date_task")
    private String dateOfTask;
    @ColumnInfo(name = "verify_task")
    private Boolean verifyOfTask;

    public TaskEntity() {
    }

    public TaskEntity(String titleOfTask, String dateOfTask, Boolean verifyOfTask) {
        this.titleOfTask = titleOfTask;
        this.dateOfTask = dateOfTask;
        this.verifyOfTask = verifyOfTask;
    }

    public int getId() {
        return id;
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
