package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.textadventuregame.R;

public class StartActivity extends AppCompatActivity {

    TextView text;
    Button newGame;
    Button loadGame;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setupControls();
        MusicPlayer.getInstance(this).startMusic();
    }

    @SuppressLint("SetTextI18n")
    private void setupControls() {
        ImageView gifImageView = findViewById(R.id.gifImageView);
        String gifUrl = "https://media.tenor.com/jP0qr_Ha7_MAAAAj/darksouls-knight.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView);

        text = findViewById(R.id.appNameTextStart);
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        text.setText("Dungeon Adventure");
        text.setTextColor(Color.WHITE);
        text.setTextSize(20);

        newGame = findViewById(R.id.newGameBtn);
        newGame.setBackgroundColor(Color.GRAY);
        newGame.setTextSize(18);
        newGame.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newGame.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(StartActivity.this, NewGameActivity.class);
            startActivity(aboutIntent);
            MusicPlayer.getInstance(this).pauseMusic();
        });
        loadGame = findViewById(R.id.loadGameBtn);
        loadGame.setBackgroundColor(Color.GRAY);
        loadGame.setTextSize(18);
        loadGame.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        loadGame.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(StartActivity.this, LoadGameActivity.class);
            startActivity(aboutIntent);
            MusicPlayer.getInstance(this).pauseMusic();

        });
        backBtn = findViewById(R.id.backBtnStart);
        backBtn.setBackgroundColor(Color.GRAY);
        backBtn.setTextSize(18);
        backBtn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backBtn.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(aboutIntent);
            MusicPlayer.getInstance(this).pauseMusic();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayer.getInstance(this).pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MusicPlayer.getInstance(this).startMusic();
    }
}