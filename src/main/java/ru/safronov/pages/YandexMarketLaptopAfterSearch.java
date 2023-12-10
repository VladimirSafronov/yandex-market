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

  private static final String LAPTOP_XPATH = "//div[@data-known-size='249']";
  private static final String SHOW_MORE_BUTTON_XPATH = "//span[text()=\"Показать ещё\"]";
  private static final String SEARCH_PAGER_XPATH = "//div[@data-zone-name='SearchPager']";
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
        SEARCH_PAGER_XPATH)));
    this.searchPager = driver.findElement(By.xpath(SEARCH_PAGER_XPATH));
  }

  public void initShowMoreButton() {
    if (isShowMoreButtonExists()) {
      this.showMoreButton = driver.findElement(By.xpath(SHOW_MORE_BUTTON_XPATH));
    }
  }

  public void loadLaptops() {
    searchField.click();
    buttonSubmit.click();

    new Actions(driver)
        .moveToElement(searchPager, 0, 0)
        .perform();

    laptops.clear();
    laptops.addAll(driver.findElements(By.xpath(LAPTOP_XPATH)));
    System.out.println("laptops size = " + laptops.size());
  }

  public boolean isShowMoreButtonExists() {
    //TODO: придумать как избавиться от Thread.sleep()
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return driver.findElements(By.xpath(SHOW_MORE_BUTTON_XPATH)).size() > 0;
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
    return showMoreButton;
  }
}
