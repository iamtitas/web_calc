package com.nagarro.CalculatorTK;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CalculatorSeleniumTest {

	private WebDriver driver;
	private String baseUrl;

	@BeforeTest
	@Parameters({ "hostname", "port", "context" })
	public void setup(String hostname, String port, String context) {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--remote-allow-origins=*");
		options.setBinary("/usr/bin/google-chrome");
		driver = new ChromeDriver(options);

		baseUrl = "http://" + hostname + ":" + port + "/" + context + "/";
	}

	@Test
	public void testAddition() {
		driver.get(baseUrl);
		performCalculation(5, 3, "+");
		WebElement result = driver.findElement(By.tagName("p"));
		assertEquals("The result is: 8", result.getText());
	}

	@Test
	public void testSubtraction() {
		driver.get(baseUrl);
		performCalculation(10, 4, "-");
		WebElement result = driver.findElement(By.tagName("p"));
		assertEquals("The result is: 6", result.getText());
	}

	@Test
	public void testMultiplication() {
		driver.get(baseUrl);
		performCalculation(7, 6, "*");
		WebElement result = driver.findElement(By.tagName("p"));
		assertEquals("The result is: 42", result.getText());
	}

	@Test
	public void testAddition2() {
		driver.get(baseUrl);
		performCalculation(10, 9, "+");
		WebElement result = driver.findElement(By.tagName("p"));
		assertEquals("The result is: 19", result.getText());
	}

	private void performCalculation(int num1, int num2, String operator) {
		WebElement num1Field = driver.findElement(By.name("num1"));
		WebElement num2Field = driver.findElement(By.name("num2"));
		WebElement operatorField = driver.findElement(By.name("operator"));

		num1Field.sendKeys(String.valueOf(num1));
		operatorField.sendKeys(operator);
		num2Field.sendKeys(String.valueOf(num2));

		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
		submitButton.click();
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
