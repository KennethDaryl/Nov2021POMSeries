package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResultsPageTest extends BaseTest {

	@BeforeClass
	public void resultsPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(dataProvider = "productData")
	public void seachTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount() > 0);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "MacBook" }, { "iMac" }, { "Apple" } };
	}
	
	@DataProvider
	public Object[][] productSelectData(){
		return new Object[][] {
			{ "MacBook","MacBook Pro" },{ "MacBook","MacBook Air" }, { "iMac","iMac" }, { "Apple","Apple Cinema 30\"" }
		};
	}

	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);
	}

}
