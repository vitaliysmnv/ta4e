package com.example.textadventuregame.model;


import static com.example.textadventuregame.model.RoomData.ROOM_DATA;

import com.example.textadventuregame.model.items.DragonBlade;
import com.example.textadventuregame.model.items.Item;
import com.example.textadventuregame.model.items.MagicSword;
import com.example.textadventuregame.model.items.MedKit;
import com.example.textadventuregame.model.items.Potion;
import com.example.textadventuregame.model.items.Shield;
import com.example.textadventuregame.model.items.Sword;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel implements Serializable {
    @JacksonXmlProperty(localName = "Player")
    private Player player;
    @JacksonXmlElementWrapper(localName = "Rooms")
    @JacksonXmlProperty(localName = "Room")
    private final List<Room> rooms = new ArrayList<>();
    @JacksonXmlProperty(localName = "Map")
    @JsonSerialize(using = TwoDArrayXmlSerializer.class)
    @JsonDeserialize(using = TwoDArrayXmlDeserializer.class)
    private int[][] map = new int[30][30];
    @JacksonXmlElementWrapper(localName = "Inventory")
    @JacksonXmlProperty(localName = "Item")
    private List<Item> inventory = new ArrayList<>();
    @JacksonXmlProperty(localName = "Sword")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Item sword;
    @JacksonXmlProperty(localName = "Shield")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Shield shield;
    @JacksonXmlElementWrapper(localName = "BattleLog")
    @JacksonXmlProperty(localName = "LogEntry")
    private List<String> battleLog;
    @JacksonXmlElementWrapper(localName = "PassedRooms")
    @JacksonXmlProperty(localName = "RoomNumber")
    private final List<Integer> passedRooms = new ArrayList<>();
    public GameModel() {
    }

    public void createRooms(){
        for (int i = 0; i < 15; i++) {
            rooms.add(new Room(i+1,
                    ROOM_DATA[i][0],
                    ROOM_DATA[i][1],
                    ROOM_DATA[i][2],
                    ROOM_DATA[i][3],
                    ROOM_DATA[i][4],
                    new ArrayList<>(Arrays.asList(ROOM_DATA[i][5].split(",")))));
        }
        generateMaze();
    }
    public void generateMaze(){
        MapGenerator generator = new MapGenerator();
        generator.generateMap();
        map = generator.getMap();
    }

    public void createPlayer(String name) {
        player = new Player(name);
    }

    public void createInventory() {
        inventory = new ArrayList<>();
        inventory.add(new Sword());
        inventory.add(new Shield());
    }

    public Player getPlayer() {
        return player;
    }

    public Room currentRoomByLocation() {
        int id = map[getPlayer().getLocation()[0]][getPlayer().getLocation()[1]];
        for (Room room : rooms) {
            if(room.getId()==id) return room;
        }
        return null;
    }
    public boolean hasNorthNeighbor(){
        return map[getPlayer().getLocation()[0]-1][getPlayer().getLocation()[1]]>0;
    }
    public boolean hasSouthNeighbor(){
        return map[getPlayer().getLocation()[0]+1][getPlayer().getLocation()[1]]!=0;
    }
    public boolean hasWestNeighbor(){
        return map[getPlayer().getLocation()[0]][getPlayer().getLocation()[1]-1]>0;
    }
    public boolean hasEastNeighbor(){
        return map[getPlayer().getLocation()[0]][getPlayer().getLocation()[1]+1]>0;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Item getSword() {
        return sword;
    }

    public Shield getShield() {
        return shield;
    }

    public void setSword(Item sword) {
        if (this.sword!=null) {
            switch (this.sword.getName()) {
                case "Sword": {
                    Sword swoord = (Sword) this.sword;
                    getPlayer().setPhys_attack(getPlayer().getPhys_attack() - swoord.getPhys_power_bonus());
                    break;
                }
                case "Magic Sword": {
                    MagicSword swoord = (MagicSword) this.sword;
                    getPlayer().setPhys_attack(getPlayer().getPhys_attack() - swoord.getPhys_power_bonus());
                    getPlayer().setMagic_attack(getPlayer().getMagic_attack() - swoord.getMagic_power_bonus());
                    break;
                }
                case "Dragon Blade": {
                    DragonBlade swoord = (DragonBlade) this.sword;
                    getPlayer().setPhys_attack(getPlayer().getPhys_attack() - swoord.getPhys_power_bonus());
                    break;
                }
            }
        }
        this.sword = sword;
        switch (this.sword.getName()) {
            case "Sword": {
                Sword swoord = (Sword) this.sword;
                getPlayer().setPhys_attack(getPlayer().getPhys_attack() + swoord.getPhys_power_bonus());
                break;
            }
            case "Magic Sword": {
                MagicSword swoord = (MagicSword) this.sword;
                getPlayer().setPhys_attack(getPlayer().getPhys_attack() + swoord.getPhys_power_bonus());
                getPlayer().setMagic_attack(getPlayer().getMagic_attack() + swoord.getMagic_power_bonus());
                break;
            }
            case "Dragon Blade": {
                DragonBlade swoord = (DragonBlade) this.sword;
                getPlayer().setPhys_attack(getPlayer().getPhys_attack() + swoord.getPhys_power_bonus());
                break;
            }
        }

    }

    public void setShield(Shield shield) {
        if (this.shield!=null) {
            getPlayer().setShields(getPlayer().getShields()-shield.getShields_power_bonus());
        }
        this.shield = shield;
        getPlayer().setShields(getPlayer().getShields()+shield.getShields_power_bonus());
    }

    public void useOneTimeItem(Item item) {
        if (item.getName().equals("Med Kit")) {
            MedKit medKit = (MedKit) item;
            player.setHp(Math.min(player.getHp() + medKit.getHp_restore(), 100));
        }else if (item.getName().equals("Potion")) {
            Potion potion = (Potion) item;
            player.setMagic_attack(player.getMagic_attack()+potion.getMagic_power_bonus());
            if(player.getHp()-potion.getHp_decrease()<=0){
                player.setAlive(false);
            } else player.setHp(player.getHp()-potion.getHp_decrease());
        }
        inventory.remove(item);
    }
    public void doEvent() {
        if(currentRoomByLocation().getEvent().equals("Monster")){
            battleLog = new ArrayList<>();
            int monsterHP = (int)(Math.random()*50)+70;
            int monsterAttack = (int)(Math.random()*5)+5;
            int monsterMagicAttack = (int)(Math.random()*5)+2;
            int monsterShields = (int)(Math.random()*2)+1;
            int playerPower = getPlayer().getPhys_attack()+(int)(0.5*getPlayer().getMagic_attack());
            int playerDamage = playerPower-monsterShields;
            int monsterPower = monsterAttack+(int)(0.5*monsterMagicAttack);
            int monsterDamage = monsterPower-player.getShields();
            while(getPlayer().getHp()>0 && monsterHP>0) {
                monsterHP -= playerDamage;
                String report = getPlayer().getName()+" attacked with power "
                        + playerPower
                        + " and dealt "+playerDamage
                        +" damage. Monster HP="+monsterHP;
                battleLog.add(report);
                if(monsterHP<=0) {
                    battleLog.add("victory");
                    addItemsToInventory(currentRoomByLocation().getEventRewards());
                    player.setXp(player.getXp()+(int)(Math.random()*3)+3);
                    currentRoomByLocation().setEvent("None");
                    break;
                }
                player.setHp(player.getHp()-monsterDamage);
                report = "Monster attacked with power "
                        + monsterPower
                        + "and dealt "+monsterDamage
                        +". "+getPlayer().getName()+" HP="+getPlayer().getHp();
                battleLog.add(report);
            }
            if (getPlayer().getHp()<=0) {
                battleLog.add("defeat");
                player.setAlive(false);
            }
        }
        if(currentRoomByLocation().getEvent().equals("Dragon")) {
            battleLog = new ArrayList<>();
            int dragonHP = (int) (Math.random() * 50) + 70;
            int dragonAttack = (int) (Math.random() * 7) + 5;
            int dragonMagicAttack = (int) (Math.random() * 5) + 1;
            int dragonShields = (int) (Math.random() * 4) + 3;
            int playerPower = getPlayer().getPhys_attack() + getPlayer().getMagic_attack();
            int playerDamage = playerPower - dragonShields;
            int dragonPower = dragonAttack + (int) (0.5 * dragonMagicAttack);
            int dragonDamage = dragonPower - player.getShields();
            while (getPlayer().getHp() > 0 && dragonHP > 0) {
                dragonHP -= playerDamage;
                String report = getPlayer().getName() + " attacked with power "
                        + playerPower
                        + " and dealt " + playerDamage
                        + " damage. Dragon HP=" + dragonHP;
                battleLog.add(report);
                if (dragonHP <= 0) {
                    battleLog.add("victory");
                    addItemsToInventory(currentRoomByLocation().getEventRewards());
                    player.setXp(player.getXp()+(int)(Math.random()*3)+3);
                    currentRoomByLocation().setEvent("None");
                    break;
                }
                player.setHp(player.getHp() - dragonDamage);
                report = "Monster attacked with power "
                        + dragonPower
                        + "and dealt " + dragonDamage
                        + ". " + getPlayer().getName() + " HP=" + getPlayer().getHp();
                battleLog.add(report);
            }
            if (getPlayer().getHp() <= 0) {
                battleLog.add("defeat");
                player.setAlive(false);
            }
        }

        if(currentRoomByLocation().getEvent().equals("Treasure")){
            addItemsToInventory(currentRoomByLocation().getEventRewards());
            currentRoomByLocation().setEvent("None");
        }
        if(currentRoomByLocation().getEvent().equals("Horror")){
            player.setShields(Math.max(0, player.getShields()-1));
            player.setPhys_attack(Math.max(1, player.getPhys_attack()-2));
            player.setMagic_attack(Math.max(1, player.getMagic_attack()-1));
            currentRoomByLocation().setEvent("None");
        }
        if(currentRoomByLocation().getEvent().equals("Heal")){
            player.setHp(100);
            currentRoomByLocation().setEvent("None");
        }
        if(currentRoomByLocation().getEvent().equals("Trap")){
            player.setHp(Math.max(player.getHp()-(int)(Math.random()*30+10), 1));
            currentRoomByLocation().setEvent("None");
        }
    }
    public List<String> getBattleLog() {
        return battleLog;
    }
    public void addItemsToInventory(List<Item> items) {
        List<Item> newInventory = new ArrayList<>(inventory);
        newInventory.addAll(items);
        inventory = newInventory;
    }

    public List<Integer> getPassedRooms() {
        return passedRooms;
    }
    public boolean isInPassedRooms(Integer i) {
        return passedRooms.contains(i);
    }
}
