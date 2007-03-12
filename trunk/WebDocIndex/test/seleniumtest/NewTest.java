

package seleniumtest;

import junit.framework.*;
import org.openqa.selenium.server.*;
import com.thoughtworks.selenium.*;

public class NewTest extends TestCase {
    
    private Selenium selenium;

    public void setUp() throws Exception {
        String url = "http://localhost:8080";
        selenium = new DefaultSelenium("localhost", SeleniumServer.getDefaultPort(), "*firefox", url);
        selenium.start();
    }

    protected void tearDown() throws Exception {
        selenium.stop();
    }

    public void NewTest() throws Throwable {
        selenium.open("/WebDocIndex/signup.html");
        selenium.type("email1", "qwert@ha.com");
        selenium.type("email2", "qwert@ha.com");
        selenium.type("password1", "12345");
        selenium.type("password2", "12345");
        selenium.click("submit");
        selenium.waitForPageToLoad("30000");
        selenium.isTextPresent("Usuario Registrado con Exito"); //significa que el test ha pasado con exito
        
    }

}