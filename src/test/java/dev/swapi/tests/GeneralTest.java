package dev.swapi.tests;

import dev.swapi.actions.SwapiActions;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.apache.http.HttpStatus.SC_OK;

@ExtendWith(SerenityJUnit5Extension.class)
public class GeneralTest {
    SwapiActions swapiActions;


    @Test
    void checkNumberOfPlanets() {
        final int expectedNumPlanets = 60;
        swapiActions.makeGetPlanetsRequest();
        swapiActions.statusCodeShouldBe(SC_OK);
        swapiActions.numOfPlanetsShouldBe(expectedNumPlanets);
    }

    @Test
    void checkNumberOfPeople() {
        final int expectedNumPeople = 82;
        swapiActions.makeGetPeopleRequest();
        swapiActions.statusCodeShouldBe(SC_OK);
        swapiActions.numOfPeopleShouldBe(expectedNumPeople);
    }

    @Test
    void checkFirstPersonOfPeople() {
        final String expectedPerson = "Luke Skywalker";
        swapiActions.makeGetPeopleRequest(1);
        swapiActions.statusCodeShouldBe(SC_OK);
        swapiActions.thePersonShouldBe(expectedPerson);
    }

    @Test
    void checkDorinPlanetInformation() {
        final String planetName = "Dorin";
        final int expectedDiameter = 13400;
        final int expectedNumOfResident = 1;
        swapiActions.makeGetPlanetsRequest(planetName);
        swapiActions.statusCodeShouldBe(SC_OK);
        swapiActions.planetDiameterShouldBe(planetName, expectedDiameter);
        swapiActions.planetResidentNumShouldBe(planetName, expectedNumOfResident);
    }

}
