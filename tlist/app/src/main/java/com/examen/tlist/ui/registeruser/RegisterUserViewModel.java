package com.examen.tlist.ui.registeruser;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.examen.tlist.data.model.UserEntity;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.utils.ToolBox;

import java.util.List;

public class RegisterUserViewModel extends ViewModel {
    private MutableLiveData<List<UserEntity>> users;

    public LiveData<List<UserEntity>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<UserEntity>>();
        }
        return users;
    }

    public void registerUser(Context context, String correo, String password) {
        if (isValidMail(correo) && password.length() > 8){
            FirebaseServices.createUserWithFirebaseAuth(context, correo, password);
        } else {
            Log.i("register:::", "error");
        }
    }

    private boolean isValidMail(String correo) {
        return correo.contains("@");
    }
}
