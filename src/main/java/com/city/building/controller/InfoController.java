package com.city.building.controller;

import com.city.building.models.MathExpectation;
import com.city.building.models.CityList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("info")
public class InfoController {
    CityList cityList = new CityList();
    MathExpectation mathExpectation = new MathExpectation();

    @GetMapping
    public HashMap<String, Object> info(){
        HashMap<String, Object> info = new HashMap<String, Object>();
        cityList.readXML();
        int count_building = cityList.listCity().size();
        double math_wait = mathExpectation.matAnalyze(cityList);


        info.put("ФИО автора", "Ищенко Никита Александрович");
        info.put("Вариант", 16);
        info.put("Предметная область", "Город");
        info.put("Ожидаемая этажность", Math.round(math_wait * 100.0) / 100.0);
        info.put("Кол-во построек", count_building);
        return info;

    }
}
