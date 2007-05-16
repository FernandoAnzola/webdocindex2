package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class InvitationES extends TestCase {
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
        return new TestSuite(InvitationES.class);
    }

    public void testInvitationES() throws Throwable {
        selenium.open("/nootes/index.html?lang=es");
		selenium.type("user", "javier");
		selenium.type("password", "javier");
		selenium.click("Submit");
                selenium.waitForPageToLoad("30000");
      selenium.open("/nootes/search.html?lang=es");
	selenium.click("link=Invitaciones");
        selenium.isTextPresent("Recuerda que para acceder");
	selenium.click("Submit");
	selenium.isTextPresent("SU INVITACION SE HA ENVIADO");
	

     }

}