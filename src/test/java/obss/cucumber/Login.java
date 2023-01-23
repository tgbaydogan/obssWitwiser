package obss.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import witwiser.common.util.CommonVariables;

public class Login {
    CommonVariables variables = new CommonVariables();

    @Given("open {string}")
    public void openBrowser(String browserName) {
        if (browserName.equals(variables.CHROME_BROWSER)) {
            CommonVariables.DRIVER = new ChromeDriver();
        }
    }

    public void changeLanguageToTurkish(){
        LocalStorage local = ((WebStorage) CommonVariables.DRIVER).getLocalStorage();
        local.setItem("witwiser-lang", "tr-TR");
        CommonVariables.DRIVER.navigate().refresh();
    }


    @Then("go to {string}")
    public void goToGivenURL(String url) {
        CommonVariables.DRIVER.get(url);
        changeLanguageToTurkish();
        CommonVariables.DRIVER.manage().window().maximize();
    }

    @And("type username as {string} and password as {string}")
    public void enterCredentials(String username, String password) {
        WebElement usernameInput = CommonVariables.DRIVER.findElement(By.name(variables.USERNAME));
        WebElement passwordInput = CommonVariables.DRIVER.findElement(By.name(variables.PASSWORD));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    @Then("click login")
    public void clickLogin(){
        WebElement login = CommonVariables.DRIVER.findElement(By.xpath("//span[text()='"+variables.LOGIN+"']"));
        login.click();
    }
}
