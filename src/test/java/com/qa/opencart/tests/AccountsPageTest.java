package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle =  accPage.getAccountsPageTitle();
		System.out.println("Account page Title is "+ actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		String actUrl = accPage.getAccountsPageUrl();
		System.out.println("Account page Url is "+ actUrl);
		Assert.assertTrue(actUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test(description = "Getting the Header text")
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
		//Assert.assertTrue(false);
	}
	
	@Test(enabled = true)
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> accSectionsList =  accPage.getAccPageSections();
		System.out.println("Actual section list :"+ accSectionsList);
		Assert.assertEquals(accSectionsList, Constants.ACCOUNTS_PAGE_SECTIONS_LIST);
	}
}
