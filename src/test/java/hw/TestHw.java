package hw;

import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.ENTER;

public class TestHw {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("page"));
    }

    @Test
    @Description("Тест на вход в лк")
    public void loginTest() throws InterruptedException {
        WebElement login =  driver.findElement(By.xpath("//a[text()='Войти']"));
        Assert.assertTrue(login.isDisplayed());
        login.click();
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//div/input[@type='email']")))
                .sendKeys(ConfProperties.getProperty("login"))
                .sendKeys(ENTER)
                .build()
                .perform();
        Thread.sleep(1000);
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//div/input[@type='password']")))
                .sendKeys(ConfProperties.getProperty("password"))
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Test
    @Description("Тест на изменение имени пользователя")
    public void changeUserInfo() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        loginTest();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//img[@class='gb_Ha gbii']")))
                .click()
                .build()
                .perform();
        WebElement acc = driver.findElement(By.xpath("//a[text()='Управление аккаунтом Google']"));
        Assert.assertTrue(acc.isDisplayed());
        js.executeScript("arguments[0].click();", acc);
        new WebDriverWait(driver,5).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        tabs.forEach(tab->{
            System.out.println(tab);
            driver.switchTo().window(tab);
        });
        //так в тесте перекидывает в проверку конфиценциальности
        //WebElement info = driver.findElement(By.xpath("//a[@data-nav-type='1']/div[text()='Личная информация']"));
        //js.executeScript("arguments[0].click();", info);
        driver.navigate().to("https://myaccount.google.com/personal-info?pli=1");
        js.executeScript("arguments[0].click();",driver
                .findElement(By.xpath("(//a[@class='VZLjze Wvetm I6g62c N5YmOc kJXJmd'])[1]")));
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath(("(//input)[3]"))))
                .doubleClick()
                .sendKeys(Keys.DELETE)
                .sendKeys(ConfProperties.getProperty("name"))
                .build()
                .perform();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Сохранить']")));
        driver.manage().addCookie(new Cookie("info", "november27"));
        driver.manage().deleteCookieNamed("info");
    }

    @Test
    @Description("Тест на поиск в дудлах по дате")
    public void testDate() {
        driver.findElement(By.xpath("(//input[@aria-label='Мне повезёт!'])[2]")).click();
        driver.findElement(By.xpath("(//a[@id='highlight-detail'])[1]")).click();
        String date = driver.findElement(By.xpath("//div[@class='time']")).getText();
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//input[@placeholder='Поиск дудлов']")))
                .click()
                .sendKeys(date)
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Test
    @Description("Тест на поиск обоев в картинках")
    public void testWallpaper() {
        driver.findElement(By.xpath("//a[text()='Картинки']")).click();
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//input[@title='Поиск']")))
                .click()
                .sendKeys(ConfProperties.getProperty("search"))
                .sendKeys(ENTER)
                .build()
                .perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Инструменты']")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Инструменты']")).click();
        driver.findElement(By.xpath("//div[text()='Размер']")).click();
        driver.findElement(By.xpath("//span[text()='Большой']")).click();
        driver.findElement(By.xpath("//div[text()='Тип']")).click();
        driver.findElement(By.xpath("//span[text()='Рисунки']")).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
