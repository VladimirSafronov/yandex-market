package ru.safronov.helpers;

public class Assertions {

  public static void assertTrue(boolean condition, String message) {
    org.junit.jupiter.api.Assertions.assertTrue(condition, message);
  }

  public static void fail(String message) {
    org.junit.jupiter.api.Assertions.fail(message);
  }
}
