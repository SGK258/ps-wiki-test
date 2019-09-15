package wiki.testspec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import wiki.pages.*;
import org.testng.annotations.Test;


public class MetisTest {

    String baseURL = "https://en.wikipedia.org";
    public MetisPage metisPage;
    public HomePage homePage;
    public WebDriver driver  = new ChromeDriver();
    public String searchText = "Metis (mythology)";
    public NikePage nikePage;
    public String compareText ="In ancient Greek religion, Nike was a goddess who personified victory. Her Roman equivalent was Victoria.";


    @Test(priority=1)
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver.navigate().to(baseURL);

        homePage = new HomePage();                              //At Wiki base page
        homePage.confirmAtHomePage(driver);
        homePage.enterSearchTextAndSumbit(searchText, driver);  //Search for Metis and opens Metis page
        metisPage = new MetisPage();                            //At Metis Page
        metisPage.confirmAtMetisPage(driver);

    }


   @Test(priority=2)
    public void MentisPageContentsSectionLinksTest(){

       metisPage = new MetisPage();
       metisPage.printNumberOfLinksInContentsSection(driver);
       metisPage.verifyContentSectionLinksAreHeaders(driver);
       metisPage.verifyContentSectionLinksWorking(driver);
       metisPage.hoverTextTheLinkInPersonifiedSection(driver, "Nike", compareText);
       metisPage.findAndClickTheLinkInPersonifiedSection(driver, "Nike");

       nikePage = new NikePage();
       nikePage.confirmAtNikePage(driver);
       nikePage.confirmFamilyTableDisplayed(driver);
    }

    @Test(priority=3)
    public void NikePageFamilySectionTest(){

        nikePage = new NikePage();
        nikePage.confirmAtNikePage(driver);
        nikePage.confirmFamilyTableDisplayed(driver);
    }

    @Test(priority=3)
    public void cleanUp(){
       driver.quit();
    }


}

