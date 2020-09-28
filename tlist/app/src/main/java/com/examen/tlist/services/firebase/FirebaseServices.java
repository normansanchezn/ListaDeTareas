package com.examen.tlist.services.firebase;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.examen.tlist.R;
import com.examen.tlist.ui.home.HomeActivity;
import com.examen.tlist.utils.ToolBox;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseServices {
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static void loginWithFirebaseAuth(final Context context, String mail, String password){
        firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent goHome = new Intent(context, HomeActivity.class);
                    goHome.putExtra("email", task.getResult().getUser().getEmail());
                    goHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(goHome);
                } else {
                    ToolBox.showToast(context, context.getResources().getString(R.string.error_register));
                }
            }
        });
    }

    public static void createUserWithFirebaseAuth(final Context context, String mail, String password){
        firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    ToolBox.showToast(context, context.getResources().getString(R.string.register_done));
                    Intent goHome = new Intent(context, HomeActivity.class);
                    goHome.putExtra("email", task.getResult().getUser().getEmail());
                    goHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(goHome);
                } else {
                    ToolBox.showToast(context, context.getResources().getString(R.string.error_register));
                }
            }
        });
    }

    public static void closeSession(){
        firebaseAuth.signOut();
    }
}
