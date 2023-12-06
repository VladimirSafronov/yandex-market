package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMarketCatalog {

  private final WebDriver driver;
  private final WebDriverWait wait;
  private final WebElement sectionLaptopsAndComputers;
  private final WebElement sectionLaptops;

  public YandexMarketCatalog(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
    this.sectionLaptopsAndComputers = driver.findElement(By.xpath(
        "//ul[@role='tablist']/li[@role='tab']/a/span[text()=\"Ноутбуки и компьютеры\"]"));
    wait.until(visibilityOfElementLocated(By.xpath("//a[text()=\"Ноутбуки\"]")));
    this.sectionLaptops = driver.findElement(By.xpath("//a[text()=\"Ноутбуки\"]"));
  }

  public WebElement getSectionLaptopsAndComputers() {
    return sectionLaptopsAndComputers;
  }

  public WebElement getSectionLaptops() {
    return sectionLaptops;
  }
}
