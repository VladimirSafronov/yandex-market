package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMarketLaptopAfterSearch {

  private final WebDriver driver;
  public final Wait<WebDriver> wait;
  private List<WebElement> laptops;
  private WebElement showMoreButton;

  public YandexMarketLaptopAfterSearch(WebDriver driver) {
    this.driver = driver;

    List<Class<? extends WebDriverException>> excs = List.of(
        NoSuchElementException.class, StaleElementReferenceException.class, TimeoutException.class
    );
    this.wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(5L))
        .pollingEvery(Duration.ofMillis(500L))
        .ignoreAll(excs);

    this.laptops = new ArrayList<>();
  }

  public void loadAndInitShowMoreButton() {
    wait.until(
        d -> {
          d.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));
          return true;
        });
    try {
      wait.until(elementToBeClickable(By.xpath("//span[text()=\"Показать ещё\"]")));
      this.showMoreButton = driver.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));
    } catch (TimeoutException e) {
      System.out.println("Обработали TimeoutException");
    }
  }

  public void loadLaptops() {
    wait.until(
        d -> {
          d.findElement(By.xpath("//div[@data-auto='pagination-page']/div[text()='1']"));
          return true;
        });
    WebElement bottomOfPage = driver.findElement(By.xpath(
        "//div[@data-auto='pagination-page']/div[text()='1']"));
    new Actions(driver)
        .moveToElement(bottomOfPage, 0, 0)
        .perform();

    laptops.clear();
    laptops.addAll(driver.findElements(By.xpath("//div[@data-known-size='249']")));
    System.out.println("laptops size = " + laptops.size());
  }

  public void loadPage() {
    new WebDriverWait(driver, 10).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
            .equals("complete"));
  }

  public List<WebElement> getLaptops() {
    return laptops;
  }

  public WebElement getShowMoreButton() {
    wait.until(
        d -> {
          d.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));
          return true;
        });
    return showMoreButton;
  }
}
