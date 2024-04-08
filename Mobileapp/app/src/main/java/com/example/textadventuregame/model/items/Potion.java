package com.example.textadventuregame.model.items;

import java.io.Serializable;

public class Potion extends Item implements Serializable {
    private int magic_power_bonus;
    private int hp_decrease;
    private String name;
    private String imageFileName;
    public Potion(){
        super();
        magic_power_bonus = (int)(Math.random()*5)+1;
        hp_decrease = (int)(Math.random()*20)+1;
        this.name = "Potion";
        this.imageFileName = "potion_image";
    }

    public int getHp_decrease() {
        return hp_decrease;
    }

    public int getMagic_power_bonus() {
        return magic_power_bonus;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getBonusesDesc() {
        return "MA: +"+magic_power_bonus+"  HP: -"+hp_decrease+"  (1 use)";
    }

    @Override
    public String getImageFileName() {
        return imageFileName;
    }
}
