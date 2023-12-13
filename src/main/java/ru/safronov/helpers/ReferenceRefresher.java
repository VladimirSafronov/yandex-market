package ru.safronov.helpers;

import static ru.safronov.pages.YandexMarketMain.SEARCH_FIELD_XPATH;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Данный класс помогает программе корректно отработать в цикле при проходе на первую и последнюю
 * страницы
 */
public class ReferenceRefresher {

  private static WebDriver driver;

  public static void setDriver(WebDriver driver) {
    ReferenceRefresher.driver = driver;
  }

  /**
   * переменнаая хранит количество попыток
   */
  private static final int RETRY_NUMBER = 5;

  /**
   * Метод помогает прогрузить товар на странице
   * @param goalXpath xpath элемента до которого производиться скроллинг
   * @return получилось ли просколлить до целевого элемента
   */
  public static boolean retryMoveToElement(String goalXpath) {
    for (int i = 1; i <= RETRY_NUMBER; i++) {
      try {
        new Actions(driver)
            .moveToElement(driver.findElement(By.xpath(SEARCH_FIELD_XPATH)))
            .moveToElement(driver.findElement(By.xpath(goalXpath)))
            .perform();
        return true;
      } catch (StaleElementReferenceException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return false;
  }

  /**
   *
   * @param goalXpath xpath элемента по которому осуществляется клик мышью
   * @return получилось ли кликнуть по целевому элементу
   */
  public static boolean retryClickToElement(String goalXpath) {

    for (int i = 1; i <= RETRY_NUMBER; i++) {
      try {
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
