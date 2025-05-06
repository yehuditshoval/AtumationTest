package Test1;

import Lesson26_PageObjects.pages.LoginPage;
import Test1.Pages.Book;
import Test1.Pages.HomePage;
import Test1.Pages.Login;
import Test1.Pages.Search;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestAtu {
     WebDriver driver;
    Login loginPage;
    HomePage homePage;
    Search searchPage;


    @BeforeClass
    public void start(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getData("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage= PageFactory.initElements(driver, Login.class);
        homePage= PageFactory.initElements(driver, HomePage.class);
        searchPage= PageFactory.initElements(driver, Search.class);

    }



    @AfterClass
    public void finish(){
        driver.quit();
    }

    @Test
    public void Test1(){
        loginPage.login(getData("UserName"),getData("Password"));
    }

    @Test
    public void Test3(){
      searchPage.search(getData("search1"));
      List<WebElement> list=driver.findElements(By.xpath("//*[@id='see-book-Git Pocket Guide']/a"));
      Assert.assertEquals(list.size(),1);

    }
    @Test
    public void Test4(){
        searchPage.search(getData("search2"));
        List<WebElement> list=driver.findElements(By.xpath("//*[@id='see-book-Git Pocket Guide']/a"));
        Assert.assertEquals(list.size(),0);
        Assert.assertEquals("No rows found",driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[2]/div[3]")).getText());
    }
    @Test
    public void Test5(){
        driver.navigate().refresh();
        Book[] books = new Book[8];
        for (int i = 0; i < 8; i++) {
            String xpathTitle = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]/div/div[2]")).getText();
            String title = xpathTitle;
            String authorXpath = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]/div/div[3]")).getText();
            String publisher = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[" + i + "]/div/div[4]")).getText();

            books[i] = new Book(title, authorXpath, publisher);
        }
    }
    @Test
    public void Test5(){
        for (Book b : books) {
            b.printBook();
        }
    }


    @AfterMethod
    public void afterMethod() throws InterruptedException
    {
        Thread.sleep(2000);
    }


    public String getData (String nodeName)
    {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./testConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        }
        catch(Exception e)
        {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
