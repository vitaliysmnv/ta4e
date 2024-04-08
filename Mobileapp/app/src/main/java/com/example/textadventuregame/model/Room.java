package com.example.textadventuregame.model;

import com.example.textadventuregame.model.items.Item;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{
    @JacksonXmlProperty
    private int id;
    @JacksonXmlProperty
    private String name;
    @JacksonXmlProperty
    private String description;
    @JacksonXmlProperty
    private String image;
    @JacksonXmlProperty
    private boolean visited;
    @JacksonXmlProperty
    private String event;
    @JacksonXmlProperty
    private String eventHandle;
    @JacksonXmlElementWrapper(localName = "EventRewards")
    @JacksonXmlProperty(localName = "Item")
    private List<Item> eventRewards;

    public Room(int id, String name, String description, String event,String eventHandle,String image, List<String> rewards) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.visited = false;
        this.event = event;
        this.eventHandle = eventHandle;
        if(!rewards.get(0).equals("")) {
            this.eventRewards = new ArrayList<>();
            for (String reward : rewards) {
                this.eventRewards.add(createItem(reward));
            }
        } else {
            this.eventRewards=null;
        }
    }
    public Room(){}
    public static Item createItem(String className) {
        try {
            Class<?> clazz = Class.forName("com.example.textadventuregame.model.items." + className);
            Constructor<?> ctor = clazz.getConstructor();
            Object object = ctor.newInstance();
            return (Item)object;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getEventHandle() {
        return eventHandle;
    }
    public boolean hasEvent(){
        return !event.equals("None");
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<Item> getEventRewards() {
        return eventRewards;
    }

    public void setEventRewards(List<Item> eventRewards) {
        this.eventRewards = eventRewards;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return id == other.getId();
    }
}
