package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// maintain By locators
	private By subHeader = By.cssSelector("fieldset#account legend");
	private By loginPagelink = By.linkText("login page");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passwordConfirm = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueButton = By.xpath("//input[@type='submit']");
	private By successMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.xpath("//div[@class='list-group']//a[text()='Register']");

	// Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getRegPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.REGISTRATION_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getRegPageUrl() {
		return eleUtil.waitForUrlContains(Constants.REGISTRATION_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public String getRegPageSubHeader() {
		return eleUtil.doGetText(subHeader);
	}

	public boolean isLoginPageLinkExist() {
		return eleUtil.doIsDisplayed(loginPagelink);
	}
	
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(passwordConfirm, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String successMsg = eleUtil.doGetText(successMessage);
		System.out.println(successMsg);
		if(successMsg.contains(Constants.REGISTRATION_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

	
	
	
	
	
}
