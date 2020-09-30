package com.examen.tlist.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.examen.tlist.data.local.dao.LocalDao;
import com.examen.tlist.data.model.TaskEntity;
import com.examen.tlist.data.model.UserEntity;

@Database(entities = {UserEntity.class, TaskEntity.class}, version = 2, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    public static RoomDb roomDb;

    public synchronized static RoomDb getInstance(Context context, String nameDatabase) {
        if (roomDb == null){
            roomDb = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDb.class, nameDatabase)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDb;
    }

    public abstract LocalDao localDao();
}
