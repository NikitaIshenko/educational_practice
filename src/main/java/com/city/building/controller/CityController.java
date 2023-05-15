package com.city.building.controller;

import com.city.building.models.Building;
import com.city.building.models.CityList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("city")
public class CityController {
    CityList cityList = new CityList();

    @GetMapping
    public List<Building> list(){
        cityList.readXML();
        return cityList.listCity();
    }

    @GetMapping("{id}")
    public Building get_by_id(@PathVariable String id){
        cityList.readXML();
        return cityList.getBuildingById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Building create(@RequestBody Map<String, String> body) throws ParserConfigurationException {
        cityList.readXML();
        Building building = new Building(body.get("type_ownership"), body.get("address"), body.get("date_commiss"), Integer.parseInt(body.get("number_floors")), body.get("name_owner"));
        cityList.addBuilding(building);
        cityList.saveXML();
        return building;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) throws ParserConfigurationException {
        cityList.readXML();
        cityList.deleteBuilding(id);
        cityList.saveXML();
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Building update(@PathVariable String id, @RequestBody Map<String, String> body) throws ParserConfigurationException {
        cityList.readXML();
        Building building = cityList.getBuildingById(id);
        for(String key : body.keySet()){
            switch (key){
                case "type_ownership" -> building.setType_ownership(body.get(key));
                case "address" -> building.setAddress(body.get(key));
                case "date_commiss" -> building.setDate_commiss(body.get(key));
                case "number_floors" -> building.setNumber_floors(Integer.parseInt(body.get(key)));
                case "name_owner" -> building.setName_owner(body.get(key));
            }
        }

        cityList.saveXML();
        return building;
    }

}
