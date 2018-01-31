package wiley.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by Антон on 30.01.2018.
 */
public class WileyHomeWorkTests extends TestBase {


  @Test
  public void testWileyHomeWork() {
    wd.get("http://www.wiley.com/WileyCDA");


    //Check the following links are displayed in the top menu
    Assert.assertTrue(wd.findElement(By.xpath("//a[text()='Resources']")).isDisplayed());
    Assert.assertTrue(wd.findElement(By.xpath("//a[text()='Subjects']")).isDisplayed());
    Assert.assertTrue(wd.findElement(By.xpath("//a[text()='About']")).isDisplayed());


    //Check items under Resources for sub-header
    wd.findElement(By.xpath("//a[text()='Resources']")).click();
    //There are 10 items under resources sub-header
    int k = wd.findElements(By.cssSelector("li.hover div.dropdown-items-wrapper")).size();
    Assert.assertEquals(k, 10);


    //Check titles
    List<String> titles = new ArrayList<>();
    List<String> titles2 = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      String title = wd.findElements(By.cssSelector("li.hover div.dropdown-items-wrapper")).get(i).getAttribute("innerText");
      titles.add(title.replaceAll("[^A-Za-zА-Яа-я0-9]", ""));
    }

    List<String> defTitles = Arrays.asList("AUTHORS", "CORPORATIONS", "INSTITUTIONS", "INSTRUCTORS", "LIBRARIANS", "PROFESSIONALS", "RESEARCHERS", "RESELLERS", "SOCIETIES", "STUDENTS");
    Assert.assertEquals(titles, defTitles);

    //Click “Students”
    wd.findElement(By.xpath("//a[text()='Students']")).click();

    //Check that https://www.wiley.com/en-ru/students url is opened
    String currentUrl = wd.getCurrentUrl();
    String studentsPage = "https://www.wiley.com/en-ru/students";
    Assert.assertEquals(currentUrl, studentsPage);

    //Check that “Students” header is displayed
    Assert.assertTrue(wd.findElement(By.cssSelector("ul.breadcrumbs li.active")).isDisplayed());

    //Check “WileyPLUS” link is present on page and it leads to http://wileyplus.wiley.com
    String urlWileyPLUS = wd.findElement(By.xpath("//a[text()='WileyPLUS']")).getAttribute("href");
    Assert.assertEquals(urlWileyPLUS, "http://wileyplus.wiley.com/");

    //Go to “Subjects”
    WebElement subject = wd.findElement(By.xpath("//a[text()='Subjects']"));
    Actions action = new Actions(wd);
    action.moveToElement(subject).build().perform();

    //select “E-L”
    WebElement e_l = wd.findElement(By.xpath("//a[text()='E-L']"));
    wait.until(visibilityOf(e_l));
    action.moveToElement(e_l).build().perform();

    //select Education
    WebElement education = wd.findElement(By.xpath("//a[text()='Education']"));
    wait.until(visibilityOf(education));
    action.moveToElement(education).build().perform();
    education.click();

    //Check “Education” header is displayed
    Assert.assertTrue(wd.findElement(By.cssSelector("ul.breadcrumbs li.active")).isDisplayed());


    //13 items are displayed under “Subjects”
    int countItemsUnderSubject = wd.findElements(By.cssSelector("div.side-panel li")).size();
    Assert.assertEquals(countItemsUnderSubject, 13);

    List<String> texts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("div.side-panel li"));
    List<String> textsInformation = Arrays.asList("Information & Library Science", "Education & Public Policy",
            "K-12 General", "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)",
            "Curriculum Tools- General", "Special Educational Needs", "Theory of Education",
            "Education Special Topics", "Educational Research & Statistics", "Literacy & Reading", "Classroom Management"
    );
    for (int i = 0; i < countItemsUnderSubject; i++) {

      Assert.assertTrue(elements.get(i).isDisplayed());
      texts.add(elements.get(i).getAttribute("innerText"));
    }
    Assert.assertEquals(texts, textsInformation);

    //goToHomePage
    wd.findElement(By.cssSelector("div.main-navigation-menu div.simple-responsive-banner-component")).click();

    //Click Search
    wd.findElement(By.xpath("//button[text()='Search']")).click();

  }
}
