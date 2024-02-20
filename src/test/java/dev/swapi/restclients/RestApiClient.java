package dev.swapi.restclients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;


public class RestApiClient {
    private final RequestSpecification requestSpecification;

    public RestApiClient(String baseUrl) {
        this.requestSpecification = SerenityRest.given()
                .baseUri(baseUrl);
    }

    public void makeGetRequest(String endpointUrl) {
        makeGetRequest(endpointUrl, Map.of());
    }

    public void makeGetRequest(String endpointUrl, Map<String, Object> params) {
        requestSpecification
                .when()
                    .params(params)
                    .get(endpointUrl);
    }

}
