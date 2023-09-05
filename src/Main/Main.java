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
        PlaceOrderElementleri locaterlar = new PlaceOrderElementleri();

        driver.get("https://e-junkie.com/wiki/demo/");

        locaterlar.addTocartBtn.click();

        //Sepet (cart) sayfasında "Pay using debit card" seçeneğini görmeli ve tıklamalıyım.
        driver.switchTo().frame(0);
        Assert.assertTrue(locaterlar.debitCardBtn.getText().contains("Pay using Debit"));
        locaterlar.debitCardBtn.click();

        //Ödeme sayfasında debit kartı bilgileri alanını görebilmeliyim
        // (kart numarası, son kullanma tarihi, CVC).
        Assert.assertTrue(locaterlar.kartInfo.getText().contains("card number"));
        Assert.assertTrue(locaterlar.kartInfo.getText().contains("Expiration"));
        Assert.assertTrue(locaterlar.kartInfo.getText().contains("CVV"));

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
        locaterlar.payBtn.click();

        //Ödeme işlemi sırasında  "Invalid Email," "Invalid Billing Name", "Invalid Billing Address",
        // "Invalid Billing City", "Invalid Billing PostCode" hatalarının aynı anda görüntülendiğini doğrulamalıyım.
        Assert.assertTrue(locaterlar.snackBar.getText().contains("Invalid Email"));
        Assert.assertTrue(locaterlar.snackBar.getText().contains("Invalid Billing Name"));
        Assert.assertTrue(locaterlar.snackBar.getText().contains("Invalid Billing Address"));
        Assert.assertTrue(locaterlar.snackBar.getText().contains("Invalid Billing City"));
        Assert.assertTrue(locaterlar.snackBar.getText().contains("Invalid Billing PostCode"));
    }
    @Test
    public void odemeIslemOnay() {
        PlaceOrderElementleri locaterlar = new PlaceOrderElementleri();

        //Demo e-kitabını sepete eklemek ve ödeme işlemimi tamamlamak istiyorum.
        locaterlar.addTocartBtn.click();

        //Sepet (cart) sayfasında "Pay using debit card" seçeneğini görmeli ve tıklamalıyım.
        driver.switchTo().frame(0);
        Assert.assertTrue(locaterlar.debitCardBtn.getText().contains("Pay using Debit"));
        locaterlar.debitCardBtn.click();

        //Ödeme formundaki bilgileri doldurmalıyım. (Email, Confirm Email, Name, Phone, Company)
        locaterlar.inputEmail.sendKeys("osman@hotmail.com");
        locaterlar.inputConfirmEmail.sendKeys("osman@hotmail.com");
        locaterlar.inputName.sendKeys("Osman Yilmaz");
        locaterlar.inputPhone.sendKeys("1234567890");
        locaterlar.inputCompany.sendKeys("ABC");
        locaterlar.inputAdress.sendKeys("Fifth Avenue 24");
        locaterlar.inputCity.sendKeys("New York");
        locaterlar.inputPostCode.sendKeys("2345PC");

        //Sağ tarafta bulunan "Use credit card number" bölümüne Kart Numarası: 4242 4242 4242 4242,
        // Kart Türü: VISA, Son Kullanma Tarihi: 12/2028, CVC: 315 girmeliyim.
        Select ddMenu = new Select(locaterlar.kartTuru);
        ddMenu.selectByIndex(0);
        locaterlar.inputCardNo.sendKeys("4242 4242 4242 4242");
        locaterlar.inputCardEx.sendKeys("12/28");
        locaterlar.inputCVVCSC.sendKeys("315");

        //Pay" düğmesini tıklayarak ödeme işlemimi tamamlamalıyım.
        locaterlar.payBtn.click();

        //Ödeme işlemi sonucunda "Your order is confirmed. Thank you!"
        //mesajının görüntülendiğini doğrulamalıyım.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='thank_you_content_bar']")));
        Assert.assertTrue(locaterlar.dogrulama.getText().contains("your order is confirmed. Thank you!"));

    }
}