package com.example.textadventuregame.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textadventuregame.R;
import com.example.textadventuregame.controller.GameController;
import com.example.textadventuregame.model.items.Shield;

import java.io.File;

public class GameActivity extends AppCompatActivity implements RecyclerViewInterface{
    private GameController gameController;
    private MediaPlayer mediaPlayer;
    private ImageView locationImage;
    private TextView locationText;
    private TextView playerInfo;
    private ImageButton menuButton;
    private ImageButton inventoryButton;
    private ImageButton levelUpButton;
    private Button eventButton;
    private Button westButton;
    private Button eastButton;
    private Button northButton;
    private Button southButton;

    private ImageButton closeInventoryButton;
    private RecyclerView inventoryRecyclerview;
    private InventoryRecyclerViewAdapter adapter;
    private LinearLayout linearLayoutBattleLog;
    private ScrollView battleLogScrollView;
    private ImageButton closeBattleLogButton;
    private Button saveGameButton;
    private Button saveGameAndExitButton;
    private Button menuExitButton;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Object parameter = intent.getSerializableExtra("parameter");
        initData(parameter);
        setupControls();
        renderScreen();
    }

    private void initData(Object obj) {
        if(obj.getClass().equals(String.class)) {
            gameController = new GameController();
            gameController.createGameModel();
            gameController.gameStart((String) obj);
        } else if(obj.getClass().equals(File.class)){
            gameController = new GameController();
            gameController.loadGame((File) obj);
        }
    }

    private void setupControls() {
        mediaPlayer = MediaPlayer.create(this, R.raw.gamewindow);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.start());


        locationImage = findViewById(R.id.locationImage);

        locationText = findViewById(R.id.locationInfo);
        locationText.setTextSize(15);
        locationText.setTextColor(Color.WHITE);

        menuButton = findViewById(R.id.menuBtn);
        saveGameButton = findViewById(R.id.saveGameButton);
        saveGameButton.setBackgroundColor(Color.GRAY);
        saveGameButton.setTextSize(15);
        saveGameButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        disableButton(saveGameButton);
        saveGameAndExitButton = findViewById(R.id.saveGameAndExitButton);
        saveGameAndExitButton.setBackgroundColor(Color.GRAY);
        saveGameAndExitButton.setTextSize(15);
        saveGameAndExitButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        disableButton(saveGameAndExitButton);
        menuExitButton = findViewById(R.id.menuExitButton);
        menuExitButton.setBackgroundColor(Color.GRAY);
        menuExitButton.setTextSize(15);
        menuExitButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        disableButton(menuExitButton);
        menuButtonAction();

        playerInfo = findViewById(R.id.playerInfo);
        playerInfo.setTextColor(Color.YELLOW);
        playerInfo.setTextSize(15);
        playerInfo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        inventoryButton = findViewById(R.id.inventoryBtn);
        inventoryButton.setOnClickListener(view -> openInventory());

        levelUpButton = findViewById(R.id.levelUpBtn);
        levelUpButton.setOnClickListener(view -> {
            gameController.levelUp();
            renderLevelUpButton();
            renderPlayerInfo();
        });
        linearLayoutBattleLog = findViewById(R.id.battleLogList);
        linearLayoutBattleLog.setEnabled(false);
        linearLayoutBattleLog.setVisibility(View.INVISIBLE);

        battleLogScrollView = findViewById(R.id.battleLogScrollView);
        battleLogScrollView.setEnabled(false);
        battleLogScrollView.setVisibility(View.INVISIBLE);

        eventButton = findViewById(R.id.eventBtn);
        eventButton.setTextSize(15);
        eventButton.setTextColor(Color.WHITE);


        eastButton = findViewById(R.id.rightBtn);
        eastButton.setBackgroundColor(Color.GRAY);
        eastButton.setTextSize(15);
        eastButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        westButton = findViewById(R.id.leftBtn);
        westButton.setBackgroundColor(Color.GRAY);
        westButton.setTextSize(15);
        westButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        northButton = findViewById(R.id.downstairsBtn);
        northButton.setBackgroundColor(Color.GRAY);
        northButton.setTextSize(15);
        northButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        southButton = findViewById(R.id.returnBackBtn);
        southButton.setBackgroundColor(Color.GRAY);
        southButton.setTextSize(15);
        southButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        closeBattleLogButton = findViewById(R.id.closeBattleLogBtn);
        closeBattleLogButton.setEnabled(false);
        closeBattleLogButton.setVisibility(View.INVISIBLE);
        closeBattleLogButton.setOnClickListener(view->{
            closeBattleLogButton.setEnabled(false);
            closeBattleLogButton.setVisibility(View.INVISIBLE);
            battleLogScrollView.setEnabled(false);
            battleLogScrollView.setVisibility(View.INVISIBLE);
            linearLayoutBattleLog.setEnabled(false);
            linearLayoutBattleLog.setVisibility(View.INVISIBLE);
            renderControlButtons();
            inventoryButton.setEnabled(true);
        });

        closeInventoryButton = findViewById(R.id.closeInventoryBtn);
        closeInventoryButton.setEnabled(false);
        closeInventoryButton.setVisibility(View.INVISIBLE);
        closeInventoryButton.setOnClickListener(view->{
            closeInventoryButton.setEnabled(false);
            closeInventoryButton.setVisibility(View.INVISIBLE);
            inventoryRecyclerview.setEnabled(false);
            inventoryRecyclerview.setVisibility(View.INVISIBLE);
            renderControlButtons();
            eventButton.setEnabled(true);
        });

        inventoryRecyclerview = findViewById(R.id.inventoryRecyclerView);
        inventoryRecyclerview.setVisibility(View.INVISIBLE);
        inventoryRecyclerview.setEnabled(false);

        adapter = new InventoryRecyclerViewAdapter(this,
                gameController.getInventoryItems(),
                gameController.getEquippedSword(), gameController.getEquippedShield(), this);
        inventoryRecyclerview.setAdapter(adapter);
        inventoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void renderScreen() {
        renderLocationImage();
        renderLocationText();
        renderPlayerInfo();
        renderInventoryImage();
        renderLevelUpButton();
        renderEventButton();
        renderControlButtons();
    }



    private void renderLocationImage(){
        String imageFileName = gameController.getLocationImageName();
        int imageResourceId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
        locationImage.setImageResource(imageResourceId);
    }
    private void renderLocationText() {
        handler.removeCallbacksAndMessages(null);
        typingAnimation(gameController.getLocationText(), locationText);
        handler.sendMessage(handler.obtainMessage(0));
    }
    private void renderPlayerInfo() {
          playerInfo.setText(gameController.getPlayerInfoText());
    }
    private void renderInventoryImage() {
        inventoryButton.setImageResource(R.drawable.inventory_icon);
    }
    private void renderLevelUpButton() {
        levelUpButton.setImageResource(R.drawable.levelup);
        if(gameController.checkLevelUp()) {
            levelUpButton.setEnabled(true);
            levelUpButton.setFocusable(true);
            levelUpButton.setVisibility(View.VISIBLE);
        } else{
            levelUpButton.setEnabled(false);
            levelUpButton.setFocusable(false);
            levelUpButton.setVisibility(View.INVISIBLE);
        }
    }
    private void renderEventButton() {
        if(gameController.roomWithEvent()) {
            enableButton(eventButton);
            eventButton.setBackgroundColor(0xFF3E0F0C);
            eventButton.setText(gameController.roomEventHandleText());
            eventButton.setOnClickListener(view -> {
                gameController.doEvent();
                adapter.setItems(gameController.getInventoryItems());
                if(!gameController.playerIsAlive()) {
                    Intent aboutIntent = new Intent(GameActivity.this, GameOverActivity.class);
                    startActivity(aboutIntent);
                    stopMusic();
                } else {
                    if (gameController.roomEventHandleText().equals("FIGHT")) {
                        inventoryButton.setEnabled(false);
                        hideControlButtons();
                        battleLogScrollView.setEnabled(true);
                        battleLogScrollView.setVisibility(View.VISIBLE);
                        linearLayoutBattleLog.setEnabled(true);
                        linearLayoutBattleLog.setVisibility(View.VISIBLE);
                        closeBattleLogButton.setEnabled(true);
                        closeBattleLogButton.setVisibility(View.VISIBLE);
                        for (String log : gameController.getBattleLog()) {
                            TextView textView = new TextView(this);
                            textView.setText(log);
                            textView.setTextColor(Color.WHITE);
                            textView.setTextSize(15);
                            linearLayoutBattleLog.addView(textView);

                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                            layoutParams.setMargins(0, 0, 0, 16);
                            textView.setLayoutParams(layoutParams);
                        }
                        Toast.makeText(this, "You found something interesting!", Toast.LENGTH_SHORT).show();
                    }
                    renderPlayerInfo();
                    renderLevelUpButton();
                    disableButton(eventButton);
                }
            });
        } else{
            disableButton(eventButton);
        }
    }
    @SuppressLint("SetTextI18n")
    private void renderControlButtons() {
        if (checkVictoryCondition()) {
            Intent aboutIntent = new Intent(GameActivity.this, GameWonActivity.class);
            startActivity(aboutIntent);
            stopMusic();
        } else {
            if (gameController.hasNorthNeighbor()) {
                enableButton(northButton);
                northButton.setOnClickListener(view -> {
                    if (!gameController.getCurrentRoomEvent().equals("Monster")) {
                        int[] currentLocation = gameController.getCurrentLocation();
                        currentLocation[0] -= 1;
                        gameController.changeLocation(currentLocation);
                        renderScreen();
                    } else {
                        Toast.makeText(this, "Monster on the way!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else disableButton(northButton);
            if (gameController.hasSouthNeighbor()) {
                enableButton(southButton);
                if (gameController.isStartingLocation()) {
                    southButton.setText("Leave");
                    southButton.setOnClickListener(view -> {
                        Intent aboutIntent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(aboutIntent);
                        stopMusic();
                        Toast.makeText(this, "You successfully left dungeon.", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    southButton.setText("Go South");
                    southButton.setOnClickListener(view -> {
                        if (!gameController.getCurrentRoomEvent().equals("Monster")) {
                            int[] currentLocation = gameController.getCurrentLocation();
                            currentLocation[0] += 1;
                            gameController.changeLocation(currentLocation);
                            renderScreen();
                        } else {
                            Toast.makeText(this, "Monster on the way!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else disableButton(southButton);
            if (gameController.hasEastNeighbor()) {
                enableButton(eastButton);
                eastButton.setOnClickListener(view -> {
                    if (!gameController.getCurrentRoomEvent().equals("Monster")) {
                        int[] currentLocation = gameController.getCurrentLocation();
                        currentLocation[1] += 1;
                        gameController.changeLocation(currentLocation);
                        renderScreen();
                    } else {
                        Toast.makeText(this, "Monster on the way!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else disableButton(eastButton);
            if (gameController.hasWestNeighbor()) {
                enableButton(westButton);
                westButton.setOnClickListener(view -> {
                    if (!gameController.getCurrentRoomEvent().equals("Monster")) {
                        int[] currentLocation = gameController.getCurrentLocation();
                        currentLocation[1] -= 1;
                        gameController.changeLocation(currentLocation);
                        renderScreen();
                    } else {
                        Toast.makeText(this, "Monster on the way!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else disableButton(westButton);
        }
    }
    private void enableButton(Button button){
        button.setEnabled(true);
        button.setFocusable(true);
        button.setVisibility(View.VISIBLE);
    }
    private void disableButton(Button button) {
        button.setEnabled(false);
        button.setFocusable(false);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(view->{});
    }
    private void openInventory() {
        hideControlButtons();
        eventButton.setEnabled(false);
        closeInventoryButton.setEnabled(true);
        closeInventoryButton.setVisibility(View.VISIBLE);
        inventoryRecyclerview.setEnabled(true);
        inventoryRecyclerview.setVisibility(View.VISIBLE);
    }
    private void hideControlButtons(){
        disableButton(northButton);
        disableButton(southButton);
        disableButton(eastButton);
        disableButton(westButton);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClick(int position) {
        String itemName = gameController.getInventoryItems().get(position).getName();
        if(itemName.equals("Sword")
                || itemName.equals("Magic Sword")
                || itemName.equals("Dragon Blade")){
            gameController.equipSword(gameController.getInventoryItems().get(position));
            adapter.setSword(gameController.getInventoryItems().get(position));
            adapter.notifyDataSetChanged();
        } else if(itemName.equals("Shield")){
            gameController.equipShield((Shield) gameController.getInventoryItems().get(position));
            adapter.setShield((Shield) gameController.getInventoryItems().get(position));
            adapter.notifyDataSetChanged();
        } else {
            gameController.useOneTimeItem(gameController.getInventoryItems().get(position));
            adapter.notifyItemRemoved(position);
            if(!gameController.playerIsAlive()) {
                Intent aboutIntent = new Intent(GameActivity.this, GameOverActivity.class);
                startActivity(aboutIntent);
                stopMusic();
            }
        }
        renderPlayerInfo();
    }
    public boolean checkVictoryCondition() {
        if(!gameController.getEventName().equals("Monster")
                &&!gameController.roomEventHandleText().equals("Dragon")
                && !gameController.isInPassedRooms(gameController.getLocationID())){
            gameController.getPassedRooms().add(gameController.getLocationID());
        }
        return gameController.getPassedRooms().size() >= 15;
    }
    public void menuButtonAction() {
        menuButton.setOnClickListener(view -> {
            showMenu();
            menuButton.setOnClickListener(v->{
                disableButton(saveGameButton);
                disableButton(saveGameAndExitButton);
                disableButton(menuExitButton);
                menuButtonAction();
            });
        });
    }
    public void showMenu() {
        enableButton(saveGameButton);
        saveGameButton.setOnClickListener(view->{
            gameController.saveGame(this);
            disableButton(saveGameButton);
            disableButton(saveGameAndExitButton);
            disableButton(menuExitButton);
            menuButtonAction();
        });
        enableButton(saveGameAndExitButton);
        saveGameAndExitButton.setOnClickListener(view->{
            gameController.saveGame(this);
            Intent aboutIntent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(aboutIntent);
            stopMusic();
        });
        enableButton(menuExitButton);
        menuExitButton.setOnClickListener(view->{
            Intent aboutIntent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(aboutIntent);
            stopMusic();
        });
    }
    protected void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private void typingAnimation(String fullText, TextView typingTextView) {
        // Creates a handler to simulate typing animation

         handler = new Handler(getMainLooper()) {
            int charIndex = 0;
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (charIndex <= fullText.length()) {
                    typingTextView.setText(fullText.substring(0, charIndex++));
                    sendMessageDelayed(obtainMessage(0), 70);
                }
            }
        };
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