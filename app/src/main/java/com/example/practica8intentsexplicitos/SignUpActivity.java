package com.example.practica8intentsexplicitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUser = null;
    private EditText editTextPassword = null;
    private EditText editTextRepeatPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUser = (EditText) findViewById(R.id.signUp_EditTextUser);
        editTextPassword = (EditText) findViewById(R.id.signUp_EditTextPassword);
        editTextRepeatPassword = (EditText) findViewById(R.id.signUp_EditTextRepeatPassword);

        Button buttonCancel = (Button) findViewById(R.id.signUp_ButtonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button buttonSignUp = (Button) findViewById(R.id.signUp_ButtonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextFilled()) {
                    if (passwordMatch()) {
                        Intent returnToMainActivityIntent = new Intent(SignUpActivity.this, MainActivity.class);
                        returnToMainActivityIntent.putExtra("USER", editTextUser.getText().toString());
                        returnToMainActivityIntent.putExtra("PASSWORD", editTextPassword.getText().toString());
                        setResult(RESULT_OK, returnToMainActivityIntent);
                        finish();
                    } else {
                        Toast toastPasswordNotMatch = Toast.makeText(getApplicationContext(), getText(R.string.error_sign_up_password_not_match), Toast.LENGTH_SHORT);
                        toastPasswordNotMatch.show();
                    }
                } else {
                    Toast toastEditTextNotFilled = Toast.makeText(getApplicationContext(), getText(R.string.error_sign_up_not_filled), Toast.LENGTH_SHORT);
                    toastEditTextNotFilled.show();
                }
            }
        });
    }

    public boolean editTextFilled() {
        return !editTextUser.getText().toString().equals("") && !editTextPassword.getText().toString().equals("") && !editTextRepeatPassword.getText().toString().equals("");
    }

    public boolean passwordMatch() {
        return editTextPassword.getText().toString().equalsIgnoreCase(editTextRepeatPassword.getText().toString());
    }
}
