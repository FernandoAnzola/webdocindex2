package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class NewFolderES extends TestCase {
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
        return new TestSuite(NewFolderES.class);
    }

    public void testNewFolderES() throws Throwable {
        selenium.open("/nootes/index.html?lang=en");
		selenium.type("user", "javier");
		selenium.type("password", "javier");
		selenium.click("Submit");
                selenium.waitForPageToLoad("30000");
           selenium.open("/nootes/mydocuments.html");
		selenium.click("link=Crear Nueva Carpeta");
		selenium.type("folder", "JUANJO");
		selenium.click("Submit");
		selenium.isTextPresent("SU CARPETA SE HA CREADO.");
	
	

     }

}