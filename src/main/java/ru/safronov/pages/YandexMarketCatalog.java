package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexMarketCatalog extends YandexMarketMain {

  private static final String SEARCH_LAPTOPS_AND_COMPUTERS_XPATH =
      "//ul[@role='tablist']/li[@role='tab']/a/span[text()=\"Ноутбуки и компьютеры\"]";
  private static final String SECTION_LAPTOPS_XPATH = "//a[text()=\"Ноутбуки\"]";
  private final WebElement sectionLaptopsAndComputers;
  private final WebElement sectionLaptops;

  public YandexMarketCatalog(WebDriver driver) {
    super(driver);
    this.sectionLaptopsAndComputers = driver.findElement(
        By.xpath(SEARCH_LAPTOPS_AND_COMPUTERS_XPATH));

    wait.until(visibilityOfElementLocated(By.xpath(SECTION_LAPTOPS_XPATH)));
    this.sectionLaptops = driver.findElement(By.xpath(SECTION_LAPTOPS_XPATH));
  }

  public WebElement getSectionLaptopsAndComputers() {
    return sectionLaptopsAndComputers;
  }

  public WebElement getSectionLaptops() {
    return sectionLaptops;
  }
}
