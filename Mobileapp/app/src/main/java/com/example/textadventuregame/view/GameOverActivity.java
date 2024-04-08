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

public class GameOverActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.losesound);
        mediaPlayer.start();

        TextView gameOverText = findViewById(R.id.gameOverText);
        gameOverText.setTextColor(Color.WHITE);
        gameOverText.setText("GAME OVER");
        gameOverText.setTextSize(25);
        gameOverText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ImageView gifImageView = findViewById(R.id.gameOverGif);
        String gifUrl = "https://i.imgur.com/hqKGxD0.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView);

        Button backToMenuButton = findViewById(R.id.defeatBtn);
        backToMenuButton.setBackgroundColor(Color.GRAY);
        backToMenuButton.setTextSize(18);
        backToMenuButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backToMenuButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(aboutIntent);
        });
    }
}