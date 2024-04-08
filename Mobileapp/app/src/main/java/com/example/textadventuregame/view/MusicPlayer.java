package com.example.textadventuregame.view;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.textadventuregame.R;

public class MusicPlayer extends MediaPlayer {
    public static MusicPlayer musicPlayer;
    private final MediaPlayer mediaPlayer;
    private MusicPlayer(Context ctx){
        mediaPlayer = MediaPlayer.create(ctx, R.raw.startwindow);
    }
    public static synchronized MusicPlayer getInstance(Context ctx) {
        if (musicPlayer == null) {
            musicPlayer = new MusicPlayer(ctx);
            musicPlayer.setOnCompletionListener(mp -> musicPlayer.start());
        }
        return musicPlayer;
    }
    public void startMusic() {
        if(!mediaPlayer.isPlaying()) mediaPlayer.start();
    }
    public void pauseMusic() {
        if(mediaPlayer.isPlaying()) mediaPlayer.pause();
    }
}
