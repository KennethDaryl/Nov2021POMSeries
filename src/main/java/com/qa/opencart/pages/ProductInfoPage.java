package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeaderName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div.col-sm-4 ul:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div.col-sm-4 ul:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderName() {
		return eleUtil.doGetText(productHeaderName);
	}

	public int getProductImageCount() {
		return eleUtil.waitForWebElementsVisible(productImages, 10).size();
	}

	public Map<String, String> getProductInfo() {
		productMap = new HashMap<String, String>();
		productMap.put("ProductName", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		productMap.put("ProductImagesCount", String.valueOf(getProductImageCount()));
		return productMap;
	}

	// Brand: Apple
	// Product Code: Product 17
	// Reward Points: 700
	// Availability: Out Of Stock
	public void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	// $1,000.00
	// Ex Tax: $1,000.00
	public void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exTaxprice = metaPriceList.get(1).getText().trim();
		productMap.put("price", price);
		productMap.put("exTaxprice", exTaxprice);
	}
}
