package ru.safronov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YandexMarketMain {

  private WebDriver driver;
  private WebDriverWait wait;
  private final WebElement buttonCatalog;

  public YandexMarketMain(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
    wait.until(
        org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//div[@data-zone-name='catalog']")));
    this.buttonCatalog = driver.findElement(By.xpath("//div[@data-zone-name='catalog']"));
  }

  public WebElement getButtonCatalog() {
    return buttonCatalog;
  }
}
