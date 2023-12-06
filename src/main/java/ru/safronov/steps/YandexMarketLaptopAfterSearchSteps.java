package ru.safronov.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.safronov.helpers.Assertions;
import ru.safronov.pages.YandexMarketLaptopAfterSearch;

public class YandexMarketLaptopAfterSearchSteps {

  private static YandexMarketLaptopAfterSearch page;

  @Step("Ожидаем загрузки страницы")
  public static void waitPageLoad(WebDriver currentDriver) {
    page = new YandexMarketLaptopAfterSearch(currentDriver);
    page.loadAndInitShowMoreButton();
  }

  @Step("Проверяем минимальное количество товаров на первой странице")
  public static void checkProductCount(int count) {
    System.out.println(page.getLaptops().size());
    Assertions.assertTrue(page.getLaptops().size() > count,
        "Количество товаров на первой странице равно или менее " + count);
  }

  @Step("Открываем полный список наименований продукта")
  private static void moveToEndSearch() {
    while (!page.getShowMoreButton().isDisplayed()) {
      System.out.println("in cicle in moveToEndSearch");
      page.getShowMoreButton().click();
      page.loadAndInitShowMoreButton();
    }
  }

  @Step("Проверяем полный список ноутбуков")
  public static void checkProductList() {
    moveToEndSearch();
  }
}
