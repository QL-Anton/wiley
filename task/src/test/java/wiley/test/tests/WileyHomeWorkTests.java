package wiley.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import wiley.test.tests.TestBase;

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

    //Type math
    wd.findElement(By.id("js-site-search-input")).click();
    wd.findElement(By.id("js-site-search-input")).clear();
    wd.findElement(By.id("js-site-search-input")).sendKeys("math");

    //Area with related content is displayed right under the search header
    WebElement relatedContent = wd.findElement(By.id("ui-id-2"));
    wait.until(visibilityOf(relatedContent));
    Assert.assertTrue(relatedContent.isDisplayed());

    //On the left side, it has 4 words starting with “Math”
    int countWordsOnTheLeft = wd.findElements(By.cssSelector("div.ui-menu-item a")).size();
    Assert.assertEquals(countWordsOnTheLeft,4);
    List<String> words = new ArrayList<>();
    for (int i = 0; i < countWordsOnTheLeft; i++) {
      String word = wd.findElements(By.cssSelector("div.ui-menu-item a")).get(i).getAttribute("innerText");
      words.add(word);
    }
    for (String word : words) {
      Assert.assertEquals(word.substring(0, 4), "Math");
    }


    //On the right side, there are 4 titles under “Related content” and each title contain “Math” word
    List<WebElement> relatedContents = wd.findElement(By.className("related-content-products")).findElements(By.className("product-title"));

    Assert.assertEquals(relatedContents.size(), 4);

    for (WebElement element : relatedContents) {
      String textOfProduct = element.getAttribute("innerText");
      Assert.assertTrue(textOfProduct.contains("Math"));
    }


    //Click “SEARCH” button
    wd.findElement(By.xpath("//button[text()='Search']")).click();


    List<WebElement> titlesOfContent=wd.findElements(By.cssSelector("h3.product-title"));
    //Only titles containing “Math” are displayed
    for(WebElement titleOfContent:titlesOfContent){
      Assert.assertTrue(titleOfContent.getAttribute("innerText").contains("Math"));
      Assert.assertTrue(titleOfContent.isDisplayed());
    }
    //There are 10 titles
    Assert.assertEquals(titlesOfContent.size(),10);



    //Each title has at least one “Add to Cart” button
    List<WebElement> items=wd.findElements(By.cssSelector("div.product-content"));
    for (WebElement item:items){
     WebElement addToCartButton=item.findElement(By.cssSelector("div.SearchResultsList-AddToLegacyCartAction"));
     Assert.assertTrue(addToCartButton.isDisplayed());
    }


    //Click “SEARCH” button
    wd.findElement(By.xpath("//button[text()='Search']")).click();


  }


}
