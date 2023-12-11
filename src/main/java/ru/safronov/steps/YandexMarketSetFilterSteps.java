package ru.safronov.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.safronov.helpers.Assertions;
import ru.safronov.pages.YandexMarketCatalog;
import ru.safronov.pages.YandexMarketLaptops;
import ru.safronov.pages.YandexMarketMain;

/**
 * Данный класс помогает реализовать тестирование при помощи степов - тесты короче, отчет понятнее
 */
public class YandexMarketSetFilterSteps {

  private static WebDriver driver;

  @Step("Переходим на сайт: {url}")
  public static void openSite(String url, WebDriver currentDriver) {
    driver = currentDriver;
    driver.get(url);
  }

  @Step("Переходим в Каталог")
  public static void clickButtonCatalog() {
    YandexMarketMain yandexMarketMain = new YandexMarketMain(driver);
    yandexMarketMain.getButtonCatalog().click();
  }

  @Step("Наводим курсор на раздел Ноутбуки и компьютеры")
  public static void moveToLaptopAndComputers() {
    YandexMarketCatalog yandexMarketCatalog = new YandexMarketCatalog(driver);
    new Actions(driver)
        .moveToElement(yandexMarketCatalog.getSectionLaptopsAndComputers())
        .perform();
  }

  @Step("Переходим в раздел Ноутбуки")
  public static void openLaptopCatalog() {
    YandexMarketCatalog yandexMarketCatalog = new YandexMarketCatalog(driver);
    yandexMarketCatalog.getSectionLaptops().click();
  }

  @Step("Проверка нахождения в разделе Ноутбуки")
  public static void checkLaptopPage(String title) {
    Assertions.assertTrue(driver.getTitle().equals(title),
        "Открылась страница с заголовком " + title);
  }

  @Step("Задаем параметр «Цена, Р» от {priceFrom} до {priceTo} рублей")
  public static void addFilterPrice(String priceFrom, String priceTo) {
    YandexMarketLaptops yandexMarketLaptops = new YandexMarketLaptops(driver);
    WebElement priceFromField = yandexMarketLaptops.getFieldFromPrice();
    priceFromField.click();
    priceFromField.clear();
    priceFromField.sendKeys(priceFrom);

    WebElement priceToField = yandexMarketLaptops.getFieldToPrice();
    priceToField.click();
    priceToField.clear();
    priceToField.sendKeys(priceTo);
  }

  @Step("Выбираем производителя(-ей): {products}")
  public static void chooseProduct(String... products) {
    YandexMarketLaptops yandexMarketLaptops = new YandexMarketLaptops(driver, products);
    WebElement[] chooseProducts = yandexMarketLaptops.getProducts();
    for (WebElement chooseProduct : chooseProducts) {
      chooseProduct.click();
    }
  }
}
