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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Test1.Pages.Search.btn_notfound;

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
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        loginPage= PageFactory.initElements(driver, Login.class);
        homePage= PageFactory.initElements(driver, HomePage.class);
        searchPage= PageFactory.initElements(driver, Search.class);

    }


    @Test
    public void Test1_login(){
        loginPage.login(getData("UserName"),getData("Password"));
        homePage.goShopBook(driver);
        searchPage.search(getData("search1"));
        Assert.assertEquals(searchPage.countRealBooks(driver),1);
        searchPage.search(getData("search2"));
        Assert.assertEquals(searchPage.countRealBooks(driver),0);
        Assert.assertEquals("No rows found",btn_notfound.getText());
        searchPage.doListBook(driver);
        searchPage.printBooks();
    }

    @AfterClass
    public void finish() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
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


