package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.textadventuregame.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button aboutButton;
    Button helpButton;
    Button exitButton;
    TextView text;
    private ImageView castleImage;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupControls();
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    private void setupControls() {


        mediaPlayer = MediaPlayer.create(this, R.raw.mainwindow);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.start());

        castleImage = findViewById(R.id.castleImage);
        castleImage.setImageResource(R.drawable.castle_image);

        ImageView gifImageView = findViewById(R.id.backgroundGif);
        String gifUrl = "https://i.gifer.com/origin/ff/ff1b86f96f05b3af08de65a8cb3df2ea.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView);


        text = findViewById(R.id.appNameText);
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        text.setText("Dungeon Adventure");
        text.setTextColor(Color.WHITE);
        text.setTextSize(20);
        startButton = findViewById(R.id.buttonStart);
        startButton.setBackgroundColor(Color.GRAY);
        startButton.setTextSize(18);
        startButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        startButton.setOnClickListener(view -> {
            stopMusic();
            Intent aboutIntent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(aboutIntent);
        });
        aboutButton = findViewById(R.id.buttonAbout);
        aboutButton.setBackgroundColor(Color.GRAY);
        aboutButton.setTextSize(18);
        aboutButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        aboutButton.setOnClickListener(view -> {
            stopMusic();
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        });
        helpButton = findViewById(R.id.buttonHelp);
        helpButton.setBackgroundColor(Color.GRAY);
        helpButton.setTextSize(18);
        helpButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        helpButton.setOnClickListener(view -> {
            stopMusic();
            Intent aboutIntent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(aboutIntent);
        });
        exitButton = findViewById(R.id.exitBtn);
        exitButton.setBackgroundColor(Color.GRAY);
        exitButton.setTextSize(18);
        exitButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        exitButton.setOnClickListener(view -> {
            finishAffinity();
            stopMusic();
        });
    }
    protected void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }
}