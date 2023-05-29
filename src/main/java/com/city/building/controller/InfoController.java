package com.city.building.controller;

import com.city.building.models.MathExpectation;
import com.city.building.models.CityList;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

@RestController
@RequestMapping("info")
public class InfoController {
    private final CityList cityList = new CityList();
    private final MathExpectation mathExpectation = new MathExpectation();


    @GetMapping
    public LinkedHashMap<String, Object> info(HttpServletRequest request){
        LinkedHashMap<String, Object> info = new LinkedHashMap<String, Object>();
        this.cityList.readXML();
        int count_building = this.cityList.listCity().size();
        double math_wait = this.mathExpectation.matAnalyze(this.cityList);
        String username = request.getHeader("username");

        if(!Objects.equals(username, null))
            info.put("Приветствие", "Привет, " + username);
        info.put("ФИО автора", "Ищенко Никита Александрович");
        info.put("Вариант", 16);
        info.put("Предметная область", "Город");
        info.put("Ожидаемая этажность", Math.round(math_wait * 100.0) / 100.0);
        info.put("Кол-во построек", count_building);
        return info;

    }
}
