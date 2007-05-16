package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class IndexUserNoValidES extends TestCase {
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
        return new TestSuite(IndexUserNoValidEN.class);
    }

    public void test2() throws Throwable {
        selenium.open("/nootes/index.html?lang=en");
        selenium.type("user", "jajsjsj");
        selenium.type("password", "jajajjaja");
        selenium.click("Submit");
        selenium.isTextPresent("USER OR/AND PASSWORD ARE NOT VALID."); //significa que el test ha pasado con exito
    }

}