package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.textadventuregame.R;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    TextView helpTextView;
    Button backButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        helpTextView = findViewById(R.id.HelpTextView);
        helpTextView.setTextSize(20);
        helpTextView.setTextColor(Color.WHITE);
        helpTextView.setText("If you have any issues while using the program, please contact our support team" +
                "\nsamonovv@oregonstate.edu");
        helpTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        backButton = findViewById(R.id.backBtnHelp);
        backButton.setBackgroundColor(Color.GRAY);
        backButton.setTextSize(18);
        backButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(HelpActivity.this, MainActivity.class);
            startActivity(aboutIntent);
        });
    }
}