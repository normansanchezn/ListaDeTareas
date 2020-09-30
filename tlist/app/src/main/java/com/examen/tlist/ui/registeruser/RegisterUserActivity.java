package com.examen.tlist.ui.registeruser;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.examen.tlist.R;
import com.examen.tlist.data.model.UserEntity;
import com.examen.tlist.databinding.ActivityRegisterUserBinding;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.utils.ToolBox;

import java.util.List;

public class RegisterUserActivity extends AppCompatActivity {

    private RegisterUserViewModel registerUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityRegisterUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        registerUserViewModel = new ViewModelProvider(this).get(RegisterUserViewModel.class);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_register));

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUserViewModel.registerUser(
                        getApplicationContext(),
                        binding.etMail.getText().toString(),
                        binding.etPasswordLogin.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}