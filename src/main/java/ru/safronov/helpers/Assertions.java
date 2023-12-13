package ru.safronov.helpers;

/**
 * Данный класс переопределяет классический Assertions чтобы assert отображался в allure всегда (не
 * только, когда тест упадет)
 */
public class Assertions {

  public static void assertTrue(boolean condition, String message) {
    org.junit.jupiter.api.Assertions.assertTrue(condition, message);
  }

  public static void fail(String message) {
    org.junit.jupiter.api.Assertions.fail(message);
  }
}
