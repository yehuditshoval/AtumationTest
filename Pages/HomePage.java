package Test1.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage {

    @FindBy(xpath="//*[@id=\"item-2\"]")
    public WebElement btn_shopOfBooks;

    public  void  goShopBook() {
        System.out.println(btn_shopOfBooks.isDisplayed());
        System.out.println(btn_shopOfBooks.isEnabled());
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", btn_shopOfBooks);

        btn_shopOfBooks.click();
    }
}
