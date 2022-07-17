package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

    StepDefinitions sd = new StepDefinitions();

    @Before("@DeletePlace")
    public void beforeScenario(){
        if(StepDefinitions.place_id == null){
            sd.add_place_payload_with("Ram","Tamil","Chennai");
            sd.user_calls_with_http_request("ADD_PLACE_API","POST");
            sd.verify_place_id_created_map_to_using("Ram","GET_PLACE_API");
        }
    }
}
