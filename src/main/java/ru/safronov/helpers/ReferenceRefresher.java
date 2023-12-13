package ru.safronov.helpers;

import static ru.safronov.pages.YandexMarketMain.SEARCH_FIELD_XPATH;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ReferenceRefresher {

  private static WebDriver driver;

  public static void setDriver(WebDriver driver) {
    ReferenceRefresher.driver = driver;
  }

  private static final int RETRY_NUMBER = 5;

  public static boolean retryMoveToElement(String downPageXpath) {
    for (int i = 0; i < RETRY_NUMBER; i++) {
      try {
        new Actions(driver)
            .moveToElement(driver.findElement(By.xpath(SEARCH_FIELD_XPATH)))
            .moveToElement(driver.findElement(By.xpath(downPageXpath)))
            .perform();
        return true;
      } catch (StaleElementReferenceException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return false;
  }

  public static boolean retryClickToElement(String goalXpath) {

    for (int i = 1; i <= RETRY_NUMBER; i++) {
      try {
        System.out.println(
            "In retryClickToElement: attempted to find element " + i + ". Xpath = " + goalXpath);

        new Actions(driver)
            .moveToElement(driver.findElement(By.xpath(SEARCH_FIELD_XPATH)))
            .moveToElement(driver.findElement(By.xpath(goalXpath)))
            .perform();
        driver.findElement(By.xpath(goalXpath)).click();

        return true;
      } catch (StaleElementReferenceException ex) {
        System.out.println(ex.getMessage());
      } catch (NoSuchElementException ex) {
        System.out.println("In retryClickToElement: element not found. Xpath = " + goalXpath);
        return false;
      } catch (RuntimeException ex) {
        System.out.println("In retryClickToElement: error - " + ex.getClass().getName());
        throw ex;
      }
    }
    return false;
  }
}
