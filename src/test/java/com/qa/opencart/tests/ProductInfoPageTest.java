package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("MacBook");
		productInfoPage = resultsPage.selectProduct("MacBook Air");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Air");
	}

	@Test(dataProvider = "productData")
	public void getProductImagesCountTest(String productName, String mainProductName, int img) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int imagesCount = productInfoPage.getProductImageCount();
		System.out.println("Total images count for " + mainProductName + ": " + imagesCount);
		Assert.assertEquals(imagesCount, img);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "MacBook", "MacBook Pro", Constants.MACBOOK_PRO_IMAGES_COUNT },
				{ "MacBook", "MacBook Air", Constants.MACBOOK_AIR_IMAGES_COUNT },
				{ "iMac", "iMac", Constants.IMAC_IMAGES_COUNT },
				{ "Apple", "Apple Cinema 30\"", Constants.APPLE_IMAGES_COUNT } };
	}

	// Brand:Apple
	// Availability:Out Of Stock
	// exTaxprice:Ex Tax: $1,000.00
	// ProductName:MacBook Air
	// price:$1,000.00
	// Product Code:Product 17
	// ProductImagesCount:4
	// Reward Points:700
	@Test
	public void productDataTest() {
		resultsPage = accPage.doSearch("MacBook");
		productInfoPage = resultsPage.selectProduct("MacBook Air");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("ProductName"), "MacBook Air");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 17");
		softAssert.assertEquals(actProductInfoMap.get("ProductImagesCount"), "4");
		softAssert.assertAll();
	}

}
