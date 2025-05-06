package Test1.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {
    @FindBy(id="userName")
    private WebElement username;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(id="login")
    public WebElement submit;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submit.click();
    }

}
