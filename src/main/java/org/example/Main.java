import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Main {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Mariana/Documents/chromedriver_win32/chromedriver");
        driver = new ChromeDriver();

        // Настройка генератора отчетов
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testPageElements() {
        test = extent.createTest("testPageElements", "Проверка наличия элементов на странице");

        driver.get("https://playground.learnqa.ru/puzzle/triangle");

        WebElement giveUpButton = driver.findElement(By.xpath("//button[contains(text(), 'Я сдаюсь')]"));
        Assert.assertTrue(giveUpButton.isDisplayed(), "Кнопка 'Я сдаюсь' не найдена на странице");

        giveUpButton.click();
        test.log(Status.INFO, "Клик по кнопке 'Я сдаюсь'");

        WebElement answerLink = driver.findElement(By.xpath("//a[contains(text(), 'Ссылка на ответы')]"));
        Assert.assertTrue(answerLink.isDisplayed(), "Ссылка на ответы не появилась на странице");

        WebElement hideAnswerButton = driver.findElement(By.xpath("//button[contains(text(), 'Спрятать ответы')]"));
        Assert.assertTrue(hideAnswerButton.isDisplayed(), "Кнопка 'Спрятать ответы' не появилась на странице");
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
