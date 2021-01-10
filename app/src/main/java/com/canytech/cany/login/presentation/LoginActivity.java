package com.canytech.cany.login.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.canytech.cany.R;
import com.canytech.cany.common.view.LoadingButton;

public class LoginActivity extends AppCompatActivity {

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            findViewById(R.id.button_login_access).setEnabled(!s.toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private LoadingButton btnLogin;
    private Button btnRegister;
    private Button btnForgetPassword;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.button_login_access);
        btnRegister = findViewById(R.id.button_login_not_registered);
        btnForgetPassword = findViewById(R.id.button_login_forgot_password);
        editTextEmail = findViewById(R.id.edit_text_login_email);
        editTextPassword = findViewById(R.id.edit_text_login_password);

        editTextEmail.addTextChangedListener(watcher);
        editTextPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v -> {
            btnLogin.showProgress(true);

            new Handler().postDelayed(() -> {
                btnLogin.showProgress(false);
            }, 2000);

            editTextEmail.setError("Invalid Email");
            editTextPassword.setError("Invalid Password");
        });

    }

}