package hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.Keys.ENTER;

public class YandexPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public YandexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='desk-notif-card__covid-container rotate-items i-bem rotate-items_js_inited']")
    private WebElement covid;

    @FindBy(xpath = "//li[text()='Выздоровления']")
    private WebElement good;

    @FindBy(xpath = "//li[text()='Смерти']")
    private WebElement bad;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement find;

    @FindBy(xpath = "//a[text()='Погода']")
    private WebElement weather;

    @FindBy(xpath = "//a[text()='На месяц']")
    private WebElement forMonth;

    @FindBy(xpath = "//input[@class='input__control']")
    private WebElement inputCity;

    @FindBy(xpath = "//a[text()='Москва, Москва и Московская область']")
    private WebElement moscow;

    public void goodClick() {
        good.click();
    }

    public void mscClick() {
        moscow.click();
    }

    public void forMonthClick() {
        forMonth.click();
    }

    public void inputCityClick() {
        inputCity.sendKeys("Москва");
        inputCity.sendKeys(ENTER);
    }

    public void inputCity() {
        inputCity.click();
    }

    public void weatherClick() {
        weather.click();
    }

    public void badClick() {
        bad.click();
    }

    public void findClick() {
        find.click();
    }

    public void sendMoscow() {
        find.sendKeys("Москва");
        find.sendKeys(ENTER);
    }

    public void waitTime(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    public void clickCovid() {
        covid.click();
    }

}
