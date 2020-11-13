package com.example.practica8intentsexplicitos;

import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private ArrayList<String> alWordsEnglish = new ArrayList<>();
    private ArrayList<String> alWordsSpanish = new ArrayList<>();
    private TextView textView = null;
    private EditText editText = null;
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Limpiamos los ArrayList por si en la base de datos implementamos más palabras
        alWordsEnglish.clear();
        alWordsSpanish.clear();

        textView = (TextView) findViewById(R.id.dashboard_TextView);
        editText = (EditText) findViewById(R.id.dashboard_EditText);
        button = (Button) findViewById(R.id.dashboard_Button);
        editText.requestFocus();

        if (Locale.getDefault().getLanguage().equals("es")) {
            cursorWordsSpanish();
            a();
        } else {
            cursorWordsEnglish();
            a();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textView.getText().toString().equals(editText.getText().toString()))
                        a();
                }
            });
        }
    }

    private void cursorWordsEnglish() {
        Cursor cursorWordsEnglish = MainActivity.db.rawQuery("SELECT wordEnglish FROM words", null);
        while (cursorWordsEnglish.moveToNext()) {
            alWordsEnglish.add(cursorWordsEnglish.getString(cursorWordsEnglish.getColumnIndex("wordEnglish")));
        }
        cursorWordsEnglish.close();
    }

    private void cursorWordsSpanish() {
        Cursor cursorWordsSpanish = MainActivity.db.rawQuery("SELECT wordSpanish FROM words", null);
        while (cursorWordsSpanish.moveToNext()) {
            alWordsSpanish.add(cursorWordsSpanish.getString(cursorWordsSpanish.getColumnIndex("wordEnglish")));
        }
        cursorWordsSpanish.close();
    }

    private void a() {
        int a = (int) (Math.random() * (4 - 0 + 1) + 0);
        editText.getText().clear();
        textView.clearComposingText();
        textView.setText(alWordsEnglish.get(a));
    }
}
