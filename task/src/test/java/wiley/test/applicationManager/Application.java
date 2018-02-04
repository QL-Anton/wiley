package wiley.test.applicationManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Антон on 04.02.2018.
 */
public class Application {

  private WebDriver driver;
  public WebDriverWait wait;

  public Application() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 30);


  }

  public void quit() {
    driver.quit();
  }
}
