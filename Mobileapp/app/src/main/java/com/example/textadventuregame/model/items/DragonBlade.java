package com.example.textadventuregame.model.items;

public class DragonBlade extends Item{
    private int phys_power_bonus;
    private String name;
    private String imageFileName;

    public DragonBlade(){
        super();
        phys_power_bonus = (int)(Math.random()*5)+10;
        this.name = "Dragon Blade";
        this.imageFileName = "dragon_blade_image";
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
