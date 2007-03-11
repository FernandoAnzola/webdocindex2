package seleniumtest;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;


public class NewTest extends SeleneseTestCase {
	public void testNew() throws Exception {
		selenium.open("/WebDocIndex/signup.html");
		selenium.type("email1", "pepeti@hoymail.com");
		selenium.type("email2", "pepeti@hoymail.com");
		selenium.type("password1", "12345");
		selenium.type("password2", "12345");
		selenium.click("submit");
		selenium.waitForPageToLoad("30000");
		checkForVerificationErrors();
	}
}
