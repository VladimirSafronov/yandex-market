package ru.safronov;

import static ru.safronov.steps.YandexMarketSetFilterSteps.*;
import static ru.safronov.steps.YandexMarketLaptopAfterSearchSteps.*;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class YandexMarketTest extends BaseTest {

  @Feature("Проверка наличия товара")
  @DisplayName("Проверка наличия товара - проверки в степах")
  @ParameterizedTest(name = "{displayName}: {arguments}")
  @MethodSource("ru.safronov.helpers.DataProvider#providerCheckingProduct")
  public void testFindProduct(String url, String laptopTitle, String filterPriceFrom, String filterPriceTo,
      String hpCompany, String lenovoCompany, int productsCount) {
    openSite(url, chromeDriver);
    clickButtonCatalog();
    moveToLaptopAndComputers();
    openLaptopCatalog();
    checkLaptopPage(laptopTitle);
    addFilterPrice(filterPriceFrom, filterPriceTo);
    chooseProduct(hpCompany, lenovoCompany);
    waitPageLoad(chromeDriver);
    checkProductCount(productsCount);
    checkProductList(filterPriceFrom, filterPriceTo, hpCompany, lenovoCompany);
    System.out.println();
  }
}
