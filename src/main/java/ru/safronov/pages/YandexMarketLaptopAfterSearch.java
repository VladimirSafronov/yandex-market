package ru.safronov.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMarketLaptopAfterSearch {

  private final WebDriver driver;
//  private final Wait<WebDriver> wait;
    private final WebDriverWait wait;
  private List<WebElement> laptops;
  private WebElement showMoreButton;

  public YandexMarketLaptopAfterSearch(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
//    wait.until(elementToBeClickable(By.xpath("//span[text()=\"Показать ещё\"]")));
//    this.showMore = driver.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));

//    this.wait = new FluentWait<>(driver)
//        .withTimeout(Duration.ofSeconds(10L))
//        .pollingEvery(Duration.ofMillis(500L))
//        .ignoring(NoSuchElementException.class, TimeoutException.class);

//    this.showMore = wait.until(driver1 -> driver1.findElement(By.xpath("//span[text()=\"Показать ещё\"]")));
    this.laptops = new ArrayList<>();
  }

  public void loadAndInitShowMoreButton() {
    wait.until(elementToBeClickable((By.xpath("//span[text()=\"Показать ещё\"]"))));
    this.showMoreButton = driver.findElement(By.xpath("//span[text()=\"Показать ещё\"]"));

    new Actions(driver)
        .moveToElement(showMoreButton, 0, 0)
        .perform();

    laptops.addAll(driver.findElements(By.xpath("//div[@data-known-size='249']")));
    System.out.println("After loadAndInitShowMoreButton " + laptops.size());
  }

  public List<WebElement> getLaptops() {
    return laptops;
  }

  public WebElement getShowMoreButton() {
    return showMoreButton;
  }

  public void setShowMoreButton(WebElement showMoreButton) {
    this.showMoreButton = showMoreButton;
  }
}
