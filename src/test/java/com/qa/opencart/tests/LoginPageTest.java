package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100: Design login page for Open Cart application")
@Story("US - 01: Login page features")
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title test")
	@Test(enabled = true)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Page title is :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login Page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Testing URL of login page")
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageURL();
		System.out.println("Login Page url is :" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		//Assert.assertTrue(false);
	}
	
	@Description("Testing Forgot Password link exists")
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
		//Assert.assertTrue(false);
	}
}
