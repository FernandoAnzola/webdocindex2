package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class SearchTest extends TestCase {
    private Selenium selenium;

    public void setUp() throws Exception {
        String url = "http://localhost:8080";
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", url);
        selenium.start();
        
    }

    protected void tearDown() throws Exception {
        selenium.stop();
    }

    public void testGoogleTestSearch() throws Throwable {
        selenium.open("/WebDocIndex/search.html");
        selenium.type("query", "la");
        
        selenium.click("submit");
        selenium.waitForPageToLoad("30000");
        selenium.isTextPresent("Acierto"); //significa que el test ha pasado con exito
    }

}