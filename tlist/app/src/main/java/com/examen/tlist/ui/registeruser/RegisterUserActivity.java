package com.examen.tlist.ui.registeruser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.examen.tlist.R;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.utils.ToolBox;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText etName, etLastName, etMail, etPassword, etRePassword;
    private Button btnRegister;
    private String textMail, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Init variables
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPasswordLogin);
        etRePassword = findViewById(R.id.etPasswordRepit);
        btnRegister = findViewById(R.id.btnRegister);

        setListeners();
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textMail = etMail.getText().toString();
                textPassword = etPassword.getText().toString();

                // TODO: Make all validations
                if (textPassword.isEmpty() && textMail.isEmpty()){
                    ToolBox.showToast(getApplicationContext(), getResources().getString(R.string.error_void_fields));
                } else {
                    FirebaseServices.createUserWithFirebaseAuth(getApplicationContext(), textMail, textPassword);
                }
            }
        });
    }
}