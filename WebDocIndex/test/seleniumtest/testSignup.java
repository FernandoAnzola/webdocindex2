/*
 * testSignup.java
 *
 * Created on 12 de marzo de 2007, 14:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package seleniumtest;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class testSignup extends SeleneseTestCase {
	public void testNew() throws Exception {
            selenium.open("http://localhost:8080/WebDocIndex/signup.html");
            selenium.type("email1", "pepe@fhfh.com");
            selenium.type("email2", "pepe@fhfh.com");
            selenium.type("password1", "12345");
            selenium.type("password2", "12345");
            selenium.click("submit");
            selenium.waitForPageToLoad("30000");
            verifyTrue(selenium.isTextPresent("Los datos introducidos no son validos. Compruebe que coinciden!!"));
            checkForVerificationErrors();
	}
}