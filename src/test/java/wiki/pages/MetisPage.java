package wiki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.util.List;

public class MetisPage {
    public WebDriver driver;
    public String pageTitle ="Metis (mythology)";


    public void confirmAtMetisPage(WebDriver driver){
        Assert.assertTrue(driver.getTitle().contains(pageTitle));
    }

    public void printNumberOfLinksInContentsSection(WebDriver driver){
        WebElement contentSection = driver.findElement(By.id("toc"));

        List<WebElement> contentLinks = contentSection.findElements(By.tagName("a"));
        int i = contentSection.findElements(By.tagName("a")).size();
        System.out.println("total Number of Links = " + i);
    }


    /*  Test For  Requirement - the headings listed in the Contents box are used as headings on the page
* */
    public void verifyContentSectionLinksAreHeaders(WebDriver driver){

        WebElement contentSection = driver.findElement(By.id("toc"));

        List<WebElement> contentLinks = contentSection.findElements(By.tagName("a"));
        int i = contentSection.findElements(By.tagName("a")).size();
        System.out.println("total Number of Links = " + i);


        for ( WebElement we: contentLinks) {
            boolean cond;
            String linkText = we.getText().replace(we.getText().substring(0,1), "").trim();

            if (linkText.contains(" ")) {
                linkText = linkText.replace(" ", "_");
            }
            System.out.println( linkText);

            WebElement linkHeader = driver.findElement(By.id(linkText));
//            cond = linkHeader.getAttribute("className").contains("mw-headline");
//            System.out.println(cond);
//            if(!cond) break;
            try {
                cond = we.getAttribute("href").contains(driver.getCurrentUrl().concat("#").concat(linkText));
                System.out.println(cond);
                if(!cond) {
                    throw new Error("element not found");
                }
            }
            catch (Exception e){
                System.out.println("NotFound");
            }

        }

    }


    /*
    * the headings listed in the Contents box have functioning hyperlinks
    * */
    public void verifyContentSectionLinksWorking(WebDriver driver){

        WebElement contentSection = driver.findElement(By.id("toc"));  // Get Contents section element which contains all header links

        List<WebElement> contentLinks = contentSection.findElements(By.tagName("a"));
        int i = contentSection.findElements(By.tagName("a")).size(); //Get number of links
        System.out.println("total Number of Links = " + i);


        for ( WebElement we: contentLinks) {
            String linkText = we.getText().replace(we.getText().substring(0,1), "").trim();
            System.out.println( linkText);
            if (linkText.contains(" ")) {
                linkText = linkText.replace(" ", "_");
            }

            WebElement link = we.findElement(By.xpath("//a[@href='#"+linkText+"']"));

            link.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(driver.getTitle().contains("404")) {
                System.out.println("404 Found");
            }
            driver.navigate().back();

        }

    }


    /*
    * Test For  Requirement - in the Personified concepts, Nike has a popup that contains the following text:  In ancient Greek religion, Nike was a goddess who personified victory. Her Roman equivalent was Victoria.
    * */
    public void hoverTextTheLinkInPersonifiedSection(WebDriver driver, String searchPCText, String compareText){

        WebElement pc = driver.findElement(By.xpath ("//th[contains(text(), 'Personified concepts')]"));
        WebElement searchElement = driver.findElement(By.xpath("//a[contains(@href,'"+searchPCText+"')]"));


        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.linkText("Nike"));
        builder.moveToElement(element).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        builder.moveToElement(element).build().perform();


        WebElement searchElementHoverContent = driver.findElement(By.xpath("//div[@class='mwe-popups-container']"));
        String displayedText = searchElementHoverContent.findElement(By.tagName("p")).getText();
        Assert.assertEquals(compareText.toUpperCase(), displayedText.toUpperCase());

    }


    /*
     *  Test For  Requirement - in the Personified concepts, if you click on Nike, it takes you to a page that displays a family tree
     * */
    public void findAndClickTheLinkInPersonifiedSection(WebDriver driver, String searchPCText){

        WebElement pc = driver.findElement(By.xpath ("//th[contains(text(), 'Personified concepts')]"));
        WebElement searchElement = driver.findElement(By.xpath("//a[contains(@href,'"+searchPCText+"')]"));
        searchElement.click();
    }

}
