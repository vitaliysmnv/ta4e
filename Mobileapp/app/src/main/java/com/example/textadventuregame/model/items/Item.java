package com.example.textadventuregame.model.items;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DragonBlade.class, name = "DragonBlade"),
        @JsonSubTypes.Type(value = MagicSword.class, name = "MagicSword"),
        @JsonSubTypes.Type(value = MedKit.class, name = "MedKit"),
        @JsonSubTypes.Type(value = Potion.class, name = "Potion"),
        @JsonSubTypes.Type(value = Shield.class, name = "Shield"),
        @JsonSubTypes.Type(value = Sword.class, name = "Sword")}
)

public abstract class Item {
    @JacksonXmlProperty
    private String name;
    @JacksonXmlProperty
    private String imageFileName;
    static int ID = 0;
    final int id;

    protected Item() {
        this.id = ++ID;
    }

    public String getName() {
        return name;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public abstract String getBonusesDesc();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return id == other.getId();
    }
    public int getId() {
        return id;
    }
}
