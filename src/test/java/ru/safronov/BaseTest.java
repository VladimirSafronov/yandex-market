package ru.safronov;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class BaseTest {

  protected WebDriver chromeDriver;

  @BeforeEach
  public void before() {
    System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
    ChromeOptions options = new ChromeOptions();
    options.addArguments(CapabilityType.PAGE_LOAD_STRATEGY, "none");
    chromeDriver = new ChromeDriver(options);
//    chromeDriver = new ChromeDriver();

    chromeDriver.manage().window().maximize();
    chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
  }

  @AfterEach
  public void after() {
    chromeDriver.quit();
  }
}
