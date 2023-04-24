package com.city.building.models;

import com.city.building.exceptions.BuildingNotFoundException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityList {
    private final List<Building> city = new ArrayList<>();
    private String file_name = "city.xml";
    private File file = new File(file_name);

    public String getFile_name(){
        return this.file_name;
    }
    public void setFile_name(String file_name){
        this.file_name = file_name;
        this.file = new File(file_name);
    }
    public List<Building> listCity() {return city; }

    public Building getBuildingById(String id){
        return city.stream().filter(Building -> Building.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    public void addBuilding(Building building){
        city.add(building);
    }

    public void deleteBuilding(String id) {
        Building building = getBuildingById(id);
        city.remove(building);
    }


    public void readXML(){
        city.clear();
        DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try{
            doc = bdf.newDocumentBuilder().parse(file);
        } catch (Exception e){
            System.out.println("ERR" + e.toString());
        }

        String id = "";
        String type_ownership = "";
        String address = "";
        String date_comiss = "";
        int number_floors = 0;
        String name_owner = "";


        assert doc != null;
        NodeList buildingList = doc.getElementsByTagName("Building");
        for (int i = 0; i < buildingList.getLength(); i++){

            NodeList buildingChild = buildingList.item(i).getChildNodes();
            for(int j = 0; j < buildingChild.getLength(); j++){

                if(buildingChild.item(j).getNodeType() != Node.ELEMENT_NODE)
                    continue;

                switch (buildingChild.item(j).getNodeName()) {
                    case "id" ->  id = buildingChild.item(j).getTextContent();
                    case "type_ownership" -> type_ownership = buildingChild.item(j).getTextContent();
                    case "address" -> address = buildingChild.item(j).getTextContent();
                    case "date_comiss" -> date_comiss = buildingChild.item(j).getTextContent();
                    case "number_floors" -> number_floors = Integer.parseInt(buildingChild.item(j).getTextContent());
                    case "name_owner" -> name_owner = buildingChild.item(j).getTextContent();


                }
            }
        Building building = new Building(type_ownership, address, date_comiss, number_floors, name_owner);
        building.setId(id);
        city.add(building);

        }
    }

    public void saveXML() throws ParserConfigurationException {
        DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = bdf.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("city");
        doc.appendChild(rootElement);

        for (Building building : city) {
            Element building_tag = doc.createElement("Building");
            rootElement.appendChild(building_tag);

            Element id = doc.createElement("id");
            id.setTextContent(building.getId());
            building_tag.appendChild(id);

            Element type_ownership = doc.createElement("type_ownership");
            type_ownership.setTextContent(building.getType_ownership());
            building_tag.appendChild(type_ownership);

            Element address = doc.createElement("address");
            address.setTextContent(building.getAddress());
            building_tag.appendChild(address);

            Element date_comiss = doc.createElement("date_comiss");
            date_comiss.setTextContent(building.getDate_commiss());
            building_tag.appendChild(date_comiss);

            Element number_floors = doc.createElement("number_floors");
            number_floors.setTextContent(String.valueOf(building.getNumber_floors()));
            building_tag.appendChild(number_floors);

            Element name_owner = doc.createElement("name_owner");
            name_owner.setTextContent(building.getName_owner());
            building_tag.appendChild(name_owner);
        }
        writeDocument(doc, file_name);
    }


    private void writeDocument(Document document, String path)
            throws TransformerFactoryConfigurationError
    {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;

        try {
            trf = TransformerFactory.newInstance()
                    .newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);

            StreamResult result = new StreamResult(fos);
            trf.setOutputProperty(OutputKeys.INDENT, "yes");
            trf.transform(src, result);

        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}

