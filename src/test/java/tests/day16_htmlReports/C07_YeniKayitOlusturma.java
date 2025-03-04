package tests.day16_htmlReports;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EditorPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C07_YeniKayitOlusturma {
    @DataProvider
    public static Object[][] kisiBilgileriProvider() {
        String [][] kisiBilgileriArr = {{"Ipek","Sozbir","QA","Ankara","Junior","2024-10-12","50000"}};
        return kisiBilgileriArr;
    }

    // Farkli datalarla tabloya 5 kayit ekleyip asagidaki testi yapin
    // When kullanici https://editor.datatables.net/ adresine gider
    // Then new butonuna basar
    // And tum bilgileri girer
    // And Create tusuna basar
    // When kullanici ilk isim ile arama yapar
    // Then isim bolumunde isminin oldugunu dogrular


    @Test (dataProvider =  "kisiBilgileriProvider")
    public void kayitTesti (String firstName, String lastName, String position, String office, String extension,String startDate, String salary) {

        // kullanici https://editor.datatables.net/ adresine gider
        Driver.getDriver().get(ConfigReader.getProperty("editorUrl"));


        // new butonuna basar
        EditorPage editorPage = new EditorPage();
        editorPage.newButonu.click();

        // tum bilgileri girer
        Actions actions = new Actions(Driver.getDriver());
        ReusableMethods.bekle(1);
        actions
                .sendKeys(firstName)
                .sendKeys(Keys.TAB)
                .sendKeys(lastName)
                .sendKeys(Keys.TAB)
                .sendKeys(position)
                .sendKeys(Keys.TAB)
                .sendKeys(office)
                .sendKeys(Keys.TAB)
                .sendKeys(extension)
                .sendKeys(Keys.TAB)
                .sendKeys(startDate)
                .sendKeys(Keys.TAB)
                .sendKeys(salary).perform();

        ReusableMethods.bekle(1);

        // Create tusuna basar
        editorPage.createButonu.click();

        // kullanici ilk isim ile arama yapar
        editorPage.searchKutusu.sendKeys(firstName);
        ReusableMethods.bekle(1);

        // isim bolumunde isminin oldugunu dogrular
        String actualIlkIsim = editorPage.ilkIsimElementi.getText();
        String expectedIsim = firstName + " " + lastName;
        Assert.assertEquals(actualIlkIsim,expectedIsim);

        Driver.quitDriver();
    }
}
