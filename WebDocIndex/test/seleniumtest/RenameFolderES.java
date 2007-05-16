package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class RenameFolderES extends TestCase {
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
        return new TestSuite(RenameFolderES.class);
    }

    public void testRenameFolderES() throws Throwable {
        selenium.open("/nootes/index.html?lang=es");
		selenium.type("user", "javier");
		selenium.type("password", "javier");
		selenium.click("Submit");
                selenium.waitForPageToLoad("30000");
           selenium.open("/nootes/mydocuments.html");
		selenium.click("link=Renombrar Carpeta");
		selenium.isTextPresent("Selecciona la carpeta");
		selenium.select("folderToRename", "label=JUANJO");
		selenium.type("folderrename", "PRACTICAS");
		selenium.click("Submit");
		assertTrue(selenium.getConfirmation().matches("^seguro[\\s\\S]$"));
		selenium.isTextPresent("SU CARPETA SE HA RENOMBRADO.");
	
	

     }

}