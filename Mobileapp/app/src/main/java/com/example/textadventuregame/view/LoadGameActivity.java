package com.example.textadventuregame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.textadventuregame.R;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LoadGameActivity extends AppCompatActivity {
    private Button loadButton;
    private Button backButton;

    private LinearLayout linearScroll;

    private TextView lastSelectedTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        MusicPlayer.getInstance(this).startMusic();

        backButton = findViewById(R.id.loadGameBackButton);
        backButton.setBackgroundColor(Color.GRAY);
        backButton.setTextSize(18);
        backButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        backButton.setOnClickListener(v -> {
            Intent aboutIntent = new Intent(LoadGameActivity.this, StartActivity.class);
            startActivity(aboutIntent);
            MusicPlayer.getInstance(this).pauseMusic();
        });

        loadButton = findViewById(R.id.loadButton);
        loadButton.setBackgroundColor(Color.GRAY);
        loadButton.setTextSize(18);
        loadButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        linearScroll = findViewById(R.id.linearScroll);
        List<File> fileNames = listSavedGames();
        for (File filename: fileNames) {
            if(filename.getName().matches(".*[.]xml$")) addStringToScrollView(filename);
        }
    }
    public List<File> listSavedGames() {
        final File directory = this.getFilesDir();
        if (directory.isDirectory()) {
            return Arrays.asList(directory.listFiles());
        } else {
            System.out.println("Provided path is not a directory.");
            return null;
        }
    }
    public void addStringToScrollView(File filename) {
        TextView textView = new TextView(this);
        textView.setText(filename.getName());
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setPadding(16, 16, 16, 16);
        textView.setOnClickListener(v -> {
            if(lastSelectedTextView!=null){
                lastSelectedTextView.setBackgroundColor(0x3E0F0C);
            }
            lastSelectedTextView = textView;
            textView.setBackgroundColor(Color.GRAY);
            loadButton.setOnClickListener(v1 -> {
                Intent aboutIntent = new Intent(LoadGameActivity.this, GameActivity.class);
                aboutIntent.putExtra("parameter", filename);
                startActivity(aboutIntent);
                MusicPlayer.getInstance(this).pauseMusic();
            });
        });
        linearScroll.addView(textView);
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