package com.examen.tlist.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.examen.tlist.data.model.TaskEntity;

import java.util.List;

@Dao
public interface LocalDao {
    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskEntity taskEntity);

    @Delete
    void deleteTask(TaskEntity taskEntity);

    @Query("UPDATE table_task SET verify_task = :verify WHERE title_task = :tarea")
    void updateTask(boolean verify, String tarea);

    @Query("SELECT * FROM table_task")
    List<TaskEntity> getAllTask();

    @Query("SELECT * FROM table_task WHERE verify_task = :verify")
    List<TaskEntity> getAllTaskDone(boolean verify);

    @Query("SELECT * FROM table_task WHERE verify_task = :verify")
    List<TaskEntity> getAllTaskToDone(boolean verify);

    @Query("UPDATE table_task SET title_task = :task WHERE title_task = :taskToEdit")
    void updateTask(String task, String taskToEdit);
}
