package ru.safronov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexMarketMain {

  protected WebDriver driver;
  private WebDriverWait wait;
  protected final WebElement buttonCatalog;
  protected final WebElement searchField;
  protected final WebElement buttonSubmit;

  public YandexMarketMain(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
        "//div[@data-zone-name='catalog']")));
    this.buttonCatalog = driver.findElement(By.xpath("//div[@data-zone-name='catalog']"));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
        "//input[@type='text' and @placeholder='Искать товары']")));
    this.searchField = driver.findElement(By.xpath(
        "//input[@type='text' and @placeholder='Искать товары']"));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    this.buttonSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
  }

  public WebElement getButtonCatalog() {
    return buttonCatalog;
  }
}
