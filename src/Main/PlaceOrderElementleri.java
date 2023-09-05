package Main;

import Utility.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class PlaceOrderElementleri extends BaseDriver {

    public PlaceOrderElementleri() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    @FindBy(xpath="//*[@class='btn'][2]")
    public WebElement addTocartBtn;

    @FindBy(xpath = "//button[@class='Payment-Button CC']")
    public WebElement debitCardBtn;

    @FindBy(className = "Info")
    public WebElement kartInfo;

    @FindBy(className = "Pay-Button")
    public WebElement payBtn;

    @FindBy(id = "SnackBar")
    public WebElement snackBar;

    @FindBy(xpath = "(//input[@type='email'])[1]")
    public WebElement inputEmail;

    @FindBy(xpath = "(//input[@type='email'])[2]")
    public WebElement inputConfirmEmail;

    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement inputName;

    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement inputPhone;

    @FindBy(xpath = "(//input[@type='text'])[3]" )
    public WebElement inputCompany;

    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement inputAdress;

    @FindBy(xpath = "(//input[@type='text'])[6]")
    public WebElement inputCity;

    @FindBy(xpath = "(//input[@type='text'])[7]")
    public WebElement inputPostCode;

    @FindBy(css = "[class='Card-Type'] select")
    public WebElement kartTuru;

    @FindBy(xpath = "(//input[@type='tel'])[1]")
    public WebElement inputCardNo;

    @FindBy(xpath = "(//input[@type='tel'])[2]")
    public WebElement inputCardEx;

    @FindBy(xpath = "(//input[@type='tel'])[3]")
    public WebElement inputCVVCSC;

    @FindBy(xpath = "(//*[@class='container col-centered col-lg-8'])[1]")
    public WebElement dogrulama;














}
