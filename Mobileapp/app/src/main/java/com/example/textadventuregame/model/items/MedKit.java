package com.example.textadventuregame.model.items;

public class MedKit extends Item {
    private int hp_restore;
    private String name;
    private String imageFileName;
    public MedKit() {
        super();
        hp_restore = ((int)(Math.random()*9)+1)*10;
        this.name= "Med Kit";
        this.imageFileName = "medkit_image";
    }

    public int getHp_restore() {
        return hp_restore;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getBonusesDesc() {
        return "HP: +"+hp_restore+"  (1 use)";
    }

    @Override
    public String getImageFileName() {
        return imageFileName;
    }
}
