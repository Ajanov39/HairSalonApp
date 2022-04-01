package com.ajanovski.hairsalonapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ajanovski.hairsalonapp.utils.AppHolder;

import ajanovski.hairsalonapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);


        enableButton(false);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkFields()){
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        };

        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUser(etUsername.getText().toString(), etPassword.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    dialog.setTitle(getString(R.string.wrong_credentials_title))
                            .setMessage(getString(R.string.wrong_credentials_message))
                            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    dialog.show();
                }
            }
        });

    }


    public boolean checkFields() {
        if(etUsername.getText().toString().length() > 5 &&
                etPassword.getText().toString().length() > 5 ) {
            return true;
        } else {
            return false;
        }
    }

    public void enableButton(boolean enable) {
        btnLogin.setEnabled(enable);
    }

    public boolean checkUser(String username, String password) {
        boolean userExists = false;

        for(User user : AppHolder.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                userExists = true;
            }
        }
        return userExists;
    }


}