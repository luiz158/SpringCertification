package com.baselogic.demos.jbehave.pages;

import java.util.concurrent.TimeUnit;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.condition.Condition;
import com.thoughtworks.selenium.condition.ConditionRunner;
import com.thoughtworks.selenium.condition.Not;
import com.thoughtworks.selenium.condition.Presence;
import com.thoughtworks.selenium.condition.Text;

/**
 * HibernateConfiguration
 * 
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html">Objectives</a><br />
 *
 * @author Mick Knutson
 * @see Blog: <a href="http://www.baselogic.com">http://baselogic.com</a><br />
 * @see LinkedIN: <a href="http://linkedin.com/in/mickknutson">http://linkedin.com/in/mickknutson</a><br />
 * @see Twitter: <a href="http://twitter.com/mickknutson">http://twitter.com/mickknutson</a><br />
 * @see Github: <a href="http://github.com/mickknutson">http://github.com/mickknutson</a><br />
 * 
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a><br />
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a><br />
 * 
 * @since 2012
 * 
 */
public class StoreLoginPage extends Page {

	public StoreLoginPage(Selenium selenium, ConditionRunner runner) {
		super(selenium, runner);
	}

	@Override
	public void verifyPage() {
		textIsVisible("Please enter your username and password.");
	}

	public void verifyPresenceOfInvalidCredentialsErrorMessage() {
		textIsVisible("Invalid username or password. Signon failed.");
	}

	public void typeUsername(String username) {
		type("//input[@name='username']", username);
	}

	public void typePassword(String password) {
		type("//input[@name='password']", password);
	}

	public StoreFrontPage clickLoginButton() {
		clickButton("//input[@value='Login']");
		waitForPageToLoad();
		return new StoreFrontPage(selenium, runner);
	}

}
