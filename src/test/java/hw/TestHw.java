package hw;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GooglePage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestHw {
    public static WebDriver driver;
    public static GooglePage googlePage;
    private static Logger logger = LoggerFactory.getLogger(TestHw.class);

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        googlePage = new GooglePage(driver);
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("page"));
        logger.info("User opened page " + ConfProperties.getProperty("page"));
    }

    @Test
    @DisplayName("Тест на вход в лк")
    public void loginTest() throws InterruptedException {
        Assert.assertTrue(googlePage.checkLogin());
        googlePage.clickLogin();
        googlePage.sendLogin();
        logger.info("User wrote login " + ConfProperties.getProperty("login"));
        Thread.sleep(1000);
        googlePage.sendPassword();
        logger.info("User wrote password " + ConfProperties.getProperty("password"));
    }

    @Test
    @DisplayName("Тест на изменение имени пользователя")
    public void changeUserInfo() throws InterruptedException {
        loginTest();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        googlePage.clickUser();
        Assert.assertTrue(googlePage.checkAccountButton());
        googlePage.clickAccountButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        tabs.forEach(tab -> {
            System.out.println(tab);
            driver.switchTo().window(tab);
        });
        //так в тесте перекидывает в проверку конфиценциальности
        //WebElement info = driver.findElement(By.xpath("//a[@data-nav-type='1']/div[text()='Личная информация']"));
        //js.executeScript("arguments[0].click();", info);
        driver.navigate().to("https://myaccount.google.com/personal-info?pli=1");
        googlePage.clickNameChange();
        googlePage.sendName();
        logger.info("User wrote name" + ConfProperties.getProperty("name"));
        googlePage.clickSave();
        driver.manage().addCookie(new Cookie("info", "november27"));
        logger.info("User added cookie");
        driver.manage().deleteCookieNamed("info");
        logger.info("User deleted cookie");
    }

    @Test
    @DisplayName("Тест на поиск в дудлах по дате")
    public void testDate() {
        googlePage.clickLuckyButton();
        googlePage.clickDetailsButton();
        String date = googlePage.getDate();
        googlePage.searchDate();
        logger.info("User searched date " + date);
    }

    @Test
    @DisplayName("Тест на поиск обоев в картинках")
    public void testWallpaper() {
        googlePage.clickPictures();
        googlePage.searchPic();
        logger.debug("User typed in search " + ConfProperties.getProperty("search"));
        Assert.assertTrue(googlePage.checkTools());
        googlePage.clickTools();
        googlePage.clickSize();
        googlePage.clickLarge();
        googlePage.clickType();
        googlePage.clickDrawings();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
