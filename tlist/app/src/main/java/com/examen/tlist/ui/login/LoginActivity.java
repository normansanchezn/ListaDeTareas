package com.examen.tlist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.examen.tlist.R;
import com.examen.tlist.databinding.ActivityLoginBinding;
import com.examen.tlist.databinding.DialogTemplateBinding;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.ui.registeruser.RegisterUserActivity;
import com.examen.tlist.utils.ToolBox;

public class LoginActivity extends AppCompatActivity {
    private String mail, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            final ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
            getSupportActionBar().hide();

            setListeners(activityLoginBinding);
        }

    private void setListeners(final ActivityLoginBinding activityLoginBinding) {
        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = activityLoginBinding.etEmailLogin.getText().toString();
                password = activityLoginBinding.etPasswordLogin.getText().toString();

                if (mail.isEmpty() && password.isEmpty()) {
                    ToolBox.showToast(getApplicationContext(), getResources().getString(R.string.error_void_fields));
                } else {
                    FirebaseServices.loginWithFirebaseAuth(getBaseContext(), mail, password);
                }
            }
        });

        activityLoginBinding.tvSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }
}