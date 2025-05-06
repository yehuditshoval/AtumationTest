package Test1.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage {

    @FindBy(id="gotoStore")
    public WebElement btn_shopOfBooks;

    public  void  goShopBook() {
        btn_shopOfBooks.click();
    }
}
