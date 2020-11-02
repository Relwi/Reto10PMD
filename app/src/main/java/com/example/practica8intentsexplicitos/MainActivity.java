package com.example.practica8intentsexplicitos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<User> alUser = new ArrayList<>();

    private EditText editTextUser = null;
    private EditText editTextPassword = null;

    public static final int SIGNUP_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Insertamos los usuarios user y admin
        alUser.add(new User("user", "user"));
        alUser.add(new User("admin", "admin"));

        editTextUser = (EditText) findViewById(R.id.main_EditTextUser);
        editTextPassword = (EditText) findViewById(R.id.main_EditTextPassword);

        Button buttonSignIn = (Button) findViewById(R.id.main_ButtonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextFilled() && userExist()) {
                    Intent signInIntent = new Intent(MainActivity.this, DashboardActivity.class);
                    signInIntent.putExtra("USER", editTextUser.getText().toString());
                    startActivity(signInIntent);
                } else {
                    Toast toastEditTextNotFilled = Toast.makeText(getApplicationContext(), getText(R.string.error_sign_in_not_filled), Toast.LENGTH_SHORT);
                    toastEditTextNotFilled.show();
                }
            }
        });
        Button buttonSignUp = (Button) findViewById(R.id.main_ButtonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(signUpIntent, SIGNUP_ACTIVITY);
            }
        });
    }

    public boolean editTextFilled() {
        return !editTextUser.getText().toString().equals("") && !editTextPassword.getText().toString().equals("");
    }

    public boolean userExist() {
        if (!(alUser.isEmpty())) {
            for (User user : alUser) {
                if (user.getUser().equals(editTextUser.getText().toString()) && user.getPassword().equals(editTextPassword.getText().toString()))
                    return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNUP_ACTIVITY && resultCode == RESULT_OK) {
            alUser.add(new User(data.getStringExtra("USER"), data.getStringExtra("PASSWORD")));

            Toast toastUserSignUpCorrect = Toast.makeText(getApplicationContext(), getText(R.string.user_created_successfully), Toast.LENGTH_SHORT);
            toastUserSignUpCorrect.show();
        }
    }
}