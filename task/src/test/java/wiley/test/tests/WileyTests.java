package wiley.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Антон on 04.02.2018.
 */
public class WileyTests extends TestBase {


  @Test
  public void testWiley() {

    app.goToMainPage();

    //Check the following links are displayed in the top menu
    Assert.assertTrue(app.mainPage().resources.isDisplayed());
    Assert.assertTrue(app.mainPage().subjects.isDisplayed());
    Assert.assertTrue(app.mainPage().about.isDisplayed());

    //Check items under Resources for sub-header
    app.goToResources();
    Set<String> currentTitlesUnderResources = app.resourcesPage().getTitlesOfItemsUnderResources();

    //There are 10 items under resources sub-header
    Assert.assertEquals(currentTitlesUnderResources.size(), 10);
    Set<String> defTitles = Stream.of("AUTHORS", "CORPORATIONS", "INSTITUTIONS", "INSTRUCTORS",
            "LIBRARIANS", "PROFESSIONALS", "RESEARCHERS", "RESELLERS", "SOCIETIES", "STUDENTS")
            .collect(Collectors.toCollection(HashSet::new));

    //Check titles
    Assert.assertEquals(currentTitlesUnderResources, defTitles);

    //Click “Students”
    app.goToStudents();

    //Check that https://www.wiley.com/en-ru/students url is opened
    String currentUrl = app.seleniumHelper().currentUrl();
    String studentsPage = "https://www.wiley.com/en-ru/students";
    Assert.assertEquals(currentUrl, studentsPage);

    //Check that “Students” header is displayed
    Assert.assertTrue(app.studentsPage().studentsHeader.isDisplayed());

    //Check “WileyPLUS” link is present on page and it leads to http://wileyplus.wiley.com
    String urlWileyPLUS = app.studentsPage().referTowileyplus.getAttribute("href");
    Assert.assertEquals(urlWileyPLUS, "http://wileyplus.wiley.com/");

    //Move to “Subjects” ->E-L- Education
    app.goToEducation();

    //Check “Education” header is displayed
    Assert.assertTrue(app.educationPage().educationHeader.isDisplayed());

    //13 items are displayed under “Subjects”
    Set<WebElement> itemsUnderSubject = app.educationPage().getItemsOfSubject();
    Assert.assertEquals(itemsUnderSubject.size(), 13);
    for (WebElement item : itemsUnderSubject) {
      Assert.assertTrue(item.isDisplayed());
    }
    Set<String> defTextOfSubjects = Stream.of("Information & Library Science", "Education & Public Policy",
            "K-12 General", "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)",
            "Curriculum Tools- General", "Special Educational Needs", "Theory of Education",
            "Education Special Topics", "Educational Research & Statistics", "Literacy & Reading", "Classroom Management")
            .collect(Collectors.toCollection(HashSet::new));

    Set<String> textsUnderSubject = app.educationPage().getTitlesOfSubject();
    Assert.assertEquals(textsUnderSubject, defTextOfSubjects);

    app.goToMainPageThroughLogo();
    //Click Search
    app.initSearch();

    //Type math
    app.seleniumHelper().type(By.id("js-site-search-input"), "math");

    //Area with related content is displayed right under the search header
    app.waitRelatedContent();
    Assert.assertTrue(app.mainPage().relatedContent.isDisplayed());

    //On the left side, it has 4 words starting with “Math”
    Set<String> wordsOnTheLeft = app.mainPage().getWordsAfterSearch();
    Assert.assertEquals(wordsOnTheLeft.size(), 4);
    for (String word : wordsOnTheLeft) {
      Assert.assertEquals(word.substring(0, 4), "Math");
    }

    //On the right side, there are 4 titles under “Related content” and each title contain “Math” word
    Set<String> titlesOfRelatedContent = app.mainPage().getTitlesOfRelatedContent();
    Assert.assertEquals(titlesOfRelatedContent.size(), 4);
    for (String title : titlesOfRelatedContent) {
      Assert.assertTrue(title.contains("Math"));
    }

    app.initSearch();

    Set<String> titlesOfContentAfterSearch = app.mainPage().getTitlesOfContent();
    //Only titles containing “Math” are displayed
    for (String titleOfContentAfterSearch : titlesOfContentAfterSearch) {
      Assert.assertTrue(titleOfContentAfterSearch.contains("Math"));
    }
    //There are 10 titles
    Assert.assertEquals(titlesOfContentAfterSearch.size(), 10);


    //Each title has at least one “Add to Cart” button
    List<WebElement> contentsAfterSearch = app.mainPage().contents;
    for (WebElement content : contentsAfterSearch) {
      WebElement addToCartButton = content.findElement(By.cssSelector("div.SearchResultsList-AddToLegacyCartAction"));
      Assert.assertTrue(addToCartButton.isDisplayed());
    }

    app.initSearch();
    //there are same 10 titles shown
    Set<String> titlesOfContentAfterSecondSearch = app.mainPage().getTitlesOfContent();
    Assert.assertEquals(titlesOfContentAfterSecondSearch, titlesOfContentAfterSearch);

  }
}
