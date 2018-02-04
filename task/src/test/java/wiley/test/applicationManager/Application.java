package wiley.test.applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import wiley.test.pages.EducationPage;
import wiley.test.pages.MainPage;
import wiley.test.pages.ResourcesPage;
import wiley.test.pages.StudentsPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by Антон on 04.02.2018.
 */
public class Application {

  private WebDriver driver;
  private WebDriverWait wait;
  private EducationPage educationPage;
  private MainPage mainPage;
  private ResourcesPage resourcesPage;
  private StudentsPage studentsPage;
  private  SeleniumHelper seleniumHelper;

  public EducationPage educationPage() {
    return educationPage;
  }

  public MainPage mainPage() {
    return mainPage;
  }

  public ResourcesPage resourcesPage() {
    return resourcesPage;
  }

  public StudentsPage studentsPage() {
    return studentsPage;
  }

  public SeleniumHelper seleniumHelper() {
    return seleniumHelper;
  }



  public Application() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 30);

    educationPage = new EducationPage(driver);
    mainPage = new MainPage(driver);
    resourcesPage = new ResourcesPage(driver);
    studentsPage = new StudentsPage(driver);
    seleniumHelper=new SeleniumHelper(driver);

  }


  public void goToStudents(){
    studentsPage.students.click();
  }


  public  void goToMainPage(){
    driver.get("http://www.wiley.com/WileyCDA");
  }

  public  void goToMainPageThroughLogo(){
    mainPage.logo.click();
  }

  public  void initSearch(){
    mainPage.search.click();
  }

  public  void  goToResources(){
    mainPage.resources.click();
  }

  public  void  goToEducation(){
    seleniumHelper.moveToElem(mainPage.subjects);
    seleniumHelper.moveToElem(educationPage.E_L);
    educationPage.education.click();
  }

  public  void  waitRelatedContent(){
    WebElement relatedContent = mainPage.relatedContent;
    wait.until(visibilityOf(relatedContent));
  }


  public void quit() {
    driver.quit();
  }





}
