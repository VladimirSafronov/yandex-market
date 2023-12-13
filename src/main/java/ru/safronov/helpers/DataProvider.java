package ru.safronov.helpers;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * Данный класс содержит данные, используемые при параметризации в @MethodSource
 */
public class DataProvider {

  /*
  Список компаний, выбранных в фильтре, используется для проверки соответствия результата работы
  программы. Содержит написание компаний используя русские символы.
   */
  public static String[] companies = new String[]{"HP", "Hp", "hp", "НР", "Нр", "Lenovo", "LENOVO",
      "lenovo"};

  /**
   * Метод содержит аргументы по которым проводится тестирование
   */
  public static Stream<Arguments> providerCheckingProduct() {
    return Stream.of(Arguments.of(
        "https://market.yandex.ru/", "Ноутбуки — купить по низкой цене на Яндекс Маркете",
        "10000", "30000", "HP", "Lenovo", 12, companies
    ));
  }
}
