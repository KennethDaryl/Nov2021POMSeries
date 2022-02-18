package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By locator
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.xpath("//div[@class='list-group']//a[text()='Register']");

	// 2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Page actions/methods
	@Step("Getting the login page Title")
	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitleContains(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("Getting the login page URL")
	public String getLoginPageURL() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("Check Forgot Password link is displayed")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgetPwdLink);
	}
	
	@Step("Login with Username: {0} and Password: {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	public RegistrationPage doClickRegister() {
		if(isRegisterLinkExist()) {
			eleUtil.doClick(registerLink);
		}
		return new RegistrationPage(driver);
	}
}
