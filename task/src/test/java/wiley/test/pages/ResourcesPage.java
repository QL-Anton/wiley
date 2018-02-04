package wiley.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 04.02.2018.
 */
public class ResourcesPage extends  Page {
  public ResourcesPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }



  public Set<String> getTitlesOfItemsUnderResources() {
    Set<String> titles = new HashSet<>();
    List<WebElement> currentTitles = driver.findElements(By.cssSelector("li.hover div.dropdown-items-wrapper"));
    for (WebElement currentTitle : currentTitles) {
      String title = currentTitle.getAttribute("innerText");
      titles.add(title.replaceAll("[^A-Za-zА-Яа-я0-9]", ""));
    }
    return  titles;
  }
}
