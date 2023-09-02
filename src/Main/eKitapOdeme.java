package Main;

import Utility.BaseDriver;
import Utility.MyFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class eKitapOdeme extends BaseDriver {
    @Test
    public void test1(){
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
        WebElement kartInfo =driver.findElement(By.className("Info"));
        Assert.assertTrue(kartInfo.getText().contains("card number"));
        Assert.assertTrue(kartInfo.getText().contains("Expiration"));
        Assert.assertTrue(kartInfo.getText().contains("CVV"));

        //Ödeme sayfasında e-posta adresi ve diğer alan boş bırakmalıyım.
        List<WebElement> email = driver.findElements(By.xpath("//input[@type='email']"));
        for (WebElement e: email){
            Assert.assertTrue(e.getText().isEmpty());
        }

        List<WebElement> bosBilgiler = driver.findElements(By.xpath("//input[@type='text']"));
        for (WebElement b: bosBilgiler){
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

        bitisIslemleri();
    }

}
