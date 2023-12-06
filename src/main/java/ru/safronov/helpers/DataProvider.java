package ru.safronov.helpers;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class DataProvider {

  public static Stream<Arguments> providerCheckingProduct() {
    return Stream.of(Arguments.of(
        "https://market.yandex.ru/", "Ноутбуки — купить по низкой цене на Яндекс Маркете",
        "10000", "30000", "HP", "Lenovo", 12
    ));
  }
}