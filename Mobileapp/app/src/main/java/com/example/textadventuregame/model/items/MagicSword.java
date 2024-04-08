package com.example.textadventuregame.model.items;

public class MagicSword extends Item{
    private int phys_power_bonus;
    private int magic_power_bonus;
    private String name;
    private String imageFileName;
    public MagicSword(){
        super();
        phys_power_bonus = (int)(Math.random()*3)+3;
        magic_power_bonus = (int)(Math.random()*5)+3;
        this.name = "Magic Sword";
        this.imageFileName = "magic_sword_image";
    }

    public int getPhys_power_bonus() {
        return phys_power_bonus;
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
        return "PA: +"+phys_power_bonus+"  MA: +"+magic_power_bonus;
    }

    @Override
    public String getImageFileName() {
        return imageFileName;
    }
}
