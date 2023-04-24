package com.city.building.models;

import java.util.UUID;

public class Building {
    private String id;
    private String type_ownership;
    private String address;
    private String date_commiss;
    private int number_floors;
    private String name_owner;

    public Building(String type_ownership, String address, String date_commiss, int number_floors, String name_owner) {
        this.id = UUID.randomUUID().toString();
        this.type_ownership = type_ownership;
        this.address = address;
        this. date_commiss = date_commiss;
        this.number_floors = number_floors;
        this.name_owner = name_owner;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType_ownership() {
        return type_ownership;
    }
    public void setType_ownership(String type_ownership){
        this.type_ownership = type_ownership;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getDate_commiss() {
        return date_commiss;
    }
    public void setDate_commiss(String date_commiss){
        this.date_commiss = date_commiss;
    }

    public int getNumber_floors(){
        return number_floors;
    }

    public void setNumber_floors(int number_floors){
        this.number_floors = number_floors;
    }

    public String getName_owner() {
        return name_owner;
    }
    public void setName_owner(String name_owner){
        this.name_owner = name_owner;
    }


    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", type_ownership='" + type_ownership + '\'' +
                ", address='" + address + '\'' +
                ", date_commiss='" + date_commiss + '\'' +
                ", number_floors=" + number_floors +
                ", name_owner='" + name_owner + '\'' +
                '}';
    }
}
