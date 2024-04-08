package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.textadventuregame.R;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView aboutTextView;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutTextView = findViewById(R.id.aboutTextView);
        aboutTextView.setTextSize(20);
        aboutTextView.setTextColor(Color.WHITE);

        aboutTextView.setText("Program has been created by the students of Oregon State University CS462 Team.\n Vitaliy Samonov, Harinder S Garcha, Hrishikash Kadakia, Nathan Cahoy \n \nversion: 1.0.0");
        aboutTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        backButton = findViewById(R.id.backBtnAbout);
        backButton.setBackgroundColor(Color.GRAY);
        backButton.setTextSize(18);
        backButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(aboutIntent);
        });
    }
}