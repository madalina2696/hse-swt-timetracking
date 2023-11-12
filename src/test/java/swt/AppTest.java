package swt;

import static org.junit.Assert.*;

import timetracking.App;

public class AppTest {

    @org.junit.Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest);
    }
}
