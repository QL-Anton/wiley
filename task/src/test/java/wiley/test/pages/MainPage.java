package wiley.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Антон on 04.02.2018.
 */
public class MainPage extends  Page {
  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//a[text()='Resources']")
  public WebElement resources;

  @FindBy(xpath = "//a[text()='Subjects']")
  public WebElement subjects;

  @FindBy(xpath = "//a[text()='About']")
  public WebElement about;

  @FindBy(css = "div.main-navigation-menu div.simple-responsive-banner-component")
  public WebElement logo;

  @FindBy(xpath = "//button[text()='Search']")
  public WebElement search;

  @FindBy(id = "ui-id-2")
  public WebElement relatedContent;

  @FindBy(css = "div.product-content")
  public List<WebElement> contents;


  public Set<String> getWordsAfterSearch() {
    Set<String> words = new HashSet<>();
    List<WebElement> currentWords= driver.findElements(By.cssSelector("div.ui-menu-item a"));
    for (WebElement currentWord : currentWords) {
      String word = currentWord.getAttribute("innerText");
      words.add(word);
    }
    return  words;
  }


  public Set<String> getTitlesOfRelatedContent() {
    Set<String> words = new HashSet<>();
    List<WebElement> currentWords= driver.findElement(By.className("related-content-products")).findElements(By.className("product-title"));
    for (WebElement currentWord : currentWords) {
      String word = currentWord.getAttribute("innerText");
      words.add(word);
    }
    return  words;
  }


  public Set<String> getTitlesOfContent() {
    Set<String> titles = new HashSet<>();
    List<WebElement> currentTitlesItems=driver.findElements(By.cssSelector("h3.product-title"));
    for (WebElement currentTitlesItem : currentTitlesItems) {
      String title = currentTitlesItem.getAttribute("innerText");
      titles.add(title);
    }
    return  titles;
  }

}
