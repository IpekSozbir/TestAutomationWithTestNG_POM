package tests.day13_testNGFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_TestNG_IlkTest {

    @Test
    public void test01 () {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.testotomasyonu.com");

        // phone icin arama yapin
        WebElement aramaKutusu = driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        WebElement aramaSonucuElementi = driver.findElement(By.className("product-count-text"));

        String unexpectedSonuc = "0 Products Found";
        String actualSonuc = aramaSonucuElementi.getText();
        Assert.assertNotEquals(actualSonuc,unexpectedSonuc);

        /*
        Junit Assertions ile 2 kucuk fark var
            1- Class ismi Assertions degil Assert
            2- Junit'de once expected sonra actual deger yaziliyordu
               TestNG'de once actual sonra expected deger yaziliyor
         */

        // driver'i kapatin
        driver.quit();

    }
}
