package steps;

import cucumber.api.java.Before;

import static utils.PropertyReader.setProperties;

public class Hooks {
    @Before
    public void init() {
        setProperties();
    }
}
