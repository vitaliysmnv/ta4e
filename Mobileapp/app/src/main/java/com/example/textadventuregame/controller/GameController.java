package com.example.textadventuregame.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.textadventuregame.model.*;
import com.example.textadventuregame.model.items.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.File;
import java.util.List;

public class GameController {
    @JacksonXmlProperty
    private GameModel gameModel;
    public void createGameModel() {
        gameModel = new GameModel();
    }

    public void gameStart(String playerName) {
        createGameModel();
        gameModel.createRooms();
        gameModel.createPlayer(playerName); // name entered by user
        gameModel.createInventory();

    }

    public String getLocationImageName() {
        return gameModel.currentRoomByLocation().getImage();
    }
    public String getLocationText() {
        return gameModel.currentRoomByLocation().getDescription();
    }
    public String getPlayerInfoText() {
        return gameModel.getPlayer().PlayerInfo();
    }
    public boolean checkLevelUp() {
        boolean levelUp = gameModel.getPlayer().getXp()>=5;
        if(levelUp) gameModel.getPlayer().setXp(gameModel.getPlayer().getXp()-5);
        return levelUp;
    }
    public boolean roomWithEvent(){
        return gameModel.currentRoomByLocation().hasEvent();
    }
    public String roomEventHandleText(){
        return gameModel.currentRoomByLocation().getEventHandle();
    }

    public void changeLocation(int[] newRoomLocation) {
        gameModel.getPlayer().setLocation(newRoomLocation[0], newRoomLocation[1]);
    }
    public int[] getCurrentLocation() {
        return gameModel.getPlayer().getLocation();
    }
    public boolean isStartingLocation(){
        int [] location = getCurrentLocation();
        return location[0]==15 && location[1]==15;
    }
    public String getCurrentRoomEvent(){
        return gameModel.currentRoomByLocation().getEvent();
    }
    public boolean hasNorthNeighbor(){
        return gameModel.hasNorthNeighbor();
    }
    public boolean hasSouthNeighbor(){
        return gameModel.hasSouthNeighbor();
    }
    public boolean hasWestNeighbor(){
        return gameModel.hasWestNeighbor();
    }
    public boolean hasEastNeighbor(){
        return gameModel.hasEastNeighbor();
    }
    public List<Item> getInventoryItems() {
        return gameModel.getInventory();
    }
    public Item getEquippedSword() {
        return gameModel.getSword();
    }
    public Shield getEquippedShield() {
        return gameModel.getShield();
    }
    public void equipSword(Item sword) {
        gameModel.setSword(sword);
    }
    public void equipShield(Shield shield) {
        gameModel.setShield(shield);
    }
    public void useOneTimeItem(Item item) {
        gameModel.useOneTimeItem(item);
    }

    public void doEvent() {
        gameModel.doEvent();
    }
    public List<String> getBattleLog() {
        return gameModel.getBattleLog();
    }
    public boolean playerIsAlive() {
        return gameModel.getPlayer().isAlive();
    }
    public void levelUp() {
        gameModel.getPlayer().setMagic_attack(gameModel.getPlayer().getMagic_attack()+2);
        gameModel.getPlayer().setPhys_attack(gameModel.getPlayer().getPhys_attack()+2);
        gameModel.getPlayer().setShields(gameModel.getPlayer().getShields()+2);
        gameModel.getPlayer().setLevel(gameModel.getPlayer().getLevel()+1);
    }
    public List<Integer> getPassedRooms() {
        return gameModel.getPassedRooms();
    }
    public boolean isInPassedRooms(Integer i) {
        return gameModel.isInPassedRooms(i);
    }
    public int getLocationID() {
        return gameModel.currentRoomByLocation().getId();
    }
    public String getEventName() {
        return gameModel.currentRoomByLocation().getEvent();
    }
    public void saveGame(Context ctx) {
        try {
            System.out.println(gameModel.getPlayer().getName() + ".xml");
            final File filesDir = ctx.getFilesDir();

            File file = new File(filesDir+"/"+ gameModel.getPlayer().getName() + ".xml");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file,gameModel);
            System.out.println("Game saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadGame(File file) {
        try {
            String fileName = file.getName();
            System.out.println(file.getAbsoluteFile());
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            GameModel gameData = xmlMapper.readValue(file, GameModel.class);

            System.out.println("Game loaded successfully!");
            gameModel = gameData;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
