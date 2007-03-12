/*
 * testSearch.java
 *
 * Created on 12 de marzo de 2007, 13:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package seleniumtest;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class testSearch extends SeleneseTestCase {
	public void testSearchtestselide1() throws Exception {
		selenium.open("http://localhost:8080/WebDocIndex/search.html");
		selenium.type("query", "la");
		selenium.click("submit");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("Acierto:"));
		checkForVerificationErrors();
	}
}

