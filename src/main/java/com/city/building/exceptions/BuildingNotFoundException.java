package com.city.building.exceptions;


public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(String id) {
        super("Строение с id " + id + " не найдено!");
    }

}
