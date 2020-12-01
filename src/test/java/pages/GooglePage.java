package pages;

import hw.ConfProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.Keys.ENTER;

public class GooglePage {
    private WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//div/input[@type='email']")
    private WebElement login;

    @FindBy(xpath = "//div/input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//img[@class='gb_Ha gbii']")
    private WebElement userButton;

    @FindBy(xpath = "//a[text()='Управление аккаунтом Google']")
    private WebElement accountButton;

    @FindBy(xpath = "(//a[@class='VZLjze Wvetm I6g62c N5YmOc kJXJmd'])[1]")
    private WebElement nameChange;

    @FindBy(xpath = "(//input)[3]")
    private WebElement nameInput;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = "(//input[@aria-label='Мне повезёт!'])[2]")
    private WebElement luckyButton;

    @FindBy(xpath = "(//a[@id='highlight-detail'])[1]")
    private WebElement detailsButton;

    @FindBy(xpath = "//div[@class='time']")
    private WebElement time;

    @FindBy(xpath = "//input[@placeholder='Поиск дудлов']")
    private WebElement searchDoodles;

    @FindBy(xpath = "//a[text()='Картинки']")
    private WebElement pictures;

    @FindBy(xpath = "//input[@title='Поиск']")
    private WebElement search;

    @FindBy(xpath = "//div[text()='Инструменты']")
    private WebElement tools;

    @FindBy(xpath = "//div[text()='Размер']")
    private  WebElement picSize;

    @FindBy(xpath = "//span[text()='Большой']")
    private WebElement large;

    @FindBy(xpath = "//div[text()='Тип']")
    private WebElement type;

    @FindBy(xpath = "//span[text()='Рисунки']")
    private WebElement drawings;

    @Step(value = "Нажать кнопку войти")
    public void clickLogin(){
        loginButton.click();
    }

    @Step(value = "Проверить кнопку войти")
    public boolean checkLogin(){
        return loginButton.isDisplayed();
    }

    @Step(value = "Ввести логин")
    public void sendLogin(){
        new Actions(driver)
                .moveToElement(login)
                .sendKeys(ConfProperties.getProperty("login"))
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Step(value = "Ввести пароль")
    public void sendPassword(){
        new Actions(driver)
                .moveToElement(password)
                .sendKeys(ConfProperties.getProperty("password"))
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Step(value = "Нажать на иконку пользователя")
    public void clickUser(){
        new Actions(driver)
                .moveToElement(userButton)
                .click()
                .build()
                .perform();
    }

    @Step(value = "Проверка досупности кнопки входа в аккаунт")
    public boolean checkAccountButton(){
        return accountButton.isDisplayed();
    }

    @Step(value = "Нажать кнопку входа в аккаунт")
    public void clickAccountButton(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", accountButton);
    }
    @Step(value = "Нажать на кнопку изменения имени")
    public void clickNameChange(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nameChange);
    }

    @Step(value = "Ввести другое имя")
    public void sendName(){
        new Actions(driver)
                .moveToElement(nameInput)
                .doubleClick()
                .sendKeys(Keys.DELETE)
                .sendKeys(ConfProperties.getProperty("name"))
                .build()
                .perform();
    }

    @Step(value = "Сохранить изменения")
    public void clickSave(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", saveButton);
    }

    @Step(value = "Нажать кнопку Мне повезет!")
    public void clickLuckyButton(){
        luckyButton.click();
    }

    @Step(value = "Нажать кнопку Подробнее")
    public void clickDetailsButton(){
        detailsButton.click();
    }

    @Step(value = "Получить дату")
    public String getDate(){
        return time.getText();
    }

    @Step(value = "Ввести дату в поиск")
    public void searchDate(){
        String date = getDate();
        new Actions(driver)
                .moveToElement(searchDoodles)
                .click()
                .sendKeys(date)
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Step(value = "Нажать кнопку Картинки")
    public void clickPictures(){
        pictures.click();
    }

    @Step(value = "Ввести в поиск картинки")
    public void searchPic(){
        new Actions(driver)
                .moveToElement(search)
                .click()
                .sendKeys(ConfProperties.getProperty("search"))
                .sendKeys(ENTER)
                .build()
                .perform();
    }

    @Step(value = "Проверка кнопки Настройки")
    public boolean checkTools(){
        return tools.isDisplayed();
    }

    @Step(value = "Нажать кнопку Настройки")
    public void clickTools(){
        tools.click();
    }

    @Step(value = "Нажать кнопку Размер")
    public void clickSize(){
        picSize.click();
    }

    @Step(value = "Нажать кнопку Большой")
    public void clickLarge(){
        large.click();
    }

    @Step(value = "Нажать кнопку Тип")
    public void clickType(){
        type.click();
    }

    @Step(value = "Нажать кнопку Рисунки")
    public void clickDrawings(){
        drawings.click();
    }

}
