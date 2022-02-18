package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPagesetUp() {
		regPage = loginPage.doClickRegister();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTRATION_SHEET_NAME);
		return regData;
	}
	
	public String getRandomNumber() {
		Random randomGen = new Random();
		String email = "KenDarNovBatch"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	}

	@Test
	public void regPageTitleTest() {
		String actTitle = regPage.getRegPageTitle();
		Assert.assertEquals(actTitle, Constants.REGISTRATION_PAGE_TITLE);
	}

	@Test
	public void regPageUrlTest() {
		String url = regPage.getRegPageUrl();
		Assert.assertTrue(url.contains(Constants.REGISTRATION_PAGE_URL_FRACTION));
	}

	@Test
	public void regPageSubHeaderTest() {
		String subHeader = regPage.getRegPageSubHeader();
		Assert.assertEquals(subHeader, Constants.REGISTRATION_PAGE_SUBHEADER);
	}

	@Test
	public void loginLinkTest() {
		Assert.assertTrue(regPage.isLoginPageLinkExist());
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomNumber(), telephone, password, subscribe));
		// Assert.assertTrue(regPage.accountRegistration("Ken", "Men",
		// "KenKeithDaryl@gmail.com", "9339839200", "Maniac@123", "Yes"));
	}

}
