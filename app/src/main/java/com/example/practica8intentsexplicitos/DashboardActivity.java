package com.example.practica8intentsexplicitos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private ArrayList<String> alWordsEnglish = new ArrayList<>();
    private ArrayList<String> alWordsSpanish = new ArrayList<>();
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        alWordsEnglish.clear();
        alWordsSpanish.clear();
        textView = (TextView) findViewById(R.id.dashboard_TextView);

        if (Locale.getDefault().getLanguage().equals("en")) {
            cursorWordsEnglish();
            int a = (int) (Math.random() * (4 - 0 + 1) + 0);
            textView.setText(alWordsEnglish.get(a));
        } else if (Locale.getDefault().getLanguage().equals("es")) {
            cursorWordsSpanish();
        } // TODO: Si todo falla, volver al MainActiviy sacando un mensaje de error
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
}
