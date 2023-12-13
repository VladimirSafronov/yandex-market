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

/**
 * Помогает реализовать тестирование при помощи степов - тесты короче, отчет понятнее.Данный класс -
 * степы работы с отфильтровнным продуктом
 */
public class YandexMarketLaptopAfterSearchSteps {

  /*
  Переменная используется для нахождения цены продукта из текста всего WebElement-а (цена может быть
  до миллиона)
   */
  private static final int PRICE_MAX_LENGTH = 8;

  //Символ рубля
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

  @Step("Открываем полный список наименований продукта")
  private static void moveLastPage() {
    while (page.isShowMoreButtonExists()) {
      page.loadPage();
      if (!ReferenceRefresher.retryClickToElement(
          YandexMarketLaptopAfterSearch.SHOW_MORE_BUTTON_XPATH)) {
        break;
      }
    }
    page.loadLaptops();
  }

  @Step("Возвращаемся на первую страницу с продуктами")
  private static void moveFirstPage() {
    while (page.isPrevPageButtonExists()) {
      page.loadPage();
      if (!ReferenceRefresher.retryClickToElement(
          YandexMarketLaptopAfterSearch.PREV_PAGE_BUTTON_XPATH)) {
        break;
      }
    }
  }

  @Step("Проверяем полный список ноутбуков")
  public static void checkProductList(String filterPriceFrom, String filterPriceTo,
      String... companies) {

    moveLastPage();
    Assertions.assertTrue(
        isProductsCorrespond(page.getLaptops(), filterPriceFrom, filterPriceTo, companies),
        "Список ноутбуков не соответствует фильтру");
  }

  @Step("Сохранить первое наименование продукта")
  public static String saveFirstProduct() {
    moveFirstPage();
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

  /**
   * Метод проверяет имеется ли продукт во всем списке продуктов
   *
   * @param products     - список продуктов
   * @param productTitle - заголовкок продукта
   */
  private static boolean isProductExists(List<WebElement> products, String productTitle) {
    for (WebElement product : products) {
      if (product.getText().contains(productTitle)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Метод проверяет соответствует ли список продуктов фильтру
   *
   * @param products        - список продуктов
   * @param filterPriceFrom - цена от
   * @param filterPriceTo   - цена до
   * @param companies       - выбранные компании
   */
  private static boolean isProductsCorrespond(List<WebElement> products, String filterPriceFrom,
      String filterPriceTo, String... companies) {

    for (WebElement product : products) {
      String productInfo = product.getText();
      String productCompany = getProductCompany(productInfo);
      int productPrice = getProductPrice(productInfo);
      if (!Arrays.asList(companies).contains(productCompany)) {
        Assertions.fail("В список продуктов попал производитель (" + productCompany +
            ") не из фильтра: " + Arrays.toString(companies));
        return false;
      } else if (productPrice < Integer.parseInt(filterPriceFrom)
          || productPrice > Integer.parseInt(filterPriceTo)) {
        Assertions.fail("Цена продукта: " + productPrice + " - не соответствует фильтру: "
            + filterPriceFrom + " - " + filterPriceTo);
        return false;
      }
    }
    return true;
  }

  /**
   * Метод находит название компании в тексте WebElement-а
   *
   * @param elementInfo - текст WebElement-а
   * @return - название искомой компании (либо иной производитель)
   */
  private static String getProductCompany(String elementInfo) {
    elementInfo = elementInfo.toLowerCase();
    if (elementInfo.contains("lenovo")) {
      return "Lenovo";
    } else if (elementInfo.contains("hp") || (elementInfo.contains("нр"))) {
      return "HP";
    } else {
      return "Иной производитель. " + elementInfo;
    }
  }

  /**
   * Метод удаления нечисловых символов (в том числе убирает нестандартный пробел - &thinsp;)
   *
   * @param str Текстовые данные, содержащие цену
   * @return строка, содержащая числовые символы
   */
  private static String deleteAllSpaces(String str) {
    StringBuilder newStr = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (Character.isDigit(str.charAt(i))) {
        newStr.append(str.charAt(i));
      }
    }
    return newStr.toString();
  }

  /**
   * Метод находит цену товара в тексте WebElement-а
   *
   * @param elementInfo - текст WebElement-а
   * @return - цена товара
   */
  private static int getProductPrice(String elementInfo) {
    int priceIndexTo = elementInfo.lastIndexOf(REGEX_PRICE_TO);
    String price = deleteAllSpaces(
        elementInfo.substring(priceIndexTo - PRICE_MAX_LENGTH, priceIndexTo));
    return Integer.parseInt(price);
  }
}
