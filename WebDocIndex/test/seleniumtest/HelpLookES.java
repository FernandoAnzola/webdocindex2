package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class HelpLookES extends TestCase {
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
        return new TestSuite(HelpLookES.class);
    }

    public void testHelpLookES() throws Throwable {
        selenium.open("/nootes/index.html?lang=es");
		selenium.type("user", "javier");
		selenium.type("password", "javier");
		selenium.click("Submit");
                selenium.waitForPageToLoad("30000");
      selenium.open("/nootes/search.html");
		selenium.click("link=Ayuda a la busqueda");
		selenium.isTextPresent("Es posible realizar");
	
	

     }

}