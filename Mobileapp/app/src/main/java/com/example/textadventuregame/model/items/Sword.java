package com.example.textadventuregame.model.items;

import java.io.Serializable;

public class Sword extends Item implements Serializable {
    private int phys_power_bonus;
    private String name;
    private String imageFileName;
    public Sword(){
        super();
        phys_power_bonus = (int)(Math.random()*4)+2;
        this.name = "Sword";
        this.imageFileName = "sword_image";
    }

    public int getPhys_power_bonus() {
        return phys_power_bonus;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getBonusesDesc() {
        return "PA: +"+phys_power_bonus;
    }

    @Override
    public String getImageFileName() {
        return imageFileName;
    }
}
