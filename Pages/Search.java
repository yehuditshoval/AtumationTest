package Test1.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class Search {
    @FindBy(id = "searchBox")
    public WebElement btn_search;

    @FindBy(xpath = "//*[@id=\\\"app\\\"]/div/div/div/div[2]/div[2]/div[2]/div[3]")
    public static WebElement btn_notfound;
    Book[] books = new Book[8];


    public void search(String text) {
        btn_search.clear();
        btn_search.sendKeys(text);
    }

    public List<WebElement> findBooks(WebDriver driver) {
        return driver.findElements(By.xpath("//*[@class='rt-tr-group']"));
    }

    public int countRealBooks(WebDriver driver) {
        return driver.findElements(By.xpath("//div[@class='rt-tbody'/div/div/div/img")).size();
    }

    public void doListBook(WebDriver driver) {

        driver.navigate().refresh();
        for (int i = 0; i < 8; i++) {
            String title = driver.findElements(By.className("mr-2")).get(i).getText();
            String author = driver.findElements(By.className("rt-td")).get(2 * i + 1).getText();
            String publisher = driver.findElements(By.className("rt-td")).get(2 * i + 2).getText();

            books[i] = new Book(title, author, publisher);
        }
    }
    public void printBooks() {
        for (Book b : books) {
            b.printBook();
        }
    }
}

