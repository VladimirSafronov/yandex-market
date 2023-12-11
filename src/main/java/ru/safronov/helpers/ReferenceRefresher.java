package ru.safronov.helpers;

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

  public static boolean retryMoveToElement(String xpath) {
    for (int i = 0; i < RETRY_NUMBER; i++) {
      try {
        new Actions(driver)
            .moveToElement(driver.findElement(By.xpath(xpath)), 0, 0)
            .perform();
        return true;
      } catch (StaleElementReferenceException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return false;
  }

  public static boolean retryClickToElement(String xpath) {

    for (int i = 1; i <= RETRY_NUMBER; i++) {
      //TODO: устранить периодическое зацикливание с кнопкой
      try {
        System.out.println(
            "In retryClickToElement: attemped to find element " + i + ". Xpath = " + xpath);

        driver.findElement(By.xpath(xpath)).click();
        return true;
      } catch (StaleElementReferenceException ex) {
        System.out.println(ex.getMessage());
      } catch (NoSuchElementException ex) {
        System.out.println("In retryClickToElement: element not found. Xpath = " + xpath);
        return false;
      } catch (RuntimeException ex) {
        System.out.println("In retryClickToElement: error - " + ex.getClass().getName());
        throw ex;
      }
    }
    return false;
  }
}
