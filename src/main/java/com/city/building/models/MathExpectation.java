package com.city.building.models;

import java.util.HashMap;

public class MathExpectation {
    CityList cityList = new CityList();

    private HashMap<Integer, Float> getPercentFloor(CityList cityList){
        HashMap<Integer, Float> percentFloor = new HashMap<>();
        int count_floors = cityList.listCity().size();
        for (int i = 0; i < count_floors; i++){
            int floor = cityList.listCity().get(i).getNumber_floors();
            Float floor_percent = percentFloor.get(floor);
            if(floor_percent == null){
                float floor_c = 1;
                for(int j = i + 1; j < count_floors; j++){
                    if (cityList.listCity().get(j).getNumber_floors() == floor)
                        floor_c++;
                }
                float result = floor_c / count_floors;
                percentFloor.put(floor, result);
            }
        }
        return percentFloor;
    }

    public double matAnalyze(CityList cityList){
        double mat_wait = 0.0;
        HashMap<Integer, Float> percentFloor = getPercentFloor(cityList);
        for (HashMap.Entry<Integer, Float> floor : percentFloor.entrySet()) {
            mat_wait += floor.getKey() * floor.getValue();
        }
        return mat_wait;

    }

}
