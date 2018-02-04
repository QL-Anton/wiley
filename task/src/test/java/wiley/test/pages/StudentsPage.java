package wiley.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Антон on 04.02.2018.
 */
public class StudentsPage extends  Page {
  public StudentsPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }


  @FindBy(xpath = "//a[text()='Students']")
  public WebElement students;


  @FindBy(css = "ul.breadcrumbs li.active")
  public WebElement studentsHeader;

  @FindBy(xpath = "//a[text()='WileyPLUS']")
  public WebElement referTowileyplus ;





}
