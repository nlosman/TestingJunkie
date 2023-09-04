package Main;

import Utility.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Main extends BaseDriver {
    @Test
    public void eKitapOdeme() {
        driver.get("https://e-junkie.com/wiki/demo/");
        WebElement addTocartBtn = driver.findElement(By.xpath("//*[@class='btn'][2]"));
        addTocartBtn.click();

        //Sepet (cart) sayfasında "Pay using debit card" seçeneğini görmeli ve tıklamalıyım.
        driver.switchTo().frame(0);
        WebElement debitCardBtn = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        Assert.assertTrue(debitCardBtn.getText().contains("Pay using Debit"));
        debitCardBtn.click();

        //Ödeme sayfasında debit kartı bilgileri alanını görebilmeliyim
        // (kart numarası, son kullanma tarihi, CVC).
        WebElement kartInfo = driver.findElement(By.className("Info"));
        Assert.assertTrue(kartInfo.getText().contains("card number"));
        Assert.assertTrue(kartInfo.getText().contains("Expiration"));
        Assert.assertTrue(kartInfo.getText().contains("CVV"));

        //Ödeme sayfasında e-posta adresi ve diğer alan boş bırakmalıyım.
        List<WebElement> email = driver.findElements(By.xpath("//input[@type='email']"));
        for (WebElement e : email) {
            Assert.assertTrue(e.getText().isEmpty());
        }

        List<WebElement> bosBilgiler = driver.findElements(By.xpath("//input[@type='text']"));
        for (WebElement b : bosBilgiler) {
            Assert.assertTrue(b.getText().isEmpty());
        }

        //Ödeme işlemini tamamlamak için "Pay" düğmesini tıklamalıyım.
        WebElement payBtn = driver.findElement(By.className("Pay-Button"));
        payBtn.click();

        //Ödeme işlemi sırasında  "Invalid Email," "Invalid Billing Name", "Invalid Billing Address",
        // "Invalid Billing City", "Invalid Billing PostCode" hatalarının aynı anda görüntülendiğini doğrulamalıyım.

        WebElement snackBar = driver.findElement(By.id("SnackBar"));
        Assert.assertTrue(snackBar.getText().contains("Invalid Email"));
        Assert.assertTrue(snackBar.getText().contains("Invalid Billing Name"));
        Assert.assertTrue(snackBar.getText().contains("Invalid Billing Address"));
        Assert.assertTrue(snackBar.getText().contains("Invalid Billing City"));
        Assert.assertTrue(snackBar.getText().contains("Invalid Billing PostCode"));

    }
    @Test
    public void odemeIslemOnay(){

        //Demo e-kitabını sepete eklemek ve ödeme işlemimi tamamlamak istiyorum.
        WebElement addTocartBtn = driver.findElement(By.xpath("//*[@class='btn'][2]"));
        addTocartBtn.click();

        //Sepet (cart) sayfasında "Pay using debit card" seçeneğini görmeli ve tıklamalıyım.
        driver.switchTo().frame(0);
        WebElement debitCardBtn = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        Assert.assertTrue(debitCardBtn.getText().contains("Pay using Debit"));
        debitCardBtn.click();

        //Ödeme formundaki bilgileri doldurmalıyım. (Email, Confirm Email, Name, Phone, Company)
        WebElement inputEmail = driver.findElement(By.xpath("(//input[@type='email'])[1]"));
        inputEmail.sendKeys("osman@hotmail.com");
        WebElement inputConfirmEmail = driver.findElement(By.xpath("(//input[@type='email'])[2]"));
        inputConfirmEmail.sendKeys("osman@hotmail.com");
        WebElement inputName = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        inputName.sendKeys("Osman Yilmaz");
        WebElement inputPhone = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        inputPhone.sendKeys("1234567890");
        WebElement inputCompany = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
        inputCompany.sendKeys("ABC");
        WebElement inputAdress = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
        inputAdress.sendKeys("Fifth Avenue 24");
        WebElement inputCity = driver.findElement(By.xpath("(//input[@type='text'])[6]"));
        inputCity.sendKeys("New York");
        WebElement inputPostCode = driver.findElement(By.xpath("(//input[@type='text'])[7]"));
        inputPostCode.sendKeys("2345PC");

        //Sağ tarafta bulunan "Use credit card number" bölümüne Kart Numarası: 4242 4242 4242 4242,
        // Kart Türü: VISA, Son Kullanma Tarihi: 12/2028, CVC: 315 girmeliyim.
        WebElement kartTuru = driver.findElement(By.cssSelector("[class='Card-Type'] select"));
        Select ddMenu = new Select(kartTuru);
        ddMenu.selectByIndex(0);
        WebElement inputCardNo = driver.findElement(By.xpath("(//input[@type='tel'])[1]"));
        inputCardNo.sendKeys("4242 4242 4242 4242");
        WebElement inputCardEx = driver.findElement(By.xpath("(//input[@type='tel'])[2]"));
        inputCardEx.sendKeys("12/28");
        WebElement inputCVVCSC = driver.findElement(By.xpath("(//input[@type='tel'])[3]"));
        inputCVVCSC.sendKeys("315");

        //Pay" düğmesini tıklayarak ödeme işlemimi tamamlamalıyım.
        WebElement payBtn = driver.findElement(By.className("Pay-Button"));
        payBtn.click();

        //Ödeme işlemi sonucunda "Your order is confirmed. Thank you!"
        //mesajının görüntülendiğini doğrulamalıyım.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='thank_you_content_bar']")));
        WebElement dogrulama =driver.findElement(By.xpath("(//*[@class='container col-centered col-lg-8'])[1]"));
        Assert.assertTrue(dogrulama.getText().contains("your order is confirmed. Thank you!"));
    }
}