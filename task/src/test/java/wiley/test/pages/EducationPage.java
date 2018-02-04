package wiley.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 04.02.2018.
 */
public class EducationPage extends  Page {
  public EducationPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//a[text()='E-L']")
  public WebElement E_L;

  @FindBy(xpath = "//a[text()='Education']")
  public WebElement education;

  @FindBy(css = "ul.breadcrumbs li.active")
  public WebElement educationHeader;

  @FindBy(css = "div.side-panel li")
  public List<WebElement> subjectItems;


  public Set<String> getTitlesOfSubject() {
    Set<String> titles = new HashSet<>();
    List<WebElement> currentTitlesOfSubject = driver.findElements(By.cssSelector("div.side-panel li"));
    for (WebElement currentTitle : currentTitlesOfSubject) {
      String title = currentTitle.getAttribute("innerText");
      titles.add(title);
    }
    return  titles;
  }

  public Set<WebElement> getItemsOfSubject() {
    Set<WebElement> items = new HashSet<>();
    List<WebElement> currentItemsOfSubject = subjectItems;
    for (WebElement item : currentItemsOfSubject) {
      items.add(item);
    }
    return  items;
  }


}
