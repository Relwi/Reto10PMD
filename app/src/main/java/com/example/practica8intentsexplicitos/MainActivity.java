package com.example.practica8intentsexplicitos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {
    protected static SQLiteDatabase db = null;

    private EditText editTextUser = null;
    private EditText editTextPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creando base de datos
        db = openOrCreateDatabase("reto10", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (user VARCHAR, password VARCHAR, score INT)");

        // Insertamos los usuarios user y admin
        Cursor cursor = db.rawQuery("SELECT user, password FROM users", null);
        if (cursor.getCount() == 0) {
            db.execSQL("INSERT INTO users VALUES ('user', 'user', 0)");
            db.execSQL("INSERT INTO users VALUES ('admin', 'admin', 0)");
        }

        editTextUser = (EditText) findViewById(R.id.main_EditTextUser);
        editTextPassword = (EditText) findViewById(R.id.main_EditTextPassword);

        Button buttonSignIn = (Button) findViewById(R.id.main_ButtonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifEditTextFilled() && ifUserExistOnDatabase()) {
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
                startActivity(signUpIntent);
            }
        });
    }

    public boolean ifEditTextFilled() {
        return !editTextUser.getText().toString().equals("") && !editTextPassword.getText().toString().equals("");
    }

    public boolean ifUserExistOnDatabase() {
        Cursor cursor = db.rawQuery("SELECT user, password FROM users", null);
        while (cursor.moveToNext()) {
            if (editTextUser.getText().toString().equals(cursor.getString(0)) && editTextPassword.getText().toString().equalsIgnoreCase(cursor.getString(1)))
                return true;
        }
        return false;
    }
}