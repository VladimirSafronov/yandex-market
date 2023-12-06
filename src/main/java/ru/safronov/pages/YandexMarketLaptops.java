package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMarketLaptops {

  private final WebDriver driver;
  private final WebDriverWait wait;
  private WebElement fieldFromPrice;
  private WebElement fieldToPrice;
  private WebElement[] products;

  public YandexMarketLaptops(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);

    wait.until(elementToBeClickable(By.xpath(
        "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]")));
    this.fieldFromPrice = driver.findElement(By.xpath(
        "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]"));

    wait.until(elementToBeClickable(By.xpath(
        "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]")));
    this.fieldToPrice = driver.findElement(By.xpath(
        "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]"));
  }

  public YandexMarketLaptops(WebDriver driver, String... chooseProducts) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);

    this.products = new WebElement[chooseProducts.length];
    for (int i = 0; i < products.length; i++) {
      wait.until(elementToBeClickable(By.xpath("//span[text()=\"" + chooseProducts[i] + "\"]")));
      products[i] = driver.findElement(By.xpath("//span[text()=\"" + chooseProducts[i] + "\"]"));
    }
//    wait.until(elementToBeClickable(By.xpath("//span[text()=\"Lenovo\"]")));
//    this.checkBoxLenovo = driver.findElement(By.xpath("//span[text()=\"Lenovo\"]"));
//
//    wait.until(elementToBeClickable(By.xpath("//span[text()=\"HP\"]")));
//    this.checkBoxHP = driver.findElement(By.xpath("//span[text()=\"HP\"]"));
  }

  public WebElement getFieldFromPrice() {
    return fieldFromPrice;
  }

  public WebElement getFieldToPrice() {
    return fieldToPrice;
  }

  public WebElement[] getProducts() {
    return products;
  }
}
