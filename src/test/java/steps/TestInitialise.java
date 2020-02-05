package steps;

import cucumber.api.java.Before;
import utilities.RestAssuredExtension;

public class TestInitialise {

    @Before
    public void testSetup() {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
