package dev.swapi.actions;

import dev.swapi.dto.GeneralArrayResponseDto;
import dev.swapi.dto.PeopleResponseDto;
import dev.swapi.dto.PlanetResponseDto;
import dev.swapi.restclients.RestApiClient;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

import java.util.Collections;

import static dev.swapi.utils.PojoConverter.convertMapToPojo;
import static dev.swapi.utils.PropertyReader.*;
import static java.lang.String.format;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SwapiActions extends UIInteractions {

    private final RestApiClient restApiClient = new RestApiClient(getBaseUrl());

    @Step("Make GET /planet request")
    public void makeGetPlanetsRequest() {
        restApiClient.makeGetRequest(getPlanetsUrl());
    }

    @Step("Make GET /planet?search={0} request ")
    public void makeGetPlanetsRequest(String planetName) {
        restApiClient
                .makeGetRequest(getPlanetsUrl(), Collections.singletonMap("search", planetName));
    }

    @Step("Make GET /people request")
    public void makeGetPeopleRequest() {
        restApiClient.makeGetRequest(getPeopleUrl());
    }

    @Step("Make GET /people/{0} request")
    public void makeGetPeopleRequest(Integer index) {
        restApiClient.makeGetRequest(format("%s/%d", getPeopleUrl(), index));
    }

    @Step("Check that response status code is {0}")
    public void statusCodeShouldBe(Integer statusCode) {
        then().assertThat().statusCode(statusCode);
    }

    @Step("Check that number of planets is equal to {0}")
    public void numOfPlanetsShouldBe(Integer expectedNumPlanets) {
        assertThat(then().extract().as(GeneralArrayResponseDto.class).getCount())
                .as("Total number of planets returned by the API isn't " + expectedNumPlanets)
                .isEqualTo(expectedNumPlanets);
    }

    @Step("Check that number of people is equal to {0}")
    public void numOfPeopleShouldBe(Integer expectedNumPeople) {
        assertThat(then().extract().as(GeneralArrayResponseDto.class).getCount())
                .as("Total number of people returned by the API isn't " + expectedNumPeople)
                .isEqualTo(expectedNumPeople);
    }

    @Step("Check that person is {0}")
    public void thePersonShouldBe(String expectedPerson) {
        assertThat(then().extract().as(PeopleResponseDto.class).getName())
                .as("The first returned person isn't " + expectedPerson)
                .isEqualToIgnoringCase(expectedPerson);
    }

    @Step("Check that diameter of {0} is equal to {1}")
    public void planetDiameterShouldBe(String planetName, Integer expectedDiameter) {
        PlanetResponseDto dorianPlanet = convertMapToPojo(
                then().extract().as(GeneralArrayResponseDto.class).getResults().get(0),
                PlanetResponseDto.class);
        assertThat(dorianPlanet.getDiameter())
                .as(format("The diameter of %s isn't equal to %d", planetName, expectedDiameter))
                .isEqualTo(expectedDiameter);
    }

    @Step("Check that number of {0}'s residents is equal to {1}")
    public void planetResidentNumShouldBe(String planetName, Integer expectedNumOfResident) {
        PlanetResponseDto dorianPlanet = convertMapToPojo(
                then().extract().as(GeneralArrayResponseDto.class).getResults().get(0),
                PlanetResponseDto.class);
        assertThat(dorianPlanet.getResidents().size())
                .as(format("The number of %s's residents isn't equal to %d", planetName, expectedNumOfResident))
                .isEqualTo(expectedNumOfResident);
    }
}
