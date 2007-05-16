package seleniumtest;

import com.thoughtworks.selenium.*;

import junit.framework.*;

import org.openqa.selenium.server.*;

public class ChangeColor extends TestCase {
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
        return new TestSuite(ChangeColor.class);
    }

    public void test7() throws Throwable {
       selenium.open("/nootes/search.html?lang=es#c=upload");
	selenium.click("//img[@alt='Green']");
	//selenium.waitForPageToLoad("30000");
	selenium.open("/nootes/search.html?css=green");
     }

}