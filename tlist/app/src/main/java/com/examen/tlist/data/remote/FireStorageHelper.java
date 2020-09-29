package com.examen.tlist.data.remote;

import android.content.Context;

import com.examen.tlist.data.model.TaskEntity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FireStorageHelper {
    private static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static void createTaskRemote(Context context, TaskEntity taskEntity, String user){
        HashMap<String, Object> task = new HashMap<>();
        task.put("id", taskEntity.getId());
        task.put("titleOfTask", taskEntity.getTitleOfTask());
        task.put("dateOfTask", taskEntity.getDateOfTask());
        task.put("verifyOfTask", taskEntity.getVerifyOfTask());

        firebaseFirestore.collection(user).document().set(task);
    }

}
