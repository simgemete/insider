package Tests;

import Base.BaseTest;
import Page.*;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsiderTests extends BaseTest {



    @Test
    public void insiderTest() throws InterruptedException{

        MainPage mainPage = new MainPage(driver);
        CareersPage careersPage = new CareersPage(driver);
        LeverPage leverPage = new LeverPage(driver);
        PositionsPage positionsPage = new PositionsPage(driver);

        //Websitesinin açıldığı kontrol edilir.
        Assertions.assertNotNull(mainPage.siteControl(),"Site acilmadi");
        mainPage.clickCookies();

        //More ve Careers tıklanır.
        mainPage.menu();

        //Kariyer sekmesinin açıldığı ve istenilen alanların kontrolü sağlanır.
        Assertions.assertNotNull(careersPage.insiderControl(),"Kariyer sekmesi açılmadı");
        Assertions.assertNotNull(careersPage.seeAllTeamsControl(),"Kariyer sekmesi açılmadı");
        Assertions.assertNotNull(careersPage.locationsControl(),"Kariyer sekmesi açılmadı");


        //Kalite Güvence seçilir.
        careersPage.QaPage();

        //Lokasyon seçimi yapılır.
        positionsPage.LocationJobs();

        //Lever sayfasına gittiği kontrol edilir.
        String current = driver.getWindowHandle();
        leverPage.LeverControl();
        leverPage.switchTab(current);
        Assertions.assertEquals(leverPage.getLocation(),"ISTANBUL, TURKEY /","Girilen Konum Bilgisi Bulunamadı");


    }



}
