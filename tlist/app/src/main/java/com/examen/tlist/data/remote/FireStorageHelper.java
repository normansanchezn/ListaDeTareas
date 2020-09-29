package com.examen.tlist.data.remote;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.examen.tlist.data.model.TaskEntity;
import com.examen.tlist.utils.ToolBox;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FireStorageHelper {
    private static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static void createTaskRemote(final Context context, TaskEntity taskEntity, String user){
        HashMap<String, Object> task = new HashMap<>();
        task.put("id", taskEntity.getId());
        task.put("titleOfTask", taskEntity.getTitleOfTask());
        task.put("dateOfTask", taskEntity.getDateOfTask());
        task.put("verifyOfTask", taskEntity.getVerifyOfTask());

        firebaseFirestore.collection(user).document().set(task).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("Firestore", "isSuccess");
                ToolBox.showToast(context, "Se agregó a Firestore correctamente");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Firestore", e.getMessage());
            }
        });
    }

    public static List<TaskEntity> getTaskToDone(Context context, String user){
        final List<TaskEntity> taskEntities = new ArrayList<>();

        CollectionReference docRef = firebaseFirestore.collection(user);
        docRef.whereEqualTo("verifyOfTask", false)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            System.err.println("Listen failed:" + error);
                            return;
                        }

                        for (DocumentSnapshot doc : value) {
                            Log.i("TASK_TO_DONE", doc.getData().toString());
                            if (doc.getData() != null) {
                                taskEntities.add(new TaskEntity(
                                        doc.getString("titleOfTask"),
                                        doc.getString("dateOfTask"),
                                        doc.getBoolean("verifyOfTask")
                                ));
                            }
                        }
                    }
                });
        return taskEntities;
    }

}
