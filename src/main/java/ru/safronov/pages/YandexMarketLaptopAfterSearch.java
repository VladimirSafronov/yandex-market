package ru.safronov.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.safronov.helpers.Assertions;
import ru.safronov.helpers.ReferenceRefresher;

/**
 * Класс страницы Яндекс Маркет после использования фильтра
 */
public class YandexMarketLaptopAfterSearch extends YandexMarketMain {

  //Xpath одного товара
  private static final String LAPTOP_XPATH = "//div[@data-known-size='249']";

  //Xpath кнопки "Показать ещё"
  public static final String SHOW_MORE_BUTTON_XPATH = "//span[text()=\"Показать ещё\"]";

  //Xpath списка страниц с товарами
  private static final String SEARCH_PAGER_XPATH = "//div[@data-zone-name='SearchPager']";

  //Xpath кнопки "Назад" в списке страниц
  public static final String PREV_PAGE_BUTTON_XPATH = "//div[@data-auto='pagination-prev']";

  //Xpath названия товара
  private static final String PRODUCT_TITLE_XPATH = "//h3[@data-auto='snippet-title-header']";

  //Список всех отфильтрованных ранее товаров
  private List<WebElement> laptops;

  public YandexMarketLaptopAfterSearch(WebDriver driver) {
    super(driver);
    this.laptops = new ArrayList<>();
  }

  public void loadLaptops() {
    loadPage();
    if (!ReferenceRefresher.retryMoveToElement(SEARCH_PAGER_XPATH)) {
      Assertions.fail("Не получилось прокрутить экран до ссылок на страницы");
    }

    laptops.clear();
    laptops.addAll(driver.findElements(By.xpath(LAPTOP_XPATH)));
    System.out.println("Laptops size = " + laptops.size());
  }

  public boolean isShowMoreButtonExists() {
    return driver.findElements(By.xpath(SHOW_MORE_BUTTON_XPATH)).size() > 0;
  }

  public boolean isPrevPageButtonExists() {
    return driver.findElements(By.xpath(PREV_PAGE_BUTTON_XPATH)).size() > 0;
  }

  public void loadPage() {
    new WebDriverWait(driver, 10).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
            .equals("complete"));
  }

  public String getFirstProductTitle() {
    return driver.findElement(By.xpath(PRODUCT_TITLE_XPATH)).getText();
  }

  public List<WebElement> getLaptops() {
    return laptops;
  }
}
