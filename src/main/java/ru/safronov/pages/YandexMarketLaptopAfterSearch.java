package ru.safronov.pages;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMarketLaptopAfterSearch extends YandexMarketMain {

  public final Wait<WebDriver> wait;
  private List<WebElement> laptops;
  private WebElement showMoreButton;
  private WebElement searchPager;

  public YandexMarketLaptopAfterSearch(WebDriver driver) {
    super(driver);

    List<Class<? extends WebDriverException>> exceptions = List.of(
        NoSuchElementException.class, StaleElementReferenceException.class, TimeoutException.class
    );
    this.wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(5L))
        .pollingEvery(Duration.ofMillis(500L))
        .ignoreAll(exceptions);

    this.laptops = new ArrayList<>();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
        "//div[@data-zone-name='SearchPager']")));
    this.searchPager = driver.findElement(By.xpath("//div[@data-zone-name='SearchPager']"));
  }

  public void initShowMoreButton() {
    if (isShowMoreButtonExists()) {
      this.showMoreButton = driver.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));
    }
  }

  public void loadLaptops() {
    searchField.click();
    buttonSubmit.click();

    new Actions(driver)
        .moveToElement(searchPager, 0, 0)
        .perform();

    laptops.clear();
    laptops.addAll(driver.findElements(By.xpath("//div[@data-known-size='249']")));
    System.out.println("laptops size = " + laptops.size());
  }

  public boolean isShowMoreButtonExists() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return driver.findElements(By.xpath("//span[text()=\"Показать ещё\"]")).size() > 0;
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
//    wait.until(
//        d -> {
//          d.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));
//          return true;
//        });
    return showMoreButton;
  }
}
