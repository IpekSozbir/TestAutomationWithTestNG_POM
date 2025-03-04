package tests.day16_htmlReports;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C04_DataProviderIleTopluAramaTesti {

    /*
    Data Provider ile verilen bir listedeki her bir deger icin
    test method'unu bagimsiz olarak calistirabiliriz

        1- DataProvider ile test yapabilmek icin
        once test methodumuzu parametre ile calisacak sekilde
        tek bir urun icin yazalim

        2- Test method'una disaridan bir DataProvider tanimlayalim
        3 - yazdigimiz DataProvider isminin ustune yaklasip
        intelliJ'in data provider'i create etmesini isteyelim

        4- data provider, @DataProvider notasyonuna sahip ozel ibr method'dur
        Mutlaka iki katli bir Object Array dondurmelidir
     */

    @DataProvider
    public static Object[][] UrunProvideri() {
        //Data provider must return Object[][]

        String[][] urunlerArrayi = {{"phone"}, {"java"}, {"dress"}, {"baby"}, {"nutella"},
                {"samsung"}, {"iphone"}, {"shirt"} };

        return urunlerArrayi;
    }

    @Test (dataProvider = "UrunProvideri")
    public void aramaTesti (String aranacakUrun) {
        // testotomasyonu sayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();

        //arama kutusuna aranacakUrun'u yazdirip enter'a basin
        testotomasyonuPage.aramaKutusu.sendKeys(aranacakUrun + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin
        String unExpectedAramaSonucu = ConfigReader.getProperty("toUnexpectedSonuc");
        String actualAramaSonucYazisi = testotomasyonuPage.aramaSonucuElementi.getText();
        Assert.assertNotEquals(actualAramaSonucYazisi,unExpectedAramaSonucu,aranacakUrun + "bulunamadi");

        Driver.quitDriver();
    }
}
