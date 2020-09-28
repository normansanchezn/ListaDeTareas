package com.examen.tlist.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.examen.tlist.data.local.model.TaskEntity;

import java.util.ArrayList;
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
}
