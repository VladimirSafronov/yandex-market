package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс страницы секции "Ноутбуки" Яндекс Маркет
 */
public class YandexMarketLaptops extends YandexMarketMain {

  //Xpath поля фильтра для цены "От"
  private static final String FIELD_FROM_PRICE_XPATH =
      "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'min')]";

  //Xpath поля фильтра для цены "До"
  private static final String FIELD_TO_PRICE_XPATH =
      "//input[starts-with(@id, 'range-filter-field-glprice') and contains(@id, 'max')]";

  //Поле фильтра для цены "От"
  private WebElement fieldFromPrice;

  //Поле фильтра для цены "До"
  private WebElement fieldToPrice;

  //Список выбираемых в фильтре компаний-производителей
  private WebElement[] products;

  public YandexMarketLaptops(WebDriver driver) {
    super(driver);

    wait.until(elementToBeClickable(By.xpath(FIELD_FROM_PRICE_XPATH)));
    this.fieldFromPrice = driver.findElement(By.xpath(FIELD_FROM_PRICE_XPATH));

    wait.until(elementToBeClickable(By.xpath(FIELD_TO_PRICE_XPATH)));
    this.fieldToPrice = driver.findElement(By.xpath(FIELD_TO_PRICE_XPATH));
  }

  public YandexMarketLaptops(WebDriver driver, String... chooseProducts) {
    super(driver);

    this.products = new WebElement[chooseProducts.length];
    for (int i = 0; i < products.length; i++) {
      wait.until(elementToBeClickable(By.xpath("//span[text()=\"" + chooseProducts[i] + "\"]")));
      products[i] = driver.findElement(By.xpath("//span[text()=\"" + chooseProducts[i] + "\"]"));
    }
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
