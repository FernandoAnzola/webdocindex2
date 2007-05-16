package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class IndexUserNoValidFR extends TestCase {
    private Selenium selenium;

    public void setUp() throws Exception {
        String url = "http://localhost:8080";
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", url);
        selenium.start();
        
    }

    protected void tearDown() throws Exception {
        selenium.stop();
    }
    
    public static Test suite(){
        return new TestSuite(IndexUserNoValidFR.class);
    }

    public void test6() throws Throwable {
        selenium.open("/nootes/index.html?lang=fr");
	selenium.type("password", "sfsdfsfsfsf");
	selenium.click("Submit");
	//selenium.waitForPageToLoad("30000");
	selenium.isTextPresent("UTILISATER OU MOT DE PASSE PAS CORRECT.");
        
    }

}