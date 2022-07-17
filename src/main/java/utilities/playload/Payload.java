package utilities.playload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.request.AddPlaceModel;
import utilities.request.DeletePlaceModel;
import utilities.request.Location;
import utilities.request.UpdatePlaceModel;

import java.util.ArrayList;
import java.util.List;

public class Payload {

    public String addPlaceRequest(String name, String language, String address) {

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");

        AddPlaceModel placeModel = new AddPlaceModel();
        placeModel.setLocation(location);
        placeModel.setAccuracy(50);
        placeModel.setName(name);
        placeModel.setPhone_number("(+91) 983 893 3937");
        placeModel.setAddress(address);
        placeModel.setTypes(types);
        placeModel.setWebsite("http://google.com");
        placeModel.setLanguage(language);

        return convertJavaObjectToJsonObject(placeModel);
    }

    public String updatePlaceRequest(String place_id) {

        UpdatePlaceModel updatePlaceModel = new UpdatePlaceModel();
        updatePlaceModel.setPlace_id(place_id);
        updatePlaceModel.setKey("qaclick123");
        updatePlaceModel.setAddress("70 Summer walk, USA");

        return convertJavaObjectToJsonObject(updatePlaceModel);
    }

    public String deletePlaceRequest(String place_id) {
        DeletePlaceModel deletePlaceModel = new DeletePlaceModel();
        deletePlaceModel.setPlace_id(place_id);

        return convertJavaObjectToJsonObject(deletePlaceModel);
    }


    private String convertJavaObjectToJsonObject(Object payload) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Payload is : " + mapper.writeValueAsString(payload));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }


        public static String mockResponse() {
            return "{\n" +
                    "\"dashboard\": {\n" +
                    "\"purchaseAmount\": 910,\n" +
                    "\"website\": \"rahulshettyacademy.com\"\n" +
                    "},\n" +
                    "\"courses\": [\n" +
                    "{\n" +
                    "\"title\": \"Selenium Python\",\n" +
                    "\"price\": 50,\n" +
                    "\"copies\": 6\n" +
                    "},\n" +
                    "{\n" +
                    "\"title\": \"Cypress\",\n" +
                    "\"price\": 40,\n" +
                    "\"copies\": 4\n" +
                    "},\n" +
                    "{\n" +
                    "\"title\": \"RPA\",\n" +
                    "\"price\": 45,\n" +
                    "\"copies\": 10\n" +
                    "}\n" +
                    "]\n" +
                    "}";
        }

    }

