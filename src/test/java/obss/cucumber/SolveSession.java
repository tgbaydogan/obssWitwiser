package obss.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import witwiser.common.util.CommonVariables;

import java.time.Duration;
import java.util.List;

public class SolveSession {
    CommonVariables variables = new CommonVariables();

    JavascriptExecutor js = (JavascriptExecutor) CommonVariables.DRIVER;

    @Given("go to homepage")
    public void goToHomepage() {
        WebElement sessionLink = CommonVariables.DRIVER.findElement(By.xpath("//a[(text()='" + variables.HOMEPAGE + "')]"));
        sessionLink.click();
    }

    @Then("start test")
    public void startTest() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(20));
        WebElement navigateToStartTest = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class ='ui button src-scenes-dashboard-components-WarningInfo-WarningInfo__buttonElement']")));
        navigateToStartTest.click();
    }

    @Then("click start test button")
    public void startTestButton() {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" + variables.START_TEST + "']")));
        startButton.click();
    }

    @Then("solve questions")
    public void solveQuestions() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(CommonVariables.DRIVER, Duration.ofSeconds(10));
        WebElement parentElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("src-scenes-session-pages-test-SessionTest__questionBoxContainer")));
        List<WebElement> allChildElements = parentElement.findElements(By.xpath("*"));
        for (WebElement allChildElement : allChildElements) {
            allChildElement.click();
            this.handleCheckboxQuestions();
            this.handleRadioQuestions();
            this.handleTextAreaQuestions();
            this.handleDragDropQuestions();

        }
    }

    public void handleCheckboxQuestions() throws InterruptedException {
        Thread.sleep(100);
        List<WebElement> inputElements = CommonVariables.DRIVER.findElements(By.xpath("//input[@type='checkbox']"));
        if (inputElements.size() > 0) {
            js.executeScript("arguments[0].click();", inputElements.get(0));
        }
    }

    public void handleRadioQuestions() throws InterruptedException {
        Thread.sleep(100);
        List<WebElement> inputElements = CommonVariables.DRIVER.findElements(By.xpath(" //input[@type='radio']"));
        if (inputElements.size() > 0) {
            js.executeScript("arguments[0].click();", inputElements.get(0));

        }
    }

    public void handleTextAreaQuestions() throws InterruptedException {
        Thread.sleep(100);
        if (CommonVariables.DRIVER.findElements(By.xpath("//div[@class ='ql-editor ql-blank']")).size() > 0) {
            WebElement textArea = CommonVariables.DRIVER.findElement(By.xpath("//div[@class ='ql-editor ql-blank']//p"));
                textArea.sendKeys("Some answer");
        }
    }

    public void handleDragDropQuestions() throws InterruptedException {
        Thread.sleep(100);
        List<WebElement> targets = CommonVariables.DRIVER.findElements(By.xpath("//span[@class='src-common-components-Questions-DragAndDrop-QuestionPreview__placeholder']"));
        List<WebElement> sources = CommonVariables.DRIVER.findElements(By.xpath("//span[@class='src-common-components-Questions-DragAndDrop-BlankItem__blankItem']"));
        if (targets.size() > 0) {
            Actions builder = new Actions(CommonVariables.DRIVER);
            for (int i = 0; i < targets.size(); i++) {
                builder.moveToElement(sources.get(i)).clickAndHold().moveToElement(targets.get(i)).perform();
                Thread.sleep(2000);
                builder.moveToElement(targets.get(i)).release().build().perform();
            }
        }
    }

    @Then("finish test")
    public void finishTest() {
        WebElement button = CommonVariables.DRIVER.findElement(By.xpath("//button[(text()='" + variables.FINISH_TEST + "')]"));
        button.click();
    }

    @Then("end test")
    public void endTest() {
        WebElement button = CommonVariables.DRIVER.findElement(By.xpath("//button[(text()='" + variables.FINISH_TEST + "')]"));
        button.click();
    }
}
