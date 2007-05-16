package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class ChangePage1_2ES extends TestCase {
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
        return new TestSuite(ChangePage1_2ES.class);
    }

    public void testChangePage1_2ES() throws Throwable {
        selenium.open("/nootes/index.html?lang=es");
		selenium.type("user", "javier");
		selenium.type("password", "javier");
		selenium.click("Submit");
                selenium.waitForPageToLoad("30000");
            selenium.open("/nootes/search.html?lang=es");
            selenium.type("w", "análisis");
		selenium.click("link=Documentos");
		selenium.waitForPageToLoad("30000");
		selenium.isTextPresent("Top Descargas");
	

     }

}