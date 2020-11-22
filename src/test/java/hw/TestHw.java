package hw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestHw {
    public static YandexPage page;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        page = new YandexPage(driver);
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("page"));
    }

    @Test
    public void covidTest() throws InterruptedException {

        //page.clickCovid();
        //по клику не работают следующие методы, не поняла как исправить
        driver.navigate().to("https://yandex.ru/covid19/stat?utm_source=main_notif&geoId=11119");
        page.findClick();
        page.sendMoscow();
        page.goodClick();
        page.badClick();
        page.waitTime(5);
    }

    @Test
    public void weatherTest() throws InterruptedException {
        //page.weatherClick();
        //по клику не работают следующие методы, не поняла как исправить
        driver.navigate().to("https://yandex.ru/pogoda/");
        page.inputCity();
        page.inputCityClick();
        page.mscClick();
        page.forMonthClick();
        page.waitTime(5);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
