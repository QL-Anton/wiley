package wiley.test.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

/**
 * Created by Антон on 04.02.2018.
 */
public class SeleniumHelper {

  protected WebDriver driver;
  private By locator;
  private String text;

  public SeleniumHelper(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    WebElement element = driver.findElement(locator);
    Assert.assertNotNull(element);
    element.click();
  }

  public void type(By locator, String text) {
    this.locator = locator;
    this.text = text;
    //метод делает клик по полю ввода
    click(locator);
    //не пытается ввести значения, которые заменят дефолтные
    if (text != null) {
      String existingText = driver.findElement(locator).getAttribute("value");
      //не пытается вводить текст, если он совпадает с существующим
      if (!text.equals(existingText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }


  public void typeOnInputForm(String text){
    type(By.id("js-site-search-input"),text);

  }

    protected void navigateTo(String url) {
      driver.navigate().to(url);
    }



    public  String currentUrl(){
    return  driver.getCurrentUrl();
    }

    public  void moveToElem(WebElement element){
      Actions action = new Actions(driver);
      action.moveToElement(element).build().perform();

    }
  }
