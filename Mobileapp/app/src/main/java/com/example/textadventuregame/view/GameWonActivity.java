package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.textadventuregame.R;

public class GameWonActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.winsound);
        mediaPlayer.start();

        TextView gameOverText = findViewById(R.id.victoryText);
        gameOverText.setTextColor(Color.WHITE);
        gameOverText.setText("You successfully killed all monsters and explored all rooms");
        gameOverText.setTextSize(25);
        gameOverText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ImageView gifImageView = findViewById(R.id.victoryGif);
        String gifUrl = "https://i.pinimg.com/originals/c0/d3/8c/c0d38c518fdbf6012e0475bb7a0598a5.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView);

        Button backToMenuButton = findViewById(R.id.victoryBackButton);
        backToMenuButton.setBackgroundColor(Color.GRAY);
        backToMenuButton.setTextSize(18);
        backToMenuButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backToMenuButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(GameWonActivity.this, MainActivity.class);
            startActivity(aboutIntent);
        });
    }
}