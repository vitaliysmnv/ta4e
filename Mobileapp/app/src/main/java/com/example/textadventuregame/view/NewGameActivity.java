package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.textadventuregame.R;
import com.google.android.material.textfield.TextInputEditText;

public class NewGameActivity extends AppCompatActivity {

    Button startNewGame;
    Button backBtn;
    TextInputEditText playerNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        setupControls();
    }

    @SuppressLint("SetTextI18n")
    private void setupControls() {
        MusicPlayer.getInstance(this).startMusic();

        ImageView gifImageView = findViewById(R.id.newGameGif);
        String gifUrl = "https://media.tenor.com/K7-ivtVTJ7YAAAAM/dnd-creature.gif";
        Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView);

        playerNameText = findViewById(R.id.playerNameInput);
        playerNameText.setHintTextColor(Color.WHITE);
        playerNameText.setTextSize(18);
        playerNameText.setTextColor(Color.WHITE);
        playerNameText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        playerNameText.setText("player");

        startNewGame = findViewById(R.id.startGameBtn);
        startNewGame.setBackgroundColor(Color.GRAY);
        startNewGame.setTextSize(18);
        startNewGame.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        startNewGame.setOnClickListener(view -> {
            if(playerNameText.getText()==null || playerNameText.getText().toString().length()<3){
                Toast.makeText(getApplicationContext(),"Name is too short", Toast.LENGTH_SHORT).show();
            } else if(!playerNameText.getText().toString().matches("[a-zA-Z]+")) {
                Toast.makeText(getApplicationContext(),"Only characters are allowed", Toast.LENGTH_SHORT).show();
            } else if(playerNameText.getText().toString().length()>15){
                Toast.makeText(getApplicationContext(),"Name is too long", Toast.LENGTH_SHORT).show();
            } else {
                Intent aboutIntent = new Intent(NewGameActivity.this, GameActivity.class);
                aboutIntent.putExtra("parameter", playerNameText.getText().toString());
                startActivity(aboutIntent);
                MusicPlayer.getInstance(this).pauseMusic();
            }
        });

        backBtn = findViewById(R.id.backBtnNewGame);
        backBtn.setBackgroundColor(Color.GRAY);
        backBtn.setTextSize(18);
        backBtn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backBtn.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(NewGameActivity.this, StartActivity.class);
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