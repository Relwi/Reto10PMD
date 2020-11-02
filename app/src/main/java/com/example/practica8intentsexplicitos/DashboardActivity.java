package com.example.practica8intentsexplicitos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView textViewWelcome = (TextView) findViewById(R.id.dashboard_TextViewWelcome);

        Bundle extrasMainActivity = getIntent().getExtras();
        textViewWelcome.setText(textViewWelcome.getText().toString().concat(" ").concat(extrasMainActivity.getString("USER")));
    }
}
