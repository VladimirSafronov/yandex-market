package ru.safronov.helpers;

import io.qameta.allure.Step;

public class Assertions {

  @Step("Проверяем что открылась страница с заголовком: {message}")
  public static void assertTrue(boolean condition, String message) {
    org.junit.jupiter.api.Assertions.assertTrue(condition, message);
  }

  public static void fail(String message) {
    org.junit.jupiter.api.Assertions.fail(message);
  }
}
