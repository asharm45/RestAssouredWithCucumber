package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    public RequestSpecification requestSpecification(){
        if(req == null){
            PrintStream log;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));
                return new RequestSpecBuilder().setBaseUri(getPropertiesValue("baseURI")).addQueryParam("key","qaclick123")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).addHeader("Content-Type","application/json").build();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return req;
    }

    private static String getPropertiesValue(String key){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/properties/QA.properties");
            try {
                properties.load(fileInputStream);
                return properties.getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getValue(Response response, String key) {

        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.getString(key);
    }
}
