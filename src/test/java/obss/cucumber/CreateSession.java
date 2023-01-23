package obss.cucumber;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import witwiser.common.util.CommonVariables;

import java.time.Duration;
import java.time.LocalDate;


public class CreateSession {
    CommonVariables variables = new CommonVariables();

    @Then("click session")
    public void clickSession() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement sessionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[(text()='" + variables.SESSIONS + "')]")));
        sessionLink.click();
    }

    @Then("click create session")
    public void createSession() {
        WebElement createSessionButton = CommonVariables.DRIVER.findElement(By.xpath("//button[text()='" + variables.CREATE_SESSION + "']"));
        createSessionButton.click();
    }

    @Then("fill in the fields")
    public void template() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement templateNameButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='" + variables.TEMPLATE_NAME + "']")));
        templateNameButton.click();
    }

    @Then("click exam name")
    public void exam() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement examButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + variables.EXAM_NAME + "']")));
        examButton.click();

    }

    @Then("click start date")
    public void startDate() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement startInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/div/div")));
        startInput.click();
    }

    @Then("click today date")
    public void setTodayDate() {
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        WebElement todayDate = CommonVariables.DRIVER.findElement(By.xpath("//div[text()='" + currentDay + "' and @aria-disabled='false']"));
        todayDate.click();
    }

    @Then("click end date")
    public void endDate() {
        WebElement endInput = CommonVariables.DRIVER.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div/div"));
        endInput.click();
    }

    @Then("click finish date")
    public void setEndDate() {
        WebElement nextButton = CommonVariables.DRIVER.findElement(By.xpath("//button[@aria-label='Next Month']"));
        nextButton.click();
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        WebElement endDate = CommonVariables.DRIVER.findElement(By.xpath("//div[text()='" + currentDay + "' and @aria-disabled='false']"));
        endDate.click();
    }

    @Then("select assignment type")
    public void selectAssignmentType() {
        WebElement assignmentDropdown = CommonVariables.DRIVER.findElement(By.xpath("//*[@name='" + variables.ASSIGNMENT_TYPE + "']"));
        assignmentDropdown.click();
    }

    @Then("select single assignment")
    public void selectGroupAssignment() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement singleAssignment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + variables.SINGLE_ASSIGMENT + "']")));
        singleAssignment.click();
    }

    @Then("write candidate username as {string}")
    public void selectCandidate(String candidateUsername) {
        WebElement candidateInput = CommonVariables.DRIVER.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/div/div/div/div[1]/input"));
        candidateInput.click();
        candidateInput.sendKeys(candidateUsername);
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement results=wait.until(ExpectedConditions.elementToBeClickable(By.className("result")));
        candidateInput.sendKeys(Keys.ARROW_DOWN);
        candidateInput.sendKeys(Keys.ENTER);
    }


    @Then("create session")
    public void finishCreateSession() {
        WebElement submitButton = CommonVariables.DRIVER.findElement(By.xpath("//button[text()='" + variables.CREATE_SESSION + "' and @class='ui primary button']"));
        submitButton.click();
    }
}
