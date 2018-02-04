package wiley.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Антон on 04.02.2018.
 */
public class Page {

  protected WebDriver driver;
  protected WebDriverWait wait;

  public Page(WebDriver driver) {
    this.driver = driver;
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 30);

  }
}
