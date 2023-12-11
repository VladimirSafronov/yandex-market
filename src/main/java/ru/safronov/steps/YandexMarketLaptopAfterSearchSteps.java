package ru.safronov.steps;

import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.safronov.helpers.Assertions;
import ru.safronov.helpers.ReferenceRefresher;
import ru.safronov.pages.YandexMarketLaptopAfterSearch;
import ru.safronov.pages.YandexMarketMain;

public class YandexMarketLaptopAfterSearchSteps {

  private static final int PRICE_MAX_LENGTH = 8;
  private static final char REGEX_PRICE_TO = '₽';
  private static YandexMarketLaptopAfterSearch page;

  @Step("Ожидаем загрузки страницы")
  public static void waitPageLoad(WebDriver currentDriver) {
    page = new YandexMarketLaptopAfterSearch(currentDriver);
    page.loadPage();
  }

  @Step("Проверяем минимальное количество товаров на первой странице")
  public static void checkProductCount(int count) {
    page.loadLaptops();
    Assertions.assertTrue(page.getLaptops().size() > count,
        "Количество товаров на первой странице равно или менее " + count);
  }

  private static String deleteAllSpaces(String str) {
    StringBuilder newStr = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (Character.isDigit(str.charAt(i))) {
        newStr.append(str.charAt(i));
      }
    }
    return newStr.toString();
  }

  @Step("Открываем полный список наименований продукта")
  private static void moveToEndSearch() {
    while (page.isShowMoreButtonExists()) {
//      page.initShowMoreButton();
      if(!ReferenceRefresher.retryClickToElement(YandexMarketLaptopAfterSearch.SHOW_MORE_BUTTON_XPATH)) {
        break;
      }
    }
    page.loadLaptops();
  }

  @Step("Возвращаемся на первую страницу с продуктами")
  private static void moveToFirstPage() {
    while (page.isPrevPageButtonExists()) {
//      page.initPrevPageButton();
      if(!ReferenceRefresher.retryClickToElement(YandexMarketLaptopAfterSearch.PREV_PAGE_BUTTON_XPATH)) {
        break;
      }
    }
  }

  @Step("Проверяем полный список ноутбуков")
  public static void checkProductList(String filterPriceFrom, String filterPriceTo,
      String... companies) {
    moveToEndSearch();
    Assertions.assertTrue(
        isProductsCorrespond(page.getLaptops(), filterPriceFrom, filterPriceTo, companies),
        "Список ноутбуков не соответствует фильтру");
  }

  @Step("Сохранить первое наименование продукта")
  public static String saveFirstProduct() {
    moveToFirstPage();
    return page.getFirstProductTitle();
  }

  @Step("Вводим в поисковую строку запомненное значение")
  public static void enterFirstProductTittle(WebDriver driver, String productTitle) {
    YandexMarketMain yandexMarketMain = new YandexMarketMain(driver);
    WebElement searchField = yandexMarketMain.getSearchField();
    searchField.click();
    searchField.clear();
    System.out.println(productTitle);
    searchField.sendKeys(productTitle);
  }

  @Step("Нажимаем на кнопку Найти")
  public static void clickButtonSubmit(WebDriver driver) {
    YandexMarketMain yandexMarketMain = new YandexMarketMain(driver);
    yandexMarketMain.getButtonSubmit().click();
  }

  @Step("Проверяем, что в результатах поиска, на первой странице, есть искомый товар")
  public static void checkProductExistsOnPage(String productTitle) {
    page.loadLaptops();
    Assertions.assertTrue(isProductExists(page.getLaptops(), productTitle),
        "На первой странице не содержится искомого товара");
  }

  private static boolean isProductExists(List<WebElement> products, String productTitle) {
    for (WebElement product : products) {
      if (product.getText().contains(productTitle)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isProductsCorrespond(List<WebElement> products, String filterPriceFrom,
      String filterPriceTo, String... companies) {
    int count = 0;
    for (WebElement product : products) {
      count++;
      String productInfo = product.getText();
      String productCompany = getProductCompany(productInfo);
      int productPrice = getProductPrice(productInfo);
      if (productPrice < Integer.parseInt(filterPriceFrom)
          || productPrice > Integer.parseInt(filterPriceTo)
          || !Arrays.asList(companies).contains(productCompany)) {
        System.out.println(productInfo);
        System.out.println(productCompany);
        System.out.println(productPrice);
        System.out.println(count);
        return false;
      }
    }
    return true;
  }

  private static String getProductCompany(String elementInfo) {
//    System.out.println(elementInfo);
    elementInfo = elementInfo.toLowerCase();
//    System.out.println("after toLowerCase(): " + elementInfo);
    if (elementInfo.contains("lenovo")) {
//      System.out.println("-----contains lenovo-----");
      return "Lenovo";
    } else if (elementInfo.contains("hp")) {
//      System.out.println("------contains hp-------");
      return "HP";
    } else {
//      System.out.println("-----other company-----");
      return "Неизвестный производитель";
    }
  }

  private static int getProductPrice(String elementInfo) {
    int priceIndexTo = elementInfo.lastIndexOf(REGEX_PRICE_TO);
    String price = deleteAllSpaces(
        elementInfo.substring(priceIndexTo - PRICE_MAX_LENGTH, priceIndexTo));
    return Integer.parseInt(price);
  }
}
