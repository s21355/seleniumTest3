package seleniumTest3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class seleniumTest3 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.msedge.driver", "resources/msedgedriver");
        EdgeOptions options= new EdgeOptions();
        options.addArguments("start-maximized", "--headless");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void checkNumberOfLinks()  {
        driver.get("http://webdriveruniversity.com/index.html");
        List<WebElement> list=driver.findElements(By.xpath("//*[@href or @src]"));

        for(WebElement e : list){
            String link = e.getAttribute("href");
            if(null==link)
                link=e.getAttribute("src");
            System.out.println(e.getTagName() + "=" + link);
        }
        System.out.println(list.size());
        assertEquals(31, list.size());

    }

    @Test
    public void checkNumberOfLinksButEasy() {
        driver.get("http://webdriveruniversity.com/index.html");
        int myCount = driver.findElements(By.xpath("//*[@href or @src]")).size();
        assertEquals(31, myCount);
    }

    @Test
    public void checkNumberOfImages()  {
        driver.get("http://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html");

        List<WebElement> listImg=driver.findElements(By.xpath("//*[@img or @src]"));
        for(WebElement e : listImg) {
            String img = e.getAttribute("img");
        }
        assertEquals(5, listImg.size());
    }

    @Test
    public void checkNumberOfImagesButEasy()  {
        driver.get("http://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html");

        int myCount = driver.findElements(By.xpath("//*[@img or @src]")).size();
        assertEquals(5, myCount);
    }

    @Test
    public void checkEveryLink()  {
        driver.get("http://webdriveruniversity.com/index.html");
        List<WebElement> links = driver.findElements(By.xpath("//div[@class='col-md-12']/a"));
        int linksClicked = 0;

        for (WebElement link : links) {
            link.click();
            linksClicked++;
            driver.navigate().back();
            links = driver.findElements(By.xpath("//div[@class='col-md-12']/a"));
        }
        assertEquals(links.size(), linksClicked);
    }

    @Test
    public void checkNumberOfTextFields()  {
        driver.get("http://webdriveruniversity.com/Contact-Us/contactus.html");

        int inputCount = driver.findElements(By.xpath("//form[@id='contact_form']//input[@type='text']")).size();

        System.out.println(inputCount);
        System.out.println(driver.findElements(By.xpath("//form[@id='contact_form']//input[@type='text']")).size());
        assertEquals(3, inputCount);
    }
}


