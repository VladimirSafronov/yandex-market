package ru.safronov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс главной страницы Яндекс Маркет
 */
public class YandexMarketMain {

  //Xpath кнопки "Каталог"
  protected static final String CATALOG_BUTTON_XPATH = "//div[@data-zone-name='catalog']";

  //Xpath поля для поиска
  public static final String SEARCH_FIELD_XPATH = "//input[@type='text' and @name='text']";

  //Xpath кнопки "Найти"
  protected static final String SUBMIT_BUTTON_XPATH = "//button[@type='submit']";

  protected WebDriver driver;
  protected WebDriverWait wait;

  //Кнопка "Каталог"
  protected final WebElement buttonCatalog;

  //Поле для поиска
  protected final WebElement searchField;

  //Кнопка "Найти"
  protected final WebElement buttonSubmit;

  public YandexMarketMain(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATALOG_BUTTON_XPATH)));
    this.buttonCatalog = driver.findElement(By.xpath(CATALOG_BUTTON_XPATH));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_FIELD_XPATH)));
    this.searchField = driver.findElement(By.xpath(SEARCH_FIELD_XPATH));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_BUTTON_XPATH)));
    this.buttonSubmit = driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
  }

  public WebElement getButtonCatalog() {
    return buttonCatalog;
  }

  public WebElement getSearchField() {
    return searchField;
  }

  public WebElement getButtonSubmit() {
    return buttonSubmit;
  }
}
