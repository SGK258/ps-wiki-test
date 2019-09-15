package wiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NikePage {
    public WebDriver driver;
    public String pageTitle ="Nike (mythology)";


    public void confirmAtNikePage(WebDriver driver){
        Assert.assertTrue(driver.getTitle().contains(pageTitle));
    }

    public void confirmFamilyTableDisplayed(WebDriver driver){
        boolean family = driver.findElement((By.id("Family_tree"))).isDisplayed();
        boolean familyTable = driver.findElement(By.className("toccolours")).isDisplayed();
        boolean familyCaption = driver.findElement(By.tagName("caption")).isDisplayed();
        assert family;
        assert familyTable;
        assert familyCaption;


    }


}
