package ru.safronov;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

  protected WebDriver chromeDriver;

  @BeforeEach
  public void before() {
    System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments(CapabilityType.PAGE_LOAD_STRATEGY, "none");
//    chromeDriver = new ChromeDriver(options);
    chromeDriver = new ChromeDriver();

    chromeDriver.manage().window().maximize();
    chromeDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    chromeDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    chromeDriver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
  }

  @AfterEach
  public void after() {
    chromeDriver.quit();
  }
}
