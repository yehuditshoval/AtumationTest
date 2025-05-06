package Test1.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search {
    @FindBy(id="searchBox")
    public WebElement btn_search;

    public void search(String text){
        btn_search.sendKeys(text);
    }

}
