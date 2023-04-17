package Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    public WebDriver driver;

    public enum Browsers{
        CHROME, EDGE, FIREFOX,
    }

    @Before
    public void setUp() {
        driverSetup(Browsers.CHROME);
        driver = getDriver(Browsers.CHROME);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.useinsider.com/");
    }

    @After


    public void tearDown() throws IOException {

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = timeStamp + ".jpg";
            Files.createDirectories(Paths.get("src/screenshots"));
            File target = new File("src/screenshots/" +fileName);
            try {
                FileHandler.copy(source, target);
                System.out.println("Ekran görüntüsü kaydedildi: " + target.getAbsolutePath());
            }
            catch (IOException e)
            {
                System.out.println("Ekran görüntüsü kaydedilirken hata oluştu: " + e.getMessage());
            }
        driver.quit();
    }



    private void driverSetup(Browsers browserType){
        switch (browserType){
            case CHROME:
                WebDriverManager.chromedriver().setup();
            case EDGE:
                WebDriverManager.edgedriver().setup();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
            default: WebDriverManager.chromedriver().setup();
        }
    }

    private WebDriver getDriver(Browsers browserType){
        return switch (browserType) {
            case CHROME -> new ChromeDriver();
            case EDGE -> new EdgeDriver();
            case FIREFOX -> new FirefoxDriver();
        };
    }
}
