package com.examen.tlist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.examen.tlist.R;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.ui.home.utils.ToolBox;
import com.examen.tlist.ui.registeruser.RegisterUserActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvSingup;
    private EditText etMail, etPassword;
    private String mail, password;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Init variables
            btnLogin = findViewById(R.id.btnLogin);
            tvSingup = findViewById(R.id.tvSingup);
            etMail = findViewById(R.id.etEmailLogin);
            etPassword = findViewById(R.id.etPasswordLogin);

            firebaseAuth = FirebaseAuth.getInstance();

            setListeners();
        }

    private void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//                finish();
                mail = etMail.getText().toString();
                password = etPassword.getText().toString();

                if (mail.isEmpty() && password.isEmpty()) {
                    ToolBox.showToast(getApplicationContext(), getResources().getString(R.string.error_void_fields));
                } else {
                    FirebaseServices.loginWithFirebaseAuth(getBaseContext(), mail, password);
                }
            }
        });

        tvSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }
}