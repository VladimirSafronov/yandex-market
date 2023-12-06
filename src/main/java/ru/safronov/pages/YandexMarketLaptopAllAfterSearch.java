package ru.safronov.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class YandexMarketLaptopAllAfterSearch extends YandexMarketLaptopAfterSearch {

  private final List<Laptop> allProducts;
  public YandexMarketLaptopAllAfterSearch(WebDriver driver) {
    super(driver);
    allProducts = new ArrayList<>();
  }

//  @Override
//  public List<WebElement> getLaptops() {
//    return super.getLaptops();
//  }

  class Laptop {
    String name;
    int price;

    public Laptop(String name, int price) {
      this.name = name;
      this.price = price;
    }
  }
}
